package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class CurrentReadersCommand extends Command {
    @Override
    public void execute() {


        Map<String, Long> currentReaders = libraryService.returnCurrentReaders();

        for (Map.Entry<String, Long> reader : currentReaders.entrySet())
            System.out.println(reader.getKey() + ": " + reader.getValue() + " książek");
    }
}
