package com.jsuszynski.library.books;

import com.jsuszynski.library.arguments.ArgumentInterpreter;
import com.jsuszynski.library.arguments.ArgumentsValidator;

import java.util.Map;

public class DatabaseService {

    private static final String WRONG_PARAMS = "Niepoprawne parametry.";
    private static final String SPACING = "Wyrazy parametrów oddzielaj myślnikiem.";
    public static final String ADDING_PARAMS = "Podaj parametry w formacie: -T<tytul> -A<autor> -I<ISBN>. ";
    public static final String DELETING_PARAMS = "Podaj parametr w formacie: -T<tytul> lub -I<ISBN>. ";
    private final BookRepository bookRepository = new BookRepository();
    private final ArgumentInterpreter argumentInterpreter = new ArgumentInterpreter();
    private final ArgumentsValidator argumentsValidator = new ArgumentsValidator();


    public void addBook() {

        System.out.println(ADDING_PARAMS + SPACING);

        Map<String, String> args = argumentInterpreter.parseArguments();

        if (argumentsValidator.addingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }

        bookRepository.addBook(new BookBuilder()
                .withAuthor(args.get("-A"))
                .withIsbn(args.get("-I"))
                .withTitle(args.get("-T"))
                .buildBrandNewBook());
    }


    public void deleteBook() {
        System.out.println(DELETING_PARAMS + SPACING);

        Map<String, String> args = argumentInterpreter.parseArguments();

        if (argumentsValidator.deletingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }

        if (args.containsKey("-T")) {
            bookRepository.removeBook(bookRepository.findByTitle(args.get("-T")));
        } else {
            bookRepository.removeBook(bookRepository.findByIsbn(args.get("-I")));
        }

    }


}
