package com.jsuszynski.library.database;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileFinder {

    public static final String FILE_ERROR = "Problem z dostępem do pliku";
    private final File file = new File("resources/database.json");


    public File findFile() {

        try {
            initRepository();
        } catch (IOException e) {
            System.out.println(FILE_ERROR);
        }
        return file;
    }

    private void initRepository() throws IOException {

        if (!file.exists()) {
            file.createNewFile();
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print("[\n]");
            printWriter.close();
        }
    }

    public static void main(String[] args) {
        FileFinder fileFinder = new FileFinder();
        fileFinder.findFile();
    }
}
