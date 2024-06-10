package com.apep.cleaningbuddy.utils;

import android.content.Context;
import com.apep.cleaningbuddy.R;

public class MethodsValidations {

    private String error;
    private Context context;

    public MethodsValidations(Context context) {
        this.context = context;
    }

    public boolean validateLength(String data, String fieldName, int minLength) {
        if (data == null || data.length() < minLength) {
            error = String.format(context.getString(R.string.error_length), fieldName, minLength);
            return false;
        }
        return true;
    }

    public boolean validateNumeric(String data, String fieldName) {
        if (data == null || !data.matches("\\d+")) {
            error = String.format(context.getString(R.string.error_numeric), fieldName);
            return false;
        }
        return true;
    }

    public boolean validateNotEmpty(String data, String fieldName) {
        if (data == null || data.trim().isEmpty()) {
            error = String.format(context.getString(R.string.error_required), fieldName);
            return false;
        }
        return true;
    }

    public boolean validatePasswordStrength(String data, String fieldName) {
        if (data == null || data.trim().isEmpty()) {
            error = String.format(context.getString(R.string.error_required), fieldName);
            return false;
        }
        if (data.length() < 6) {
            error = String.format(context.getString(R.string.error_password_min_length), fieldName, 6);
            return false;
        }
        if (data.length() > 16) {
            error = String.format(context.getString(R.string.error_password_max_length), fieldName, 16);
            return false;
        }
        if (data.contains(" ")) {
            error = String.format(context.getString(R.string.error_password_no_spaces), fieldName);
            return false;
        }
        if (!data.matches(".*[A-Z].*") || !data.matches(".*[a-z].*") || !data.matches(".*\\d.*") || !data.matches(".*[!@#$%^&*()].*")) {
            error = String.format(context.getString(R.string.error_password_strength), fieldName);
            return false;
        }
        return true;
    }

    public String getError() {
        String currentError = null;
        if (error != null) {
            currentError = error;
            error = null;
        }

        return currentError;
    }
}
