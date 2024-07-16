package app.netlify.bugbank.utils;

import app.netlify.bugbank.security.SecureProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryOperation {

    public static void createFolder(String nameFolder) {
        Path directoryPath = Paths.get("src/test/resources/" + nameFolder);
        try {
            Files.createDirectory(directoryPath);
        } catch (IOException e) {
            LoggerFactory.log_WARNING("Falha ao criar a pasta '" + directoryPath + "' ou a pasta já existe.");
            throw new RuntimeException(e);
        }
    }

    public static void deleteDirectoryPath(String nameFolder) {
        String directoryPath = "src/test/resources/" + nameFolder;
        File directory = new File(directoryPath);
        if (directory.exists()) {
            deleteDirectory(directory);
        } else {
            LoggerFactory.log_WARNING("A pasta '" + directoryPath + "' não existe ou não é um diretório.");
        }
    }

    private static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    public static void OperationFolder() {
        deleteDirectoryPath("dataUser");
        createFolder("dataUser");
        SecureProperties.createProperties();
    }
}