package xyz.glowstonelabs.magicae_artis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import xyz.glowstonelabs.magicae_artis.init.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_ARCANITE, 1)
                .input(Items.RAW_GOLD)
                .input(Items.AMETHYST_SHARD)
                .criterion("has_raw_gold", conditionsFromItem(Items.RAW_GOLD))
                .criterion("has_amethyst_shard", conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);

        List<ItemConvertible> arcanite = List.of(
                (ItemConvertible) ModItems.RAW_ARCANITE
        );

        offerSmelting(exporter, arcanite, RecipeCategory.MISC, ModItems.ARCANITE_INGOT, 0.25f, 200, "xaenon");
        offerBlasting(exporter, arcanite, RecipeCategory.MISC, ModItems.ARCANITE_INGOT, 0.25f, 100, "xaenon");
    }
}
