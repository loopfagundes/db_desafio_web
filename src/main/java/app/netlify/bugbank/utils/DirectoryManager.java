package app.netlify.bugbank.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryManager {

    private static final String BASE_DIR = "src";

    public static void createFolder(String directory, String folderName) {
        Path folderPath = buildPath(directory, folderName);
        try {
            Files.createDirectories(folderPath);
            LoggerFactory.log_INFO("Pasta criada: " + folderPath);
        } catch (IOException e) {
            LoggerFactory.log_WARNING("Erro ao criar a pasta '" + folderPath + "': " + e.getMessage());
        }
    }

    public static void deleteDirectory(String directory, String folderName) {
        File folder = buildPath(directory, folderName).toFile();
        if (folder.exists()) {
            deleteRecursive(folder);
            LoggerFactory.log_INFO("Pasta deletada: " + folder.getAbsolutePath());
        } else {
            LoggerFactory.log_WARNING("A pasta '" + folder.getAbsolutePath() + "' não existe.");
        }
    }

    private static void deleteRecursive(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteRecursive(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }

    public static void resetFolder(String directory, String folderName) {
        deleteDirectory(directory, folderName);
        createFolder(directory, folderName);
    }

    private static Path buildPath(String directory, String folderName) {
        return Paths.get(BASE_DIR, directory, "resources", folderName);
    }
}