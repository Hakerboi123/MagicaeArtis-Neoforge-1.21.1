package xyz.glowstonelabs.magicae_artis.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.glowstonelabs.magicae_artis.MagicaeArtis;

public class ModItemGroups {
    public static final ItemGroup MAGICAE_ARTIS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MagicaeArtis.MOD_ID, "magicae_artis"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ARCANITE_INGOT))
                    .displayName(Text.translatable("itemgroup.magicae_artis.magicae_artis_title"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ARCANITE_INGOT);
                        entries.add(ModItems.RAW_ARCANITE);

                        entries.add(ModItems.STARTER_WAND);

                        entries.add(ModItems.FIREBALL_ARTIFACT);
                        entries.add(ModItems.WATER_ARTIFACT);
                        entries.add(ModItems.VEINMINER_ARTIFACT);
                        entries.add(ModItems.WINDCHARGE_ARTIFACT);

                        entries.add(ModItems.REPAIR_TRINKET);
                    }).build());



    public static void load() { MagicaeArtis.LOGGER.info("Registering Mod ItemGroups for " + MagicaeArtis.MOD_ID); }
}
