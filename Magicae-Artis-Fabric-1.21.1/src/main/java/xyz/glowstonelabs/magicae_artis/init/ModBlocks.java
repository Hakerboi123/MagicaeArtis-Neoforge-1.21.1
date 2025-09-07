package xyz.glowstonelabs.magicae_artis.init;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import xyz.glowstonelabs.magicae_artis.MagicaeArtis;

import java.util.Optional;

public class ModBlocks {
//    public static final Block XAENON_BLOCK = registerBlock("xaenon_block",
//            new Block(AbstractBlock.Settings.create().strength(4f)
//                    .requiresTool().sounds(BlockSoundGroup.METAL)));


    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MagicaeArtis.MOD_ID, name), block);
    }
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MagicaeArtis.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void load() { MagicaeArtis.LOGGER.info("Registering Mod Blocks for " + MagicaeArtis.MOD_ID); }
}
