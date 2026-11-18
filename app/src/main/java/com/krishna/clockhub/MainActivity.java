package com.krishna.clockhub;

import android.app.Activity;
import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.Random;

public class MainActivity extends Activity {
    static final String UPI_ID = "krishnakantvishwakrma396@okhdfcbank";
    static final String IG_URL = "https://www.instagram.com/krishnaxvis/";
    static final String[] PATTERNS = {"none", "grid", "dots", "scan", "stars"};
    static final String[][] PRESETS = {
        {"Sunset","#FF512F","#DD2476"},{"Ocean","#2193B0","#6DD5ED"},{"Neon","#0A0014","#FF2BD6"},
        {"Matrix","#000000","#00B140"},{"Purple","#7F00FF","#E100FF"},{"Fire","#F12711","#F5AF19"},
        {"Mint","#43E97B","#38F9D7"},{"Midnight","#0F2027","#2C5364"},{"Cyber","#FCEE0A","#FF003C"},
        {"Candy","#FF9A9E","#FAD0C4"},{"Royal","#141E30","#243B55"},{"Toxic","#0A0A0A","#3A4A00"}
    };
    private LinearLayout list, root;
    private float dens;
    private int dp(float v){ return Math.round(v*dens); }
    private int color(String s,int fallback){ try{return Color.parseColor(s);}catch(Exception e){return fallback;} }

    @Override public void onCreate(Bundle b){
        super.onCreate(b);
        dens=getResources().getDisplayMetrics().density;
        getWindow().setStatusBarColor(0xFF101016);
        getWindow().setNavigationBarColor(0xFF101016);
        getWindow().getDecorView().setSystemUiVisibility(0);
        ScrollView scroll=new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(0xFF101016);
        root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20),dp(22),dp(20),dp(32));
        scroll.addView(root);
        setContentView(scroll);
        buildHeader();
        buildSectionTitle();
        list=new LinearLayout(this); list.setOrientation(LinearLayout.VERTICAL); root.addView(list);
        buildList();
        addFooter();
    }

    private TextView txt(String s,int size,int c,boolean bold){
        TextView t=new TextView(this);t.setText(s);t.setTextSize(size);t.setTextColor(c);
        if(bold)t.setTypeface(android.graphics.Typeface.create("sans-serif",android.graphics.Typeface.BOLD));
        t.setIncludeFontPadding(false);return t;
    }
    private GradientDrawable shape(int a,int b,int radius){
        GradientDrawable d=new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{a,b});
        d.setCornerRadius(dp(radius));return d;
    }
    private TextView pill(String s){
        TextView t=txt(s,12,0xFFE5D9FF,true);t.setGravity(Gravity.CENTER);
        t.setPadding(dp(14),dp(9),dp(14),dp(9));t.setBackground(shape(0xFF292333,0xFF292333,40));return t;
    }
    private void buildHeader(){
        LinearLayout top=new LinearLayout(this);top.setGravity(Gravity.CENTER_VERTICAL);
        TextView mark=txt("K",22,0xFFFFFFFF,true);mark.setGravity(Gravity.CENTER);
        mark.setBackground(shape(0xFF7655D9,0xFFCE70B8,16));
        top.addView(mark,new LinearLayout.LayoutParams(dp(46),dp(46)));
        LinearLayout names=new LinearLayout(this);names.setOrientation(LinearLayout.VERTICAL);
        names.setPadding(dp(12),0,0,0);
        names.addView(txt("KRISHNA  /  CLOCK HUB",11,0xFFB8A6E8,true));
        names.addView(txt("Your time. Your style.",13,0xFF8F8B9C,false));
        top.addView(names,new LinearLayout.LayoutParams(0,-2,1));
        TextView count=pill(Styles.ALL.size()+" STYLES");top.addView(count);
        root.addView(top);
        TextView title=txt("Make time\nlook beautiful.",34,0xFFF7F4FF,true);
        title.setPadding(0,dp(30),0,dp(8));root.addView(title);
        root.addView(txt("Personalise your home screen with carefully crafted clock widgets.",14,0xFFAAA5B7,false));
        LinearLayout actions=new LinearLayout(this);actions.setPadding(0,dp(20),0,dp(22));
        TextView support=pill("♡  Support creator");support.setOnClickListener(v->showSupport());
        TextView settings=pill("⚙  Preferences");settings.setOnClickListener(v->showPreferences());
        actions.addView(support);LinearLayout.LayoutParams sp=new LinearLayout.LayoutParams(-2,-2);sp.leftMargin=dp(8);actions.addView(settings,sp);
        root.addView(actions);
        LinearLayout banner=new LinearLayout(this);banner.setOrientation(LinearLayout.VERTICAL);banner.setPadding(dp(18),dp(18),dp(18),dp(18));
        banner.setBackground(shape(0xFF30234C,0xFF4B2854,22));
        banner.addView(txt("DESIGN STUDIO",10,0xFFD8BFFF,true));
        TextView b=txt("Tap any style to customise colours, gradients, patterns and more.",15,0xFFFFFFFF,true);
        b.setPadding(0,dp(7),0,0);banner.addView(b);root.addView(banner);
    }
    private void buildSectionTitle(){
        LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
        TextView h=txt("Explore widgets",22,0xFFF4F0FA,true);
        row.addView(h,new LinearLayout.LayoutParams(0,-2,1));
        row.addView(txt("25+ DESIGNS",10,0xFF9C8CB7,true));
        row.setPadding(0,dp(26),0,dp(14));root.addView(row);
    }
    private void buildList(){
        list.removeAllViews();
        for(final Styles.Def d:Styles.ALL){
            final Theme t=Theme.load(this,d);
            LinearLayout card=new LinearLayout(this);card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(dp(16),dp(15),dp(16),dp(15));
            card.setBackground(shape(0xFF1B1A23,0xFF211D2B,22));
            LinearLayout head=new LinearLayout(this);head.setGravity(Gravity.CENTER_VERTICAL);
            head.addView(txt(d.label,16,0xFFF5F0FF,true),new LinearLayout.LayoutParams(0,-2,1));
            head.addView(txt(d.cellsW+" × "+d.cellsH+"   ↗",11,t.accent,true));card.addView(head);
            FrameLayout preview=new FrameLayout(this);
            preview.setBackground(shape(t.c1,t.c2,Math.max(12,t.corner)));
            preview.setPadding(dp(12),dp(14),dp(12),dp(14));
            LinearLayout pv=new LinearLayout(this);pv.setGravity(Gravity.CENTER);pv.setOrientation(LinearLayout.VERTICAL);
            TextView sample=txt("09:41",34,t.text,true);sample.setLetterSpacing(.02f);pv.addView(sample);
            pv.addView(txt("WEDNESDAY  ·  18 NOV",9,t.accent,true));
            preview.addView(pv,new FrameLayout.LayoutParams(-1,-1));
            LinearLayout.LayoutParams pp=new LinearLayout.LayoutParams(-1,dp(d.cellsH==1?78:116));pp.topMargin=dp(13);card.addView(preview,pp);
            LinearLayout bottom=new LinearLayout(this);bottom.setGravity(Gravity.CENTER_VERTICAL);
            TextView custom=txt("✦  CUSTOMIZE DESIGN",10,0xFFD0B5FF,true);
            bottom.addView(custom,new LinearLayout.LayoutParams(0,-2,1));
            bottom.addView(txt("Edit  →",12,0xFFBDB4CE,true));
            bottom.setPadding(0,dp(14),0,0);card.addView(bottom);
            card.setOnClickListener(v->showEditor(d));
            LinearLayout.LayoutParams cp=new LinearLayout.LayoutParams(-1,-2);cp.bottomMargin=dp(13);list.addView(card,cp);
        }
    }
    private void addFooter(){
        TextView f=txt("MADE WITH ♥ BY KRISHNA  ·  © 2026",10,0xFF777282,true);
        f.setGravity(Gravity.CENTER);f.setPadding(0,dp(24),0,dp(8));root.addView(f);
    }
    private EditText field(LinearLayout p,String label,String value,boolean number){
        TextView l=txt(label,12,0xFFBEB6CD,true);l.setPadding(0,dp(12),0,dp(5));p.addView(l);
        EditText e=new EditText(this);e.setSingleLine(true);e.setText(value);e.setTextColor(0xFFFFFFFF);
        e.setHintTextColor(0xFF777282);e.setPadding(dp(12),dp(7),dp(12),dp(7));
        e.setBackground(shape(0xFF292632,0xFF292632,12));
        if(number)e.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
        p.addView(e,new LinearLayout.LayoutParams(-1,dp(48)));return e;
    }
    private String hex(int c){return String.format("#%06X",c&0xFFFFFF);}
    private int parseColor(String s,int fallback){return color(s,fallback);}
    private int parseInt(String s,int fallback){try{return Integer.parseInt(s.trim());}catch(Exception e){return fallback;}}

    private void showEditor(final Styles.Def d){
        final Theme old=Theme.load(this,d);
        ScrollView scroll=new ScrollView(this);
        LinearLayout c=new LinearLayout(this);c.setOrientation(LinearLayout.VERTICAL);c.setPadding(dp(18),dp(12),dp(18),dp(18));scroll.addView(c);
        TextView intro=txt("Create a look that feels like yours.",14,0xFFB9B0C9,false);c.addView(intro);
        TextView live=txt("09:41",36,old.text,true);live.setGravity(Gravity.CENTER);
        live.setBackground(shape(old.c1,old.c2,old.corner));LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-1,dp(100));lp.topMargin=dp(14);c.addView(live,lp);
        HorizontalScrollView hs=new HorizontalScrollView(this);LinearLayout presets=new LinearLayout(this);hs.addView(presets);c.addView(hs);
        EditText e1=field(c,"GRADIENT START  ·  HEX",hex(old.c1),false);
        EditText e2=field(c,"GRADIENT END  ·  HEX",hex(old.c2),false);
        EditText e3=field(c,"CLOCK TEXT COLOUR",hex(old.text),false);
        EditText e4=field(c,"ACCENT COLOUR",hex(old.accent),false);
        EditText e5=field(c,"GRADIENT ANGLE  ·  0–359°",String.valueOf(old.angle),true);
        EditText e6=field(c,"CORNER RADIUS  ·  0–60",String.valueOf(old.corner),true);
        TextView patternLabel=txt("BACKGROUND PATTERN",12,0xFFBEB6CD,true);patternLabel.setPadding(0,dp(14),0,dp(5));c.addView(patternLabel);
        Spinner spin=new Spinner(this);spin.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PATTERNS));
        for(int i=0;i<PATTERNS.length;i++)if(PATTERNS[i].equals(old.pattern))spin.setSelection(i);c.addView(spin);
        CheckBox border=new CheckBox(this);border.setText("Show accent border");border.setTextColor(0xFFECE7F5);border.setButtonTintList(android.content.res.ColorStateList.valueOf(0xFFB58CFF));border.setChecked(old.border);c.addView(border);
        Runnable preview=()->{int a=parseColor(e1.getText().toString(),old.c1),b=parseColor(e2.getText().toString(),old.c2);live.setBackground(shape(a,b,parseInt(e6.getText().toString(),old.corner)));live.setTextColor(parseColor(e3.getText().toString(),old.text));};
        e1.setOnFocusChangeListener((v,f)->{if(!f)preview.run();});e2.setOnFocusChangeListener((v,f)->{if(!f)preview.run();});e3.setOnFocusChangeListener((v,f)->{if(!f)preview.run();});e6.setOnFocusChangeListener((v,f)->{if(!f)preview.run();});
        for(String[] p:PRESETS){TextView chip=pill(p[0]);chip.setOnClickListener(v->{e1.setText(p[1]);e2.setText(p[2]);preview.run();});LinearLayout.LayoutParams x=new LinearLayout.LayoutParams(-2,-2);x.rightMargin=dp(7);presets.addView(chip,x);}
        TextView random=pill("✧ Random");random.setOnClickListener(v->{Random r=new Random();float h=r.nextFloat()*360; e1.setText(hex(Color.HSVToColor(new float[]{h,.8f,.95f})));e2.setText(hex(Color.HSVToColor(new float[]{(h+70)%360,.8f,.65f})));preview.run();});presets.addView(random);
        Button reset=new Button(this);reset.setText("Reset style defaults");reset.setAllCaps(false);c.addView(reset);
        reset.setOnClickListener(v->{e1.setText(hex(d.c1));e2.setText(hex(d.c2));e3.setText(hex(d.text));e4.setText(hex(d.accent));e5.setText(""+d.angle);e6.setText(""+d.corner);border.setChecked(d.border);for(int i=0;i<PATTERNS.length;i++)if(PATTERNS[i].equals(d.pattern))spin.setSelection(i);preview.run();});
        AlertDialog dialog=new AlertDialog.Builder(this).setTitle("Customize · "+d.label).setView(scroll)
            .setPositiveButton("Save changes",null).setNeutralButton("Add to home",null).setNegativeButton("Cancel",null).create();
        dialog.setOnShowListener(v->{
            dialog.getButton(-1).setOnClickListener(w->{Theme n=readTheme(old,e1,e2,e3,e4,e5,e6,spin,border);Theme.save(this,d,n);Renderer.updateAll(this);buildList();Toast.makeText(this,"Design saved",Toast.LENGTH_SHORT).show();dialog.dismiss();});
            dialog.getButton(-3).setOnClickListener(w->{Theme n=readTheme(old,e1,e2,e3,e4,e5,e6,spin,border);Theme.save(this,d,n);Renderer.updateAll(this);buildList();pin(d);dialog.dismiss();});
        });dialog.show();
    }
    private Theme readTheme(Theme old,EditText a,EditText b,EditText tx,EditText ac,EditText an,EditText cr,Spinner sp,CheckBox bo){
        Theme n=new Theme();n.c1=parseColor(a.getText().toString(),old.c1);n.c2=parseColor(b.getText().toString(),old.c2);
        n.text=parseColor(tx.getText().toString(),old.text);n.accent=parseColor(ac.getText().toString(),old.accent);
        n.angle=((parseInt(an.getText().toString(),old.angle)%360)+360)%360;n.corner=Math.max(0,Math.min(60,parseInt(cr.getText().toString(),old.corner)));
        n.pattern=(String)sp.getSelectedItem();n.border=bo.isChecked();return n;
    }
    private void pin(Styles.Def d){
        AppWidgetManager m=AppWidgetManager.getInstance(this);
        if(m.isRequestPinAppWidgetSupported())m.requestPinAppWidget(new ComponentName(this,d.provider),null,null);
        else Toast.makeText(this,"Long press home screen → Widgets → "+d.label,Toast.LENGTH_LONG).show();
    }
    private void showPreferences(){
        String[] items={"Reset all widget designs", "About & Support", "Open Instagram"};
        new AlertDialog.Builder(this).setTitle("App preferences").setItems(items,(d,w)->{
            if(w==0) new AlertDialog.Builder(this).setTitle("Reset all designs?")
                .setMessage("All widget styles will return to their original colours and settings.")
                .setNegativeButton("Cancel",null).setPositiveButton("Reset",(a,b)->{
                    getSharedPreferences("cfg",MODE_PRIVATE).edit().clear().apply();
                    Renderer.updateAll(this);buildList();Toast.makeText(this,"All designs reset",Toast.LENGTH_SHORT).show();
                }).show();
            else if(w==1)showSupport();else openInstagram();
        }).show();
    }
    private void showSupport(){
        LinearLayout box=new LinearLayout(this);box.setOrientation(LinearLayout.VERTICAL);box.setPadding(dp(20),dp(10),dp(20),dp(10));
        TextView ig=txt("Instagram  ·  @krishnaxvis",16,0xFFBFA0FF,true);ig.setPadding(0,dp(10),0,dp(14));ig.setOnClickListener(v->openInstagram());box.addView(ig);
        box.addView(txt("Support the creator",14,0xFFFFFFFF,true));
        box.addView(txt("UPI ID: "+UPI_ID,13,0xFFBEB6CD,false));
        Button pay=new Button(this);pay.setText("Buy me a coffee · Other UPI apps");pay.setAllCaps(false);pay.setOnClickListener(v->pay(null));box.addView(pay);
        Button gpay=new Button(this);gpay.setText("Google Pay");gpay.setAllCaps(false);gpay.setOnClickListener(v->pay("com.google.android.apps.nbu.paisa.user"));box.addView(gpay);
        Button phonepe=new Button(this);phonepe.setText("PhonePe");phonepe.setAllCaps(false);phonepe.setOnClickListener(v->pay("com.phonepe.app"));box.addView(phonepe);
        Button paytm=new Button(this);paytm.setText("Paytm");paytm.setAllCaps(false);paytm.setOnClickListener(v->pay("net.one97.paytm"));box.addView(paytm);
        Button copy=new Button(this);copy.setText("Copy UPI ID");copy.setAllCaps(false);copy.setOnClickListener(v->{((android.content.ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("UPI",UPI_ID));Toast.makeText(this,"UPI ID copied",Toast.LENGTH_SHORT).show();});box.addView(copy);
        new AlertDialog.Builder(this).setTitle("About Krishna Clock Hub").setView(box).setPositiveButton("Close",null).show();
    }
    private void openInstagram(){
        try{startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(IG_URL)));}
        catch(Exception e){Toast.makeText(this,"Could not open Instagram",Toast.LENGTH_SHORT).show();}
    }
    private void pay(String pkg){
        Uri uri=Uri.parse("upi://pay?pa="+UPI_ID+"&pn=Krishna&cu=INR&tn=Buy%20me%20a%20coffee");
        Intent i=new Intent(Intent.ACTION_VIEW,uri);
        if(pkg!=null){i.setPackage(pkg);try{startActivity(i);return;}catch(Exception ignored){i.setPackage(null);}}
        try{startActivity(Intent.createChooser(i,"Pay with"));}catch(Exception e){Toast.makeText(this,"No UPI app found",Toast.LENGTH_SHORT).show();}
    }
}
