package com.krishna.clockhub;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.RemoteViews;

import java.util.Random;

public final class Renderer {
    private Renderer() {}

    private static final int[] PRIMARY = {R.id.p1, R.id.p2, R.id.p3};
    private static final int[] SECONDARY = {R.id.s1, R.id.s2, R.id.s3};
    private static final int[] ACCENT = {R.id.a1, R.id.a2, R.id.a3};

    public static Bitmap makeBg(Theme t, int w, int h, float unit) {
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        double a = Math.toRadians(t.angle);
        float dx = (float) Math.cos(a);
        float dy = (float) Math.sin(a);
        float hl = (Math.abs(w * dx) + Math.abs(h * dy)) / 2f;
        float cx = w / 2f;
        float cy = h / 2f;
        p.setShader(new LinearGradient(cx - dx * hl, cy - dy * hl, cx + dx * hl, cy + dy * hl, t.c1, t.c2, Shader.TileMode.CLAMP));
        c.drawRect(0, 0, w, h, p);
        p.setShader(null);

        drawPattern(c, t, w, h, unit);

        float r = Math.min(t.corner * unit, Math.min(w, h) / 2f);
        Bitmap mask = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas mc = new Canvas(mask);
        Paint mp = new Paint(Paint.ANTI_ALIAS_FLAG);
        mp.setColor(0xFF000000);
        mc.drawRoundRect(new RectF(0, 0, w, h), r, r, mp);
        Paint xp = new Paint();
        xp.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        c.drawBitmap(mask, 0, 0, xp);
        mask.recycle();

        if (t.border) {
            Paint s = new Paint(Paint.ANTI_ALIAS_FLAG);
            s.setStyle(Paint.Style.STROKE);
            float sw = Math.max(1.5f, 1.6f * unit);
            s.setStrokeWidth(sw);
            s.setColor(t.accent);
            float in = sw / 2f;
            float rr = Math.max(0f, r - in);
            c.drawRoundRect(new RectF(in, in, w - in, h - in), rr, rr, s);
        }
        return bmp;
    }

    private static void drawPattern(Canvas c, Theme t, int w, int h, float u) {
        if (t.pattern == null || t.pattern.equals("none")) return;
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        int col = t.accent & 0x00FFFFFF;
        if (t.pattern.equals("grid")) {
            p.setColor((0x26 << 24) | col);
            p.setStrokeWidth(Math.max(1f, 0.7f * u));
            float step = 16f * u;
            for (float x = 0; x < w; x += step) c.drawLine(x, 0, x, h, p);
            for (float y = 0; y < h; y += step) c.drawLine(0, y, w, y, p);
        } else if (t.pattern.equals("dots")) {
            p.setColor((0x40 << 24) | col);
            float step = 12f * u;
            for (float y = step / 2f; y < h; y += step) {
                for (float x = step / 2f; x < w; x += step) c.drawCircle(x, y, 0.9f * u, p);
            }
        } else if (t.pattern.equals("scan")) {
            p.setColor((0x22 << 24) | col);
            float step = 4f * u;
            for (float y = 0; y < h; y += step) c.drawRect(0, y, w, y + Math.max(1f, 1.2f * u), p);
        } else if (t.pattern.equals("stars")) {
            Random rnd = new Random(7);
            int n = Math.max(20, (int) (w * h / (900f * u * u)));
            for (int i = 0; i < n; i++) {
                float x = rnd.nextFloat() * w;
                float y = rnd.nextFloat() * h;
                float r = (0.4f + rnd.nextFloat()) * u;
                int alpha = 60 + rnd.nextInt(140);
                p.setColor((alpha << 24) | 0x00FFFFFF);
                c.drawCircle(x, y, r, p);
            }
        }
    }

    public static void update(Context ctx, AppWidgetManager mgr, int id, Styles.Def d) {
        Theme t = Theme.load(ctx, d);
        Bundle o = mgr.getAppWidgetOptions(id);
        float dens = ctx.getResources().getDisplayMetrics().density;
        int wdp = o.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 0);
        int hdp = o.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT, 0);
        if (wdp <= 0) wdp = d.cellsW == 2 ? 110 : 250;
        if (hdp <= 0) hdp = d.cellsH == 1 ? 60 : 110;
        int wpx = Math.round(wdp * dens);
        int hpx = Math.round(hdp * dens);
        float s = Math.min(1f, 720f / Math.max(wpx, hpx));
        wpx = Math.max(8, Math.round(wpx * s));
        hpx = Math.max(8, Math.round(hpx * s));
        Bitmap bmp = makeBg(t, wpx, hpx, dens * s);

        RemoteViews rv = new RemoteViews(ctx.getPackageName(), d.layout);
        rv.setImageViewBitmap(R.id.bg, bmp);
        int sec = (t.text & 0x00FFFFFF) | 0xB3000000;
        for (int vid : PRIMARY) rv.setTextColor(vid, t.text);
        for (int vid : SECONDARY) rv.setTextColor(vid, sec);
        for (int vid : ACCENT) rv.setTextColor(vid, t.accent);

        Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        rv.setOnClickPendingIntent(R.id.root, pi);
        mgr.updateAppWidget(id, rv);
    }

    public static void updateAll(Context ctx) {
        AppWidgetManager m = AppWidgetManager.getInstance(ctx);
        for (Styles.Def d : Styles.ALL) {
            int[] ids = m.getAppWidgetIds(new ComponentName(ctx, d.provider));
            for (int id : ids) update(ctx, m, id, d);
        }
    }
}
