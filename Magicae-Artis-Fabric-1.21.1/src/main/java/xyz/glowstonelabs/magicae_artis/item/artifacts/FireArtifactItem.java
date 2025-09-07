package xyz.glowstonelabs.magicae_artis.item.artifacts;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import xyz.glowstonelabs.magicae_artis.Util.ArtifactSwitcher;

public class FireArtifactItem extends Item {
    public FireArtifactItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        boolean success = ArtifactSwitcher.setWandArtifact(user, xyz.glowstonelabs.magicae_artis.item.artifacts.Artifacts.FIRE);
        return success ? TypedActionResult.success(stack, world.isClient()) : TypedActionResult.pass(stack);
    }
}
