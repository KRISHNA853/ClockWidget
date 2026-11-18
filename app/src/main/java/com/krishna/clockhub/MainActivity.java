package com.krishna.clockhub;

import android.app.Activity;
import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    static final String UPI_ID = "krishnakantvishwakrma396@okhdfcbank";
    static final String IG_HANDLE = "krishna.vis";
    static final String[] PATTERNS = {"none", "grid", "dots", "scan", "stars"};
    static final String[][] PRESETS = {
            {"Sunset", "#FF512F", "#DD2476"}, {"Ocean", "#2193B0", "#6DD5ED"}, {"Neon", "#0A0014", "#FF2BD6"},
            {"Matrix", "#000000", "#00B140"}, {"Purple", "#7F00FF", "#E100FF"}, {"Fire", "#F12711", "#F5AF19"},
            {"Mint", "#43E97B", "#38F9D7"}, {"Midnight", "#0F2027", "#2C5364"}, {"Cyber", "#FCEE0A", "#FF003C"},
            {"Candy", "#FF9A9E", "#FAD0C4"}, {"Royal", "#141E30", "#243B55"}, {"Toxic", "#0A0A0A", "#3A4A00"}
    };

    private LinearLayout list;
    private float dens;

    private int dp(int v) {
        return Math.round(v * dens);
    }

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        dens = getResources().getDisplayMetrics().density;

        ScrollView sv = new ScrollView(this);
        sv.setBackgroundColor(0xFF0E0E14);
        sv.setClipToPadding(false);
        sv.setOnApplyWindowInsetsListener((v, insets) -> {
            v.setPadding(0, insets.getSystemWindowInsetTop(), 0, insets.getSystemWindowInsetBottom());
            return insets;
        });

        LinearLayout col = new LinearLayout(this);
        col.setOrientation(LinearLayout.VERTICAL);
        col.setPadding(dp(16), dp(20), dp(16), dp(24));
        sv.addView(col);

        buildHeader(col);

        list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        col.addView(list);
        buildList();

        setContentView(sv);
    }

    private TextView text(String s, int sp, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(sp);
        t.setTextColor(color);
        if (bold) t.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);
        return t;
    }

    private Button button(String s) {
        Button b = new Button(this);
        b.setText(s);
        b.setAllCaps(false);
        return b;
    }

    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void addRow(LinearLayout col, Button... btns) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        for (Button bt : btns) {
            row.addView(bt, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        }
        col.addView(row);
    }

    private void buildHeader(LinearLayout col) {
        col.addView(text("Krishna Clock Hub", 26, 0xFFFFFFFF, true));
        TextView sub = text("Made with \u2764 by Krishna  \u2022  " + Styles.ALL.size() + " widget styles", 13, 0xFFB0B0C0, false);
        sub.setPadding(0, dp(2), 0, dp(12));
        col.addView(sub);

        Button ig = button("\uD83D\uDCF8  Instagram  @" + IG_HANDLE);
        ig.setOnClickListener(v -> openInstagram());
        col.addView(ig);

        TextView coffeeTitle = text("\u2615 Buy me a coffee", 16, 0xFFFFD27A, true);
        coffeeTitle.setPadding(0, dp(14), 0, 0);
        col.addView(coffeeTitle);
        col.addView(text("UPI: " + UPI_ID, 13, 0xFFFFFFFF, false));

        Button gpay = button("Google Pay");
        Button phonepe = button("PhonePe");
        Button paytm = button("Paytm");
        Button other = button("Other UPI apps");
        Button copy = button("Copy UPI ID");
        gpay.setOnClickListener(v -> pay("com.google.android.apps.nbu.paisa.user"));
        phonepe.setOnClickListener(v -> pay("com.phonepe.app"));
        paytm.setOnClickListener(v -> pay("net.one97.paytm"));
        other.setOnClickListener(v -> pay(null));
        copy.setOnClickListener(v -> copyUpi());
        addRow(col, gpay, phonepe);
        addRow(col, paytm, other);
        addRow(col, copy);

        TextView how = text("Tap a style to change colors, gradient, pattern and more. Then tap \"Add to home\", "
                + "or long-press home screen \u2192 Widgets \u2192 Krishna Clock Hub.", 12, 0xFF8888A0, false);
        how.setPadding(0, dp(14), 0, dp(12));
        col.addView(how);
    }

    private void openInstagram() {
        Intent app = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/" + IG_HANDLE));
        app.setPackage("com.instagram.android");
        try {
            startActivity(app);
        } catch (Exception e1) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/" + IG_HANDLE)));
            } catch (Exception e2) {
                toast("Could not open Instagram");
            }
        }
    }

    private void pay(String pkg) {
        Uri u = Uri.parse("upi://pay?pa=" + UPI_ID + "&pn=Krishna&cu=INR&tn=Buy%20me%20a%20coffee");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        if (pkg != null) {
            i.setPackage(pkg);
            try {
                startActivity(i);
                return;
            } catch (Exception e1) {
                toast("App not installed, choose another UPI app");
                i.setPackage(null);
            }
        }
        try {
            startActivity(Intent.createChooser(i, "Pay with"));
        } catch (Exception e2) {
            copyUpi();
            toast("No UPI app found. UPI ID copied.");
        }
    }

    private void copyUpi() {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("UPI", UPI_ID));
        toast("UPI ID copied");
    }

    private void buildList() {
        list.removeAllViews();
        int full = getResources().getDisplayMetrics().widthPixels - dp(32);
        for (final Styles.Def d : Styles.ALL) {
            Theme t = Theme.load(this, d);
            int wpx;
            int hpx;
            if (d.cellsW == 2) {
                wpx = dp(130);
                hpx = dp(130);
            } else {
                wpx = full;
                hpx = d.cellsH == 1 ? dp(70) : dp(120);
            }
            Bitmap bmp = Renderer.makeBg(t, wpx / 2, hpx / 2, dens / 2f);
            FrameLayout card = new FrameLayout(this);
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageBitmap(bmp);
            card.addView(iv, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            LinearLayout over = new LinearLayout(this);
            over.setOrientation(LinearLayout.VERTICAL);
            over.setGravity(Gravity.CENTER);
            over.addView(text(d.label + "  \u2022  " + d.cellsW + "\u00D7" + d.cellsH + "  \u270E", 12, t.accent, true));
            TextClock tc = new TextClock(this);
            tc.setFormat12Hour("h:mm a");
            tc.setFormat24Hour("h:mm a");
            tc.setTextColor(t.text);
            tc.setTextSize(d.cellsH == 1 ? 18 : 26);
            tc.setGravity(Gravity.CENTER);
            over.addView(tc);
            card.addView(over, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wpx, hpx);
            lp.setMargins(0, 0, 0, dp(12));
            list.addView(card, lp);
            card.setOnClickListener(v -> showEditor(d));
        }
    }

    private String hex(int c) {
        if ((c >>> 24) == 0xFF) return String.format("#%06X", c & 0xFFFFFF);
        return String.format("#%08X", c);
    }

    private int parseColor(String s, int fallback) {
        try {
            s = s.trim();
            if (!s.startsWith("#")) s = "#" + s;
            return Color.parseColor(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    private int parseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return fallback;
        }
    }

    private EditText field(LinearLayout parent, String label, String value, boolean number) {
        TextView l = new TextView(this);
        l.setText(label);
        l.setTextSize(12);
        l.setPadding(0, dp(8), 0, 0);
        parent.addView(l);
        EditText e = new EditText(this);
        e.setSingleLine(true);
        e.setText(value);
        if (number) e.setInputType(InputType.TYPE_CLASS_NUMBER);
        parent.addView(e);
        return e;
    }

    private void showEditor(final Styles.Def d) {
        final Theme t = Theme.load(this, d);
        ScrollView sv = new ScrollView(this);
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(dp(20), dp(8), dp(20), dp(8));
        sv.addView(c);

        TextView pt = new TextView(this);
        pt.setText("Quick gradients");
        pt.setTextSize(12);
        c.addView(pt);
        HorizontalScrollView hs = new HorizontalScrollView(this);
        LinearLayout pr = new LinearLayout(this);
        pr.setOrientation(LinearLayout.HORIZONTAL);
        hs.addView(pr);
        c.addView(hs);

        final EditText e1 = field(c, "Gradient color 1 (hex, like #FF512F)", hex(t.c1), false);
        final EditText e2 = field(c, "Gradient color 2", hex(t.c2), false);
        final EditText e3 = field(c, "Main text color", hex(t.text), false);
        final EditText e4 = field(c, "Accent color (AM/PM, labels, border)", hex(t.accent), false);
        final EditText e5 = field(c, "Gradient angle (0=right, 45=down-right, 90=down)", String.valueOf(t.angle), true);
        final EditText e6 = field(c, "Corner radius (0-60)", String.valueOf(t.corner), true);

        TextView pl = new TextView(this);
        pl.setText("Background pattern");
        pl.setTextSize(12);
        pl.setPadding(0, dp(8), 0, 0);
        c.addView(pl);
        final Spinner spin = new Spinner(this);
        spin.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, PATTERNS));
        for (int i = 0; i < PATTERNS.length; i++) {
            if (PATTERNS[i].equals(t.pattern)) spin.setSelection(i);
        }
        c.addView(spin);

        final CheckBox cb = new CheckBox(this);
        cb.setText("Neon border");
        cb.setChecked(t.border);
        c.addView(cb);

        for (final String[] p : PRESETS) {
            Button pb = button(p[0]);
            pb.setOnClickListener(v -> {
                e1.setText(p[1]);
                e2.setText(p[2]);
            });
            pr.addView(pb);
        }
        Button rnd = button("\uD83C\uDFB2 Random");
        rnd.setOnClickListener(v -> {
            Random r = new Random();
            float h = r.nextFloat() * 360f;
            int a = Color.HSVToColor(new float[]{h, 0.8f, 0.95f});
            int b2 = Color.HSVToColor(new float[]{(h + 40f + r.nextFloat() * 90f) % 360f, 0.85f, 0.55f});
            e1.setText(hex(a));
            e2.setText(hex(b2));
        });
        pr.addView(rnd);

        Button reset = button("Reset to default");
        reset.setOnClickListener(v -> {
            e1.setText(hex(d.c1));
            e2.setText(hex(d.c2));
            e3.setText(hex(d.text));
            e4.setText(hex(d.accent));
            e5.setText(String.valueOf(d.angle));
            e6.setText(String.valueOf(d.corner));
            cb.setChecked(d.border);
            for (int i = 0; i < PATTERNS.length; i++) {
                if (PATTERNS[i].equals(d.pattern)) spin.setSelection(i);
            }
        });
        c.addView(reset);

        final Runnable save = () -> {
            Theme n = new Theme();
            n.c1 = parseColor(e1.getText().toString(), t.c1);
            n.c2 = parseColor(e2.getText().toString(), t.c2);
            n.text = parseColor(e3.getText().toString(), t.text);
            n.accent = parseColor(e4.getText().toString(), t.accent);
            n.angle = parseInt(e5.getText().toString(), t.angle) % 360;
            n.corner = Math.max(0, Math.min(60, parseInt(e6.getText().toString(), t.corner)));
            n.pattern = (String) spin.getSelectedItem();
            n.border = cb.isChecked();
            Theme.save(MainActivity.this, d, n);
            Renderer.updateAll(MainActivity.this);
            buildList();
        };

        new AlertDialog.Builder(this)
                .setTitle(d.label)
                .setView(sv)
                .setPositiveButton("Save", (dlg, which) -> {
                    save.run();
                    toast("Saved");
                })
                .setNeutralButton("Add to home", (dlg, which) -> {
                    save.run();
                    pin(d);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void pin(Styles.Def d) {
        AppWidgetManager m = AppWidgetManager.getInstance(this);
        if (m.isRequestPinAppWidgetSupported()) {
            m.requestPinAppWidget(new ComponentName(this, d.provider), null, null);
        } else {
            toast("Long-press home screen \u2192 Widgets \u2192 " + d.label);
        }
    }
}
