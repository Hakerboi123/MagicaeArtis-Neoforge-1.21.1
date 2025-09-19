package xyz.glowstonelabs.magicae_artis.item.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class RepairTrinketItem extends TrinketItem {
    public RepairTrinketItem(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (!entity.getWorld().isClient && entity instanceof PlayerEntity player) {
            // runs every 10 ticks
            if (entity.getWorld().getTime() % 20 == 0) {
                repairPlayerItems(player);
            }
        }
    }

    private void repairPlayerItems(PlayerEntity player) {
        // Repair items in all inventory slots
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack itemStack = player.getInventory().getStack(i);

            // Skip empty slots and the repair trinket itself
            if (itemStack.isEmpty() || itemStack.getItem() instanceof RepairTrinketItem) {
                continue;
            }

            // Repair damaged items
            if (itemStack.isDamaged()) {
                itemStack.setDamage(itemStack.getDamage() - 1); // Repair 1 durability point

                // Optional: Add particle effect or sound
                if (player.getWorld() instanceof ServerWorld serverWorld) {
                    // You can add particle effects here if desired
                    // serverWorld.spawnParticles(ParticleTypes.ENCHANT, player.getX(), player.getY() + 1, player.getZ(), 3, 0.3, 0.3, 0.3, 0.1);
                }
            }
        }
    }
}