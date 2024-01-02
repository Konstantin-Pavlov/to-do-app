package interactive;

import java.io.File;

public class UserInteraction {
    public void showFileInDirectory() {
        // Get the absolute path of the root project directory
        String rootPath = new File("").getAbsolutePath();

        // Specify the folder name within the root project directory
        String folderName = "data";

        // Create a File object for the folder
        File folder = new File(rootPath + File.separator + folderName);

        // Get the list of files in the folder
        File[] files = folder.listFiles();

        // Iterate over the files and print their names
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("empty directory");
        }
    }
}
