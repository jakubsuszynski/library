package com.jsuszynski.library.file;

public class FilePath {
    public static String getFilePath() {
        return FILE_PATH;
    }

    private static String FILE_PATH = "";


    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }

    private FilePath() {
    }
}
