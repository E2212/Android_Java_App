package com.apep.cleaningbuddy.utils;

public class ArrayUtils {
    public static byte[] combine(byte[] array1, byte[] array2) {
        byte[] combined = new byte[array1.length + array2.length];

        System.arraycopy(array1, 0, combined, 0, array1.length);
        System.arraycopy(array2, 0, combined, array1.length, array2.length);

        return combined;
    }
}
