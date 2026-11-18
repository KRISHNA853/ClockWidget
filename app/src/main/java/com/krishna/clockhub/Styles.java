package com.krishna.clockhub;

import java.util.ArrayList;
import java.util.List;

public final class Styles {
    private Styles() {}

    public static final class Def {
        public final String key, label, pattern;
        public final Class<?> provider;
        public final int layout, cellsW, cellsH, c1, c2, angle, text, accent, corner;
        public final boolean border;

        Def(String key, String label, Class<?> provider, int layout, int cellsW, int cellsH, int c1, int c2, int angle,
            int text, int accent, String pattern, boolean border, int corner) {
            this.key = key; this.label = label; this.provider = provider; this.layout = layout;
            this.cellsW = cellsW; this.cellsH = cellsH; this.c1 = c1; this.c2 = c2; this.angle = angle;
            this.text = text; this.accent = accent; this.pattern = pattern; this.border = border; this.corner = corner;
        }
    }

    public static final List<Def> ALL = new ArrayList<>();

    private static void add(String key, String label, Class<?> provider, int layout, int cw, int ch, int c1, int c2, int angle,
                            int text, int accent, String pattern, boolean border, int corner) {
        ALL.add(new Def(key, label, provider, layout, cw, ch, c1, c2, angle, text, accent, pattern, border, corner));
    }

    static {
        add("aura", "Aura", AuraProvider.class, R.layout.w_aura, 4, 2, 0xFF1F1B5E, 0xFFE0487F, 45, 0xFFFFFFFF, 0xFFFFD6E8, "none", false, 28);
        add("hacker", "Hacker Terminal", HackerProvider.class, R.layout.w_hacker, 4, 2, 0xFF000000, 0xFF0B3D1E, 45, 0xFF00FF9C, 0xFFB6FF3B, "grid", true, 14);
        add("neon", "Neon Night", NeonProvider.class, R.layout.w_neon, 4, 2, 0xFF0A0014, 0xFF3B0A5E, 45, 0xFFFFFFFF, 0xFFFF2BD6, "none", true, 30);
        add("matrix", "Matrix Strip", MatrixProvider.class, R.layout.w_matrix, 4, 1, 0xFF000000, 0xFF003B14, 0, 0xFF00FF66, 0xFF7CFFB0, "scan", true, 16);
        add("glass", "Glass Dark", GlassProvider.class, R.layout.w_glass, 4, 2, 0x8C1B1B2F, 0x4D3A3A5C, 45, 0xFFFFFFFF, 0xB3FFFFFF, "none", true, 30);
        add("sunset", "Sunset", SunsetProvider.class, R.layout.w_sunset, 4, 2, 0xFFFF512F, 0xFFDD2476, 45, 0xFFFFFFFF, 0xFFFFE3B3, "none", false, 28);
        add("ocean", "Ocean Stack", OceanProvider.class, R.layout.w_ocean, 2, 2, 0xFF2193B0, 0xFF6DD5ED, 45, 0xFFFFFFFF, 0xFFE0FBFF, "none", false, 26);
        add("cyberpunk", "Cyberpunk Yellow", CyberpunkProvider.class, R.layout.w_cyberpunk, 4, 2, 0xFFFCEE0A, 0xFFFF003C, 0, 0xFF000000, 0xFF000000, "scan", true, 8);
        add("hackersplit", "Hacker Split", HackersplitProvider.class, R.layout.w_hackersplit, 4, 2, 0xFF0D0D0D, 0xFF1B1B1B, 90, 0xFF00FF41, 0xFF00D9FF, "dots", true, 12);
        add("vapor", "Vaporwave", VaporProvider.class, R.layout.w_vapor, 4, 2, 0xFFFF71CE, 0xFF01CDFE, 45, 0xFFFFFFFF, 0xFFFFFB96, "none", false, 30);
        add("midnight", "Midnight Sky", MidnightProvider.class, R.layout.w_midnight, 2, 2, 0xFF0F2027, 0xFF2C5364, 90, 0xFFFFFFFF, 0xFF8EE7FF, "stars", false, 26);
        add("mint", "Mint Pop", MintProvider.class, R.layout.w_mint, 4, 2, 0xFF43E97B, 0xFF38F9D7, 45, 0xFF0B3B2E, 0xFF0B3B2E, "none", false, 28);
        add("retro", "Retro Pixel", RetroProvider.class, R.layout.w_retro, 4, 2, 0xFF2B0A3D, 0xFF7B2FF7, 45, 0xFFFFE66D, 0xFFFF6B9D, "scan", true, 6);
        add("aurora", "Aurora", AuroraProvider.class, R.layout.w_aurora, 4, 2, 0xFF00C9A7, 0xFF845EC2, 0, 0xFFFFFFFF, 0xFFD5FFF3, "stars", false, 28);
        add("lava", "Lava", LavaProvider.class, R.layout.w_lava, 4, 2, 0xFFF12711, 0xFFF5AF19, 45, 0xFFFFFFFF, 0xFF3A0A00, "none", false, 28);
        add("gold", "Gold Luxe", GoldProvider.class, R.layout.w_gold, 4, 2, 0xFF0B0B0B, 0xFF3A2A00, 45, 0xFFF5D76E, 0xFFC9A227, "none", true, 22);
        add("gamer", "Gamer RGB", GamerProvider.class, R.layout.w_gamer, 4, 1, 0xFF12002B, 0xFF2A0050, 0, 0xFFFFFFFF, 0xFF00E5FF, "none", true, 18);
        add("dracula", "Dracula Shell", DraculaProvider.class, R.layout.w_dracula, 4, 2, 0xFF282A36, 0xFF44475A, 45, 0xFFF8F8F2, 0xFFBD93F9, "dots", true, 16);
        add("candy", "Candy Pop", CandyProvider.class, R.layout.w_candy, 2, 2, 0xFFFF9A9E, 0xFFFAD0C4, 45, 0xFF5A1A3A, 0xFFFF3D81, "dots", false, 30);
        add("mono", "Mono Dark", MonoProvider.class, R.layout.w_mono, 4, 2, 0xFF000000, 0xFF141414, 90, 0xFFFFFFFF, 0xFF8A8A8A, "none", true, 24);
        add("ice", "Ice", IceProvider.class, R.layout.w_ice, 2, 2, 0xFFE0EAFC, 0xFFCFDEF3, 45, 0xFF12263F, 0xFF2D6CDF, "none", false, 26);
        add("toxic", "Toxic Lime", ToxicProvider.class, R.layout.w_toxic, 4, 2, 0xFF0A0A0A, 0xFF1A1A00, 45, 0xFFCCFF00, 0xFFFFFFFF, "grid", true, 14);
        add("galaxy", "Galaxy", GalaxyProvider.class, R.layout.w_galaxy, 4, 2, 0xFF0F0C29, 0xFF302B63, 45, 0xFFFFFFFF, 0xFFA78BFA, "stars", false, 28);
        add("fun", "Fun Emoji", FunProvider.class, R.layout.w_fun, 4, 2, 0xFFFFDE59, 0xFFFF914D, 45, 0xFF3B1D00, 0xFF7A2E00, "dots", false, 30);
        add("redalert", "Red Alert", RedalertProvider.class, R.layout.w_redalert, 4, 2, 0xFF1A0000, 0xFF4D0000, 45, 0xFFFF3B3B, 0xFFFFB000, "grid", true, 12);
    }

    public static Def get(String key) {
        for (Def d : ALL) {
            if (d.key.equals(key)) return d;
        }
        return ALL.get(0);
    }
}
