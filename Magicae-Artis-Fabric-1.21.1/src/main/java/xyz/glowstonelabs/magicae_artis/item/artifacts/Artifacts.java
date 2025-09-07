package xyz.glowstonelabs.magicae_artis.item.artifacts;

import net.minecraft.util.Formatting;

public enum Artifacts {
    NONE(Formatting.GRAY),
    FIRE(Formatting.DARK_RED),
    WATER(Formatting.BLUE),
    EARTH(Formatting.DARK_GREEN),
    WIND(Formatting.WHITE);

    private final Formatting formatting;

    Artifacts(Formatting formatting) {
        this.formatting = formatting;
    }

    public Formatting getFormatting() {
        return formatting;
    }
}
