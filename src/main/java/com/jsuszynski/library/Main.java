package com.jsuszynski.library;

public class Main {


    public static void main(String[] args) {
        //default path is "resources/file.json"
        try {
            String path = args[0];
            new LibraryApp().run(path);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Podaj ścieżkę do pliku w parametrze uruchamiania.");
        }
    }
}
