package com.jsuszynski.library.database;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jsuszynski.library.books.Book;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonOperator {
    private static final String FILE_ERROR = "Problem z dostępem do pliku";

    private final File file = new FileFinder().findFile();
    private final Gson gson = new Gson();

    public void addBook(Book book) {
        openArray();
        JsonWriter jsonWriter = null;
        try {

            jsonWriter = new JsonWriter(new FileWriter(file, true));
            jsonWriter.setIndent(" ");
            putComa();
            gson.toJson(book, Book.class, jsonWriter);

        } catch (Exception e) {
            throw new RuntimeException(FILE_ERROR);
        } finally {
            closeCloseable(jsonWriter);
        }
        closeArray();
    }

    public Book[] getAllBooks() {
        Book[] books;
        JsonReader jsonReader = null;
        try {
            jsonReader = new JsonReader(new FileReader(file));
            books = gson.fromJson(jsonReader, Book[].class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(FILE_ERROR);
        } finally {
            closeCloseable(jsonReader);
        }
        return books;
    }

    private void putComa() {
        FileWriter fileWriter = null;
        try {

            fileWriter = new FileWriter(file, true);
            fileWriter.write(",");
        } catch (Exception e) {
            throw new RuntimeException(FILE_ERROR);
        } finally {
            closeCloseable(fileWriter);
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
            closeCloseable(fileWriter);
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
            closeCloseable(bufferedWriter);
        }
    }

    private void closeCloseable(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException(FILE_ERROR);
            }
        }
    }


    public static void main(String[] args) {
        JsonOperator jsonOperator = new JsonOperator();
        for (Book book : jsonOperator.getAllBooks()) {
            System.out.println(book);
        }
//        jsonOperator.addBook(new Book("gdfgd", "sdfs", "234", "sdfs", LocalDate.now(), false));
    }
}
