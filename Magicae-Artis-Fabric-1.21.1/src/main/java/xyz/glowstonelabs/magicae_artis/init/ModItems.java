package xyz.glowstonelabs.magicae_artis.init;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.glowstonelabs.magicae_artis.MagicaeArtis;
import xyz.glowstonelabs.magicae_artis.item.artifacts.EarthArtifactItem;
import xyz.glowstonelabs.magicae_artis.item.artifacts.FireArtifactItem;
import xyz.glowstonelabs.magicae_artis.item.artifacts.WaterArtifactItem;
import xyz.glowstonelabs.magicae_artis.item.artifacts.WindArtifactItem;
import xyz.glowstonelabs.magicae_artis.item.trinkets.RepairTrinketItem;
import xyz.glowstonelabs.magicae_artis.item.wands.StarterWandItem;

import static xyz.glowstonelabs.magicae_artis.MagicaeArtis.MOD_ID;


public class  ModItems {
    // Materials
    public static final Item ARCANITE_INGOT = registerItem("arcanite_ingot", new Item(new Item.Settings()));
    public static final Item RAW_ARCANITE = registerItem("raw_arcanite", new Item(new Item.Settings()));

    // Wands
    public static final Item STARTER_WAND = registerItem("starter_wand", new StarterWandItem(new Item.Settings()));

    // Artifacts
    public static final Item FIRE_ARTIFACT = registerItem("fire_artifact", new FireArtifactItem(new Item.Settings()));
    public static final Item WATER_ARTIFACT = registerItem("water_artifact", new WaterArtifactItem(new Item.Settings()));
    public static final Item EARTH_ARTIFACT = registerItem("earth_artifact", new EarthArtifactItem(new Item.Settings()));
    public static final Item WIND_ARTIFACT = registerItem("wind_artifact", new WindArtifactItem(new Item.Settings()));

    // Trinkets

    public static final Item REPAIR_TRINKET = registerItem("repair_trinket", new RepairTrinketItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }


    public static void load() { MagicaeArtis.LOGGER.info("Registering Mod Items for " + MagicaeArtis.MOD_ID); }
}
