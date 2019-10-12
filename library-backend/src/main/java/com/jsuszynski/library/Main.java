package com.jsuszynski.library;

import com.jsuszynski.library.domain.Book;
import com.jsuszynski.library.repositories.BooksRepository;
import com.jsuszynski.library.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class Main {
    @Autowired
    DatabaseService databaseService;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @Bean
    CommandLineRunner runner(){
        return args -> databaseService.addBook(new Book("Title", "Author"));
    }
}