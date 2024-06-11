package com.apep.cleaningbuddy.models;

import android.content.Context;

import androidx.annotation.StringRes;

import com.apep.cleaningbuddy.R;

import java.util.Arrays;

public enum Theme {
    LIGHT(R.string.theme_light),
    DARK(R.string.theme_dark);

    private final int resourceId;

    Theme(@StringRes int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public static Theme fromResourceId(int resourceId) {
        for (Theme theme : Theme.values()) {
            if (theme.getResourceId() == resourceId) {
                return theme;
            }
        }
        return null;
    }

    public static int[] getResourceIds() {
        return Arrays.stream(values())
                .mapToInt(Theme::getResourceId)
                .toArray();
    }

    public static String[] getDisplayNames(Context context) {
        return Arrays.stream(values())
                .map(language -> context.getString(language.getResourceId()))
                .toArray(String[]::new);
    }

    public static int getPosition(Context context, Theme theme) {
        String[] displayNames = getDisplayNames(context);
        String targetName = context.getString(theme.getResourceId());

        for (int i = 0; i < displayNames.length; i++) {
            if (displayNames[i].equals(targetName)) {
                return i;
            }
        }
        return -1;
    }
}