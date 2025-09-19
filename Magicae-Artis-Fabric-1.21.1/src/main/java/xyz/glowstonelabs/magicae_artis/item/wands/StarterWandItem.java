package xyz.glowstonelabs.magicae_artis.item.wands;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.glowstonelabs.magicae_artis.item.artifacts.Artifacts;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class StarterWandItem extends Item {

    private static final int VEIN_MINE_LIMIT = 999;

    public StarterWandItem(Settings settings) {
        super(settings);
    }

    // --- DataComponent helpers ---
    public static void setArtifact(ItemStack stack, Artifacts artifact) {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("Artifact", artifact.name());
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    public static Artifacts getArtifact(ItemStack stack) {
        if (stack.contains(DataComponentTypes.CUSTOM_DATA)) {
            NbtComponent comp = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (comp != null) {
                NbtCompound nbt = comp.copyNbt();
                if (nbt.contains("Artifact")) {
                    try {
                        return Artifacts.valueOf(nbt.getString("Artifact"));
                    } catch (IllegalArgumentException ignored) {}
                }
            }
        }
        return Artifacts.NONE;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        Artifacts artifact = getArtifact(stack);

        if (artifact == Artifacts.NONE) {
            user.sendMessage(Text.literal("You don't have any artifacts equipped!"), true);
            return TypedActionResult.fail(stack);
        }

        if (artifact == Artifacts.FIREBALL) castFire(world, user);
        else if (artifact == Artifacts.WINDCHARGE) castWind(world, user);
        else if (artifact == Artifacts.VEINMINER) castEarth(world, user, stack);

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(stack, world.isClient());
    }

    private void castFire(World world, PlayerEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS,
                0.5f, 1.0f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

        if (!world.isClient) {
            Vec3d dir = user.getRotationVec(1.0F);
            SmallFireballEntity fireball = new SmallFireballEntity(
                    world,
                    user.getX(),
                    user.getEyeY() - 0.1,
                    user.getZ(),
                    dir
            );
            world.spawnEntity(fireball);
        }
    }

    private void castWind(World world, PlayerEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_BREEZE_SHOOT, SoundCategory.PLAYERS,
                0.5f, 1.0f);

        if (!world.isClient) {
            Vec3d dir = user.getRotationVec(1.0F);
            WindChargeEntity wind = new WindChargeEntity(user, world, dir.x, dir.y, dir.z);
            wind.setVelocity(dir.normalize().multiply(2.0));
            wind.setPos(user.getX(), user.getEyeY() - 0.1, user.getZ());
            world.spawnEntity(wind);
        }
    }

    private void castEarth(World world, PlayerEntity user, ItemStack stack) {
        if (world.isClient) return;

        // Raycast to find the exact block the player is looking at
        var hit = user.raycast(5.0, 0.0F, false);
        if (hit.getType() != HitResult.Type.BLOCK) return;

        BlockPos targetPos = ((BlockHitResult) hit).getBlockPos();
        BlockState targetState = world.getBlockState(targetPos);

        if (targetState.isAir()) return;
        Block targetBlock = targetState.getBlock();

        // Track visited blocks to avoid revisiting
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> toVisit = new LinkedList<>();
        toVisit.add(targetPos);

        int brokenCount = 0;

        while (!toVisit.isEmpty() && brokenCount < VEIN_MINE_LIMIT) {
            BlockPos pos = toVisit.poll();
            if (visited.contains(pos)) continue;
            visited.add(pos);

            BlockState state = world.getBlockState(pos);
            if (state.getBlock() != targetBlock) continue;

            world.breakBlock(pos, true);
            brokenCount++;

            // Add neighboring blocks in all 6 directions
            for (BlockPos offset : List.of(
                    pos.up(), pos.down(), pos.north(), pos.south(), pos.east(), pos.west()
            )) {
                if (!visited.contains(offset)) toVisit.add(offset);
            }
        }

        user.sendMessage(Text.literal("Vein mined " + brokenCount + " blocks!"), true);
    }


    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        Artifacts artifact = getArtifact(stack);
        MutableText label = Text.literal("Artifact: ").formatted(Formatting.GRAY);
        MutableText artifactName = (artifact == Artifacts.NONE)
                ? Text.literal("None").formatted(Formatting.GRAY, Formatting.BOLD)
                : Text.literal(artifact.name()).formatted(artifact.getFormatting(), Formatting.BOLD);
        label.append(artifactName);
        tooltip.add(label);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
