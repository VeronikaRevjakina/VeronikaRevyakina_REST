package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtils {
    public static String readSpellarURLFromFile(String filePath) {
        Properties prop = new Properties();
        String url = "";

        try (InputStream input = new FileInputStream(filePath)) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            prop.load(input);

            url = prop.getProperty("spellar.URL");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
