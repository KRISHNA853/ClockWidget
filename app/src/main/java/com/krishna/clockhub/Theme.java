package com.krishna.clockhub;

import android.content.Context;
import android.content.SharedPreferences;

public final class Theme {
    public int c1, c2, angle, text, accent, corner;
    public String pattern;
    public boolean border;

    public static Theme load(Context ctx, Styles.Def d) {
        SharedPreferences p = ctx.getSharedPreferences("cfg", Context.MODE_PRIVATE);
        Theme t = new Theme();
        t.c1 = p.getInt(d.key + ".c1", d.c1);
        t.c2 = p.getInt(d.key + ".c2", d.c2);
        t.angle = p.getInt(d.key + ".angle", d.angle);
        t.text = p.getInt(d.key + ".text", d.text);
        t.accent = p.getInt(d.key + ".accent", d.accent);
        t.corner = p.getInt(d.key + ".corner", d.corner);
        t.pattern = p.getString(d.key + ".pattern", d.pattern);
        t.border = p.getBoolean(d.key + ".border", d.border);
        return t;
    }

    public static void save(Context ctx, Styles.Def d, Theme t) {
        SharedPreferences.Editor e = ctx.getSharedPreferences("cfg", Context.MODE_PRIVATE).edit();
        e.putInt(d.key + ".c1", t.c1);
        e.putInt(d.key + ".c2", t.c2);
        e.putInt(d.key + ".angle", t.angle);
        e.putInt(d.key + ".text", t.text);
        e.putInt(d.key + ".accent", t.accent);
        e.putInt(d.key + ".corner", t.corner);
        e.putString(d.key + ".pattern", t.pattern);
        e.putBoolean(d.key + ".border", t.border);
        e.apply();
    }
}
