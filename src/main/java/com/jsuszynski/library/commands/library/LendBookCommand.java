package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.arguments.Params;
import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;
import com.jsuszynski.library.console.DashReplacer;

import java.util.Map;

public class LendBookCommand extends Command {

    private static final String PROMPT = "Podaj parametry w formacie: " +
            "-W<imię i nazwisko wypożyczającego> i -T<tytuł> lub -I<ISBN>. " +
            "\nWyrazy parametrów oddzielaj myślnikiem a parametry - spacją.";
    private static final String BOOK_LENT = "Wypożyczono książkę pod tytułem %s, ISBN %s dla %s";

    @Override
    public void execute() {

        System.out.println(PROMPT);

        Map<Params, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.lendingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }

        if (args.containsKey(Params.T)) {
            Book book = libraryService.findBy(Book::getTitle, args.get(Params.T));
            lendIfAvailable(args, book);

        } else {
            Book book = libraryService.findBy(Book::getIsbn, args.get(Params.T));
            lendIfAvailable(args, book);
        }


    }

    private void lendIfAvailable(Map<Params, String> args, Book book) {
        if (book.isLent()) {
            throw new RuntimeException("Książka jest obecnie wypożyczona!");
        }
        swapBooks(args.get(Params.W), book);
    }

    private void swapBooks(String reader, Book book) {
        Book lentBook = book.lentBook(reader);

        System.out.println(DashReplacer
                .deleteDash(String.format(BOOK_LENT, lentBook.getTitle(), lentBook.getIsbn(), lentBook.getLastReader())));

        databaseService.deleteBook(book);
        databaseService.addBook(lentBook);
    }
}
