package xyz.glowstonelabs.magicae_artis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import xyz.glowstonelabs.magicae_artis.init.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ARCANITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ARCANITE, Models.GENERATED);

        itemModelGenerator.register(ModItems.FIRE_ARTIFACT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WATER_ARTIFACT, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARTH_ARTIFACT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WIND_ARTIFACT, Models.GENERATED);

        itemModelGenerator.register(ModItems.REPAIR_TRINKET, Models.GENERATED);
    }
}