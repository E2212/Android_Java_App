package com.apep.cleaningbuddy.models;

import android.content.Context;

import com.apep.cleaningbuddy.R;

import java.util.Arrays;

public enum Interval {
    DAILY(R.string.interval_daily_text),
    WEEKLY(R.string.interval_weekly_text),
    CUSTOM(R.string.interval_custom_text);

    private final int resourceId;

    Interval(int stringResource) {
        this.resourceId = stringResource;
    }

    public static Interval getType(int intervalAmount) {
        Interval interval;
        if (intervalAmount == 1) {
            interval = Interval.DAILY;
        } else if (intervalAmount == 7) {
            interval = Interval.WEEKLY;
        } else {
            interval = Interval.CUSTOM;
        }

        return interval;
    }

    public int getResourceId() {
        return resourceId;
    }

    public static Interval fromResourceId(int resourceId) {
        for (Interval interval : Interval.values()) {
            if (interval.getResourceId() == resourceId) {
                return interval;
            }
        }
        return null;
    }

    public static int[] getResourceIds() {
        return Arrays.stream(values())
                .mapToInt(Interval::getResourceId)
                .toArray();
    }

    public static String[] getDisplayNames(Context context) {
        return Arrays.stream(values())
                .map(interval -> context.getString(interval.getResourceId()))
                .toArray(String[]::new);
    }

    public static int getPosition(Context context, Interval interval) {
        String[] displayNames = getDisplayNames(context);
        String targetName = context.getString(interval.getResourceId());

        for (int i = 0; i < displayNames.length; i++) {
            if (displayNames[i].equals(targetName)) {
                return i;
            }
        }
        return -1;
    }
}
