package com.jsuszynski.library.database;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.jsuszynski.library.books.Book;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class JsonOperator {
    private static final String FILE_ERROR = "Problem z dostępem do pliku";

    private final File file = new FileFinder().findFile();
    private final Gson gson = new Gson();

    public void addBook(Book book) {
        openArray();
        JsonWriter jsonWriter = null;
        try {

            jsonWriter = new JsonWriter(new FileWriter(file, true));
            gson.toJson(book, Book.class, jsonWriter);
        } catch (Exception e) {
            throw new RuntimeException(FILE_ERROR);
        } finally {
            closeJsonWriter(jsonWriter);
        }
        closeArray();


    }

    private void closeJsonWriter(JsonWriter jsonWriter) {
        if(jsonWriter!=null){
            try {
                jsonWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(FILE_ERROR);
            }
        }
    }

    private void closeArray() {
        FileWriter fileWriter = null;
        try {

            fileWriter = new FileWriter(file, true);
            fileWriter.write("\n]");
        } catch (Exception e) {
            throw new RuntimeException(FILE_ERROR);
        } finally {
            closeWriter(fileWriter);
        }
    }

    private void openArray() {
        BufferedWriter bufferedWriter = null;
        try {

            String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(content.substring(0, content.length() - 2));
        } catch (Exception e) {
            throw new RuntimeException(FILE_ERROR);
        } finally {
            closeWriter(bufferedWriter);
        }
    }

    private void closeWriter(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(FILE_ERROR);
            }
        }
    }


    public static void main(String[] args) {
        JsonOperator jsonOperator = new JsonOperator();
        jsonOperator.addBook(new Book("gdfgd", "sdfs", "234", "sdfs", LocalDate.now(), false));
    }
}
