package com.apep.cleaningbuddy.models;
import android.content.Context;

import androidx.annotation.StringRes;

import com.apep.cleaningbuddy.R;

import java.util.Arrays;

public enum Language {
    DUTCH(R.string.language_dutch_text),
    ENGLISH(R.string.language_english_text);

    private final int resourceId;

    Language(@StringRes int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public static Language fromResourceId(int resourceId) {
        for (Language language : Language.values()) {
            if (language.getResourceId() == resourceId) {
                return language;
            }
        }
        return null;
    }

    public static int[] getResourceIds() {
        return Arrays.stream(values())
                .mapToInt(Language::getResourceId)
                .toArray();
    }

    public static String[] getDisplayNames(Context context) {
        return Arrays.stream(values())
                .map(language -> context.getString(language.getResourceId()))
                .toArray(String[]::new);
    }

    public static int getPosition(Context context, Language language) {
        String[] displayNames = getDisplayNames(context);
        String targetName = context.getString(language.getResourceId());

        for (int i = 0; i < displayNames.length; i++) {
            if (displayNames[i].equals(targetName)) {
                return i;
            }
        }
        return -1;
    }
}
