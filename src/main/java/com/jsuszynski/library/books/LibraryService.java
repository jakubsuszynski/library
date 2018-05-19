package com.jsuszynski.library.books;

import com.jsuszynski.library.arguments.ArgumentInterpreter;
import com.jsuszynski.library.arguments.ArgumentsValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class LibraryService {

    private static final String WRONG_PARAMS = "Niepoprawne parametry";

    private final BookRepository bookRepository = BookRepository.getInstance();
    private final ArgumentInterpreter argumentInterpreter = new ArgumentInterpreter();
    private final ArgumentsValidator argumentsValidator = new ArgumentsValidator();




    public void findBook() {
        System.out.println("Podaj parametr w formacie: -T<tytul>, -I<ISBN> lub -A<autor>");


        Map<String, String> args = argumentInterpreter.parseArguments();

        if (argumentsValidator.findingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }
        if (args.containsKey("-T")) {
            System.out.println(bookRepository.findByTitle(args.get("-T")));
        } else if (args.containsKey("-I")) {
            System.out.println(bookRepository.findByIsbn(args.get("-I")));
        } else if (args.containsKey("-A")) {
            for (Book book : bookRepository.findByAuthor(args.get("-A"))) {
                System.out.println(book);
            }
        }

    }

//    public void findBooksNotLentLately() {
//        System.out.println("Podaj ilość tygodni: ");
//        int weeks = scanner.nextInt();
//
//        for (Book book : notLent(weeks)) {
//            System.out.println(book);
//        }
//    }

//    public void lendBook() {
//        System.out.println("Podaj parametry w formacie: -W<imie i nazwisko wypożyczającego> i -T<tytul> lub -I<ISBN> " +
//                "Wyrazy parametrów oddzielaj myślnikiem");
//
//        String[] params = scanner.nextLine().split(" ");
//        Book book = null;
//        String reader = "";
//        for (String param : params) {
//            if (param.startsWith("-T")) {
//                book = bookRepository.findByTitle(param.substring(2));
//            } else if (param.startsWith("-I")) {
//                book = bookRepository.findByIsbn(param.substring(2));
//            } else if (param.startsWith("-W")) {
//                reader = param.substring(2);
//            } else
//                throw new RuntimeException(WRONG_PARAMS);
//        }
//
//        Book lentBook = new BookBuilder().withAuthor(book.getAuthor()).withIsbn(book.getIsbn())
//                .withTitle(book.getTitle())
//                .withState(true)
//                .withLastLending(LocalDate.now())
//                .withLastReader(reader)
//                .buildLentBook();
//
//        bookRepository.removeBook(book);
//        bookRepository.addBook(lentBook);
//    }
//
//
//    public void returnBook() {
//        System.out.println("Podaj parametr w formacie: -T<tytul> lub -I<ISBN>");
//        String param = scanner.nextLine();
//
//        if (param.startsWith("-T")) {
//            Book book = bookRepository.findByTitle(param.substring(2));
//            Book returnedBook = new BookBuilder().buildReturnedBook(book);
//            bookRepository.removeBook(book);
//            bookRepository.addBook(returnedBook);
//
//
//        } else if (param.startsWith("-I")) {
//            Book book = bookRepository.findByIsbn(param.substring(2));
//            Book returnedBook = new BookBuilder().buildReturnedBook(book);
//            bookRepository.removeBook(book);
//            bookRepository.addBook(returnedBook);
//        } else {
//            throw new RuntimeException(WRONG_PARAMS);
//        }
//    }

    public void returnReaders() {


        Map<String, Long> readers = bookRepository.getAllBooks().stream()
                .filter(Book::getIsLent)
                .collect(groupingBy(Book::getLastReader, counting()));


        for (Map.Entry<String, Long> reader : readers.entrySet())
            System.out.println(reader.getKey() + ": " + reader.getValue() + " książek");
    }

    public List<Book> notLent(int weeks) {
        return bookRepository.getAllBooks().stream()
                .filter(s -> s.getLastLending().isBefore(LocalDate.now().minusWeeks(weeks)))
                .collect(Collectors.toList());
    }


}
