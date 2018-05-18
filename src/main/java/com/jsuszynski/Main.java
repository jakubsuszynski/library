package com.jsuszynski.library;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static BookRepository bookRepository = new BookRepository();
    public static void main(String[] args) {
        do {
            System.out.printf("Wybierz polecenie: " +
                    "\n 1. Dodanie książki" +
                    "\n 2. Usuniecie książki" +
                    "\n 3. Wyszukanie po nazwie, autorze lub ISBN" +
                    "\n 4. Wyszukanie książek niewypożyczonych od ostatnich x tygodni" +
                    "\n 5. Wypożyczenie książki" +
                    "\n 6. Lista aktualnych wypożyczających");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();

            switch (command){
                case "1":
                    System.out.println("Podaj tytul książki: ");
                    String title = scanner.nextLine();
                    System.out.println("Podaj autora książki: ");
                    String author = scanner.nextLine();
                    System.out.println("Podaj ISBN książki: ");
                    String isbn = scanner.nextLine();
                    bookRepository.addBook(new Book(title, author, isbn, "", LocalDate.MIN));
            }




        } while (true);
    }
}
