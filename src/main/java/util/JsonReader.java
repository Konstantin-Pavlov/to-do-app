package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class JsonReader {
    private JsonReader() {
    }
    public static <T> T[] loadJsonArray(String path, Class<T[]> cls) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        Path PATH = Paths.get(path);
        try {
            String str = Files.readString(PATH);
            return gson.fromJson(str, cls);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
