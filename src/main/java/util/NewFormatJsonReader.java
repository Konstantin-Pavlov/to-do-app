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
import java.util.Map;

/**
 * how to read:
 *        Map<String, List<Task>> mapTasks = NewFormatJsonReader.loadNewFormatJson("data/tasksNewFormat.json");
 * //        System.out.println(mapTasks);
 *         assert mapTasks != null;
 *         List<Task> tasks = mapTasks.get("tasks");
 *         tasks.forEach(System.out::println);
 */

public class NewFormatJsonReader {
    private static final GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    private static final Gson gson = gsonBuilder.setPrettyPrinting().create();
    private static Path path;

    private NewFormatJsonReader() {
    }

    public static Map<String, List<Task>> loadNewFormatJson(String filePath) {

        path = Paths.get(filePath);
        try {
            String str = Files.readString(path);
            return gson.fromJson(str, new TypeToken<Map<String, List<Task>>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void saveNewFormatJson(Map<String, List<Task>> tasks, String filePath) {
        path = Paths.get(filePath);
        String json = gson.toJson(tasks);

        byte[] bytes = json.getBytes();
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
