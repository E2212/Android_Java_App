package com.apep.cleaningbuddy.utils;

import android.content.Context;
import com.apep.cleaningbuddy.R;

import java.util.ArrayList;
import java.util.List;

public class InputValidations {

    private final List<String> errors = new ArrayList<>();
    private final Context context;

    public InputValidations(Context context) {
        this.context = context;
    }

    public boolean validateLength(String data, String fieldName, int minLength) {
        if (data == null || data.trim().length() < minLength) {
            errors.add(String.format(context.getString(R.string.error_length_text), fieldName, minLength));
            return false;
        }
        return true;
    }

    public boolean validateNumeric(String data, String fieldName) {
        if (data == null || !data.matches("\\d+")) {
            errors.add(String.format(context.getString(R.string.error_numeric_text), fieldName));
            return false;
        }
        return true;
    }

    public boolean validateNotEmpty(String data, String fieldName) {
        if (data == null || data.trim().isEmpty()) {
            errors.add(String.format(context.getString(R.string.error_required_text), fieldName));
            return false;
        }
        return true;
    }

    public boolean validatePasswordStrength(String data, String fieldName) {
        boolean isValid = true;
        if (data == null || data.trim().isEmpty() || (data.length() < 6 || data.length() > 16 || data.contains(" "))) {
            errors.add(String.format(context.getString(R.string.error_password_min_length_text), fieldName, 6));
            errors.add(String.format(context.getString(R.string.error_password_max_length_text), fieldName, 16));
            errors.add(String.format(context.getString(R.string.error_password_no_spaces_text), fieldName));
            isValid = false;
        }
        if (data == null || data.trim().isEmpty() || (!data.matches(".*[A-Z].*") || !data.matches(".*[a-z].*") || !data.matches(".*\\d.*") || !data.matches(".*[!@#$%^&*()].*"))) {
            errors.add(String.format(context.getString(R.string.error_password_strength_text), fieldName));
            isValid = false;
        }
        return isValid;
    }

    public String getErrors() {
        StringBuilder errorMessage = new StringBuilder();
        if (errors.size() == 1) {
            errorMessage.append(errors.get(0));
        } else {
            boolean isFirstLine = true;
            for (String error : errors) {
                if (isFirstLine) {
                    errorMessage.append("- ").append(error);
                    isFirstLine = false;
                } else {
                    errorMessage.append("\n- ").append(error);
                }
            }
        }
        errors.clear();
        return errorMessage.toString();
    }
}
