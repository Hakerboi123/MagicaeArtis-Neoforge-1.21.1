package xyz.glowstonelabs.magicae_artis;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static xyz.glowstonelabs.magicae_artis.MagicaeArtis.MOD_ID;

public class MagicaeArtisClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitializeClient() {
		LOGGER.info("=============== Magicae Artis Client =========");
		LOGGER.info("Loading Magicae Artis's Client Content...");


		LOGGER.info("Magicae Artis Client Loaded!");
		LOGGER.info("=============== Magicae Artis Client =========");
	}
}