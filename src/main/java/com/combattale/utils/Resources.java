package com.combattale.utils;

import  java.net.URL;

public class Resources {
    public static String getPath(Object context, String filename) {
        final ClassLoader loader = context.getClass().getClassLoader();
        final URL res = loader.getResource(filename);
        if (res == null) return null;
        return res.getPath();
    }
}
