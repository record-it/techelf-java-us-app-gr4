package pl.us.spring.gr4app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.us.spring.gr4app.model.Book;
import pl.us.spring.gr4app.model.Comment;
import pl.us.spring.gr4app.repository.BookRepository;

import java.util.List;

@SpringBootApplication
public class Gr4AppApplication implements CommandLineRunner {
    private final BookRepository bookRepository;

    public Gr4AppApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(Gr4AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() > 0){
            return;
        }
        bookRepository.saveAll(
                List.of(
                        Book
                                .builder()
                                .author("Bloch")
                                .editionYear(2021)
                                .title("Java")
                                .comments(
                                        List.of(
                                                Comment
                                                        .builder()
                                                        .content("Super")
                                                        .author("Adam")
                                                        .rating(8)
                                                        .build(),
                                                Comment
                                                        .builder()
                                                        .content("Słaba")
                                                        .rating(2)
                                                        .author("Ewa")
                                                        .build()
                                        )
                                )
                                .build(),
                        Book
                                .builder()
                                .author("Freeman")
                                .editionYear(2022)
                                .title("ASP.NET")
                                .comments(
                                        List.of(
                                                Comment
                                                        .builder()
                                                        .content("Extra")
                                                        .author("Karol")
                                                        .rating(9)
                                                        .build(),
                                                Comment
                                                        .builder()
                                                        .content("Nuda")
                                                        .rating(3)
                                                        .author("Ewa")
                                                        .build()
                                        )
                                )
                                .build()
                )
        );
    }
}
