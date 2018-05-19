package com.jsuszynski.library.commands.exit;

import com.jsuszynski.library.commands.Command;

public class ExitCommand extends Command {

    @Override
    public void execute() {
        System.out.println("Zakończono");
        System.exit(0);
    }
}
