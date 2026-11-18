package com.krishna.clockhub;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Bundle;

public abstract class BaseProvider extends AppWidgetProvider {
    protected abstract String key();

    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] ids) {
        Styles.Def d = Styles.get(key());
        for (int id : ids) {
            Renderer.update(context, manager, id, d);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager manager, int id, Bundle newOptions) {
        Renderer.update(context, manager, id, Styles.get(key()));
    }
}
