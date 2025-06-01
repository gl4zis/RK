package ru.itmo.rk.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResourceReader {
    public static String readFile(String filepath) throws IOException {
        ClassLoader classLoader = ResourceReader.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(filepath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + filepath);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
                return content.toString();
            }
        }
    }
}
