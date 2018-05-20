package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class ReturnBookCommand extends Command {


    private static final String PROMPT = "Podaj parametry w formacie: -T<tytul> -A<autor> -I<ISBN>. " +
            "\nWyrazy parametrów oddzielaj myślnikiem.";
    private static final String BOOK_RETURNED = "Zwrócono książkę o tytule %s, ISBN %s";

    @Override
    public void execute() {
        System.out.println(PROMPT);
        Map<String, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.returnBookParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }


        if (args.containsKey("-T")) {
            Book book = libraryService.findBy(Book::getTitle, args.get("-T"));
            swapBooks(book);

        } else {
            Book book = libraryService.findBy(Book::getIsbn, args.get("-I"));
            swapBooks(book);
        }

    }

    private void swapBooks(Book book) {
        Book returnedBook = book.returnedBook();
        System.out.println(String.format(BOOK_RETURNED, returnedBook.getTitle(), returnedBook.getIsbn()));
        databaseService.deleteBook(book);
        databaseService.addBook(returnedBook);
    }
}
