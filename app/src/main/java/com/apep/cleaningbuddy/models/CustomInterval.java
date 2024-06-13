package com.apep.cleaningbuddy.models;

import android.content.Context;

import com.apep.cleaningbuddy.R;

import java.util.Arrays;

public enum CustomInterval {
    DAYS(R.string.custom_interval_days_text),
    WEEKS(R.string.custom_interval_weeks_text),
    MONTHS(R.string.custom_interval_months_text);

    private final int resourceId;

    CustomInterval(int stringResource) {
        this.resourceId = stringResource;
    }

    public static CustomInterval getType(int intervalAmount) {
        CustomInterval customInterval;
        if (intervalAmount % 30 == 0) {
            customInterval = CustomInterval.MONTHS;
        } else if (intervalAmount % 7 == 0) {
            customInterval = CustomInterval.WEEKS;
        } else {
            customInterval = CustomInterval.DAYS;
        }

        return customInterval;
    }

    public static int getCustomIntervalAmount(Integer intervalAmount) {
        int amount;
        if (intervalAmount % 30 == 0) {
            amount = intervalAmount / 30;
        } else if (intervalAmount % 7 == 0) {
            amount = intervalAmount / 7;
        } else {
            amount = intervalAmount;
        }

        return amount;
    }

    public int getResourceId() {
        return resourceId;
    }

    public static CustomInterval fromResourceId(int resourceId) {
        for (CustomInterval customInterval : CustomInterval.values()) {
            if (customInterval.getResourceId() == resourceId) {
                return customInterval;
            }
        }
        return null;
    }

    public static int[] getResourceIds() {
        return Arrays.stream(values())
                .mapToInt(CustomInterval::getResourceId)
                .toArray();
    }

    public static String[] getDisplayNames(Context context) {
        return Arrays.stream(values())
                .map(customInterval -> context.getString(customInterval.getResourceId()))
                .toArray(String[]::new);
    }

    public static int getPosition(Context context, CustomInterval customInterval) {
        String[] displayNames = getDisplayNames(context);
        String targetName = context.getString(customInterval.getResourceId());

        for (int i = 0; i < displayNames.length; i++) {
            if (displayNames[i].equals(targetName)) {
                return i;
            }
        }
        return -1;
    }
}
