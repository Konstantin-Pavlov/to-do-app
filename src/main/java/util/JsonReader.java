package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class JsonReader {
    private static GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    private static Gson gson = gsonBuilder.setPrettyPrinting().create();

    private static final String path = "data/tasks.json";

    private JsonReader() {
    }

    public static List<Task> loadJsonArray() {

        Path PATH = Paths.get(path);
        try {
            String str = Files.readString(PATH);
            return gson.fromJson(str, new TypeToken<List<Task>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void writeFile(List<Task> tasks) {
        Path PATH = Paths.get(path);
        String json = gson.toJson(tasks);

        byte[] bytes = json.getBytes();
        try {
            Files.write(PATH, bytes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
