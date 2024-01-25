package com.combattale.utils;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

public class Storage {
    private static final String FILE_NAME = "storage.json";
    private static final JSONObject data;

    public static void setBool(String key, boolean value) {
        data.put(key, value);
    }

    public static void setInt(String key, int value) {
        data.put(key, value);
    }

    public static void setString(String key, String value) {
        data.put(key, value);
    }

    public static <K, V> void setMap(String key, Map<K, V> values) {
        data.put(key, values);
    }

    public static void setDouble(String key, double value) {
        data.put(key, value);
    }

    public static boolean getBool(String key, boolean defaultValue) {
        return data.optBoolean(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return data.optInt(key, defaultValue);
    }

    public static String getString(String key, String defaultValue) {
        return data.optString(key, defaultValue);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> getMap(String key) {
        Object obj = data.opt(key);
        if (obj instanceof JSONObject)
            return (Map<K, V>) ((JSONObject) obj).toMap();
        return Collections.emptyMap();
    }

    public static double getDouble(String key, double defaultValue) {
        return data.optDouble(key, defaultValue);
    }

    public static void commit() {
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            file.write(data.toString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ensureCreated() throws IOException {
        final Path path = Paths.get(FILE_NAME);
        if (Files.exists(path)) return;
        Files.createFile(path);
    }

    static {
        JSONObject json;
        try {
            ensureCreated();
            String content = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            json = new JSONObject(content.isEmpty() ? "{}" : content);
        } catch (IOException e) {
            json = new JSONObject();
        }
        data = json;
    }
}
