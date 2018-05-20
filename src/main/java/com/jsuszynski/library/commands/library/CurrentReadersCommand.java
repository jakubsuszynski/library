package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.commands.Command;
import com.jsuszynski.library.console.DashReplacer;

import java.util.Map;

public class CurrentReadersCommand extends Command {

    private static final String RESPONSE = "%s Liczba ksiązek: %s ";

    @Override
    public void execute() {


        Map<String, Long> currentReaders = libraryService.returnCurrentReaders();

        for (Map.Entry<String, Long> reader : currentReaders.entrySet())

            System.out.println(DashReplacer
                    .deleteDash(String
                            .format(RESPONSE, reader.getKey(), reader.getValue())));
    }
}
