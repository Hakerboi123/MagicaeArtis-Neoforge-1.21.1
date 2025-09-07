package xyz.glowstonelabs.magicae_artis.Util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import xyz.glowstonelabs.magicae_artis.item.artifacts.Artifacts;
import xyz.glowstonelabs.magicae_artis.item.wands.StarterWandItem;

public class ArtifactSwitcher {

    /**
     * Sets the first wand found in the player's inventory to the given artifact.
     * Returns true if a wand was found and switched, false otherwise.
     */
    public static boolean setWandArtifact(PlayerEntity player, Artifacts artifact) {
        if (player == null) return false;

        // Check all inventory slots
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() instanceof StarterWandItem) {
                StarterWandItem.setArtifact(stack, artifact);
                player.sendMessage(Text.literal("Wand artifact changed to " + artifact.name()), true);
                return true;
            }
        }

        // Check offhand
        for (ItemStack stack : player.getInventory().offHand) {
            if (stack.getItem() instanceof StarterWandItem) {
                StarterWandItem.setArtifact(stack, artifact);
                player.sendMessage(Text.literal("Wand artifact changed to " + artifact.name()), true);
                return true;
            }
        }

        return false; // no wand found
    }
}
