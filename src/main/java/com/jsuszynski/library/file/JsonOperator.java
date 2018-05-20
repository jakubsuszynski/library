package com.jsuszynski.library.file;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jsuszynski.library.books.Book;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonOperator {
    private static final String FILE_ERROR = "Problem z dostępem do pliku";

    private final File file = new FileFinder().findFile();
    private final Gson gson = new Gson();
    private static JsonOperator jsonOperator;

    private JsonOperator() {
    }

    public static JsonOperator getInstance() {
        if (jsonOperator == null) {
            synchronized (JsonOperator.class) {
                if (jsonOperator == null)
                    jsonOperator = new JsonOperator();
            }
        }
        return jsonOperator;
    }


    public void addBook(Book book) {
        List<Book> allBooks = new ArrayList<>(getAllBooks());
        allBooks.add(book);

        performOperation(allBooks);
    }

    public void deleteBook(Book book) {
        List<Book> allBooks = new ArrayList<>(getAllBooks());
        allBooks.remove(book);

        performOperation(allBooks);
    }

    public List<Book> getAllBooks() {

        try (JsonReader jsonReader = new JsonReader(new FileReader(file))) {
            Book[] books = gson.fromJson(jsonReader, Book[].class);
            return new ArrayList<>(Arrays.asList(books));
        } catch (IOException e) {
            throw new RuntimeException(FILE_ERROR);
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }

    }

    private void performOperation(List<Book> books) {

        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(file))) {
            jsonWriter.setIndent(" ");
            gson.toJson(books, books.getClass(), jsonWriter);
        } catch (Exception e) {
            throw new RuntimeException(FILE_ERROR);
        }
    }

}
