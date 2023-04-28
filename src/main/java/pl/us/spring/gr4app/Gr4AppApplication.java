package pl.us.spring.gr4app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.us.spring.gr4app.model.Book;
import pl.us.spring.gr4app.model.Comment;
import pl.us.spring.gr4app.repository.BookRepository;
import pl.us.spring.gr4app.repository.CommentRepository;

@SpringBootApplication
public class Gr4AppApplication implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public Gr4AppApplication(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }


    public static void main(String[] args) {

        SpringApplication.run(Gr4AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() > 0) {
            return;
        }
        var book = Book
                .builder()
                .author("Bloch")
                .editionYear(2021)
                .title("Java")
                .build();
        bookRepository.save(book);
        var comment = Comment
                .builder()
                .author("Adam")
                .content("Super")
                .book(book)
                .rating(8)
                .build();
        commentRepository.save(comment);
    }
}
