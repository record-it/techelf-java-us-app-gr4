package pl.us.spring.gr4app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.us.spring.gr4app.model.AppUser;
import pl.us.spring.gr4app.model.Book;
import pl.us.spring.gr4app.model.Comment;
import pl.us.spring.gr4app.repository.BookRepository;
import pl.us.spring.gr4app.repository.CommentRepository;
import pl.us.spring.gr4app.repository.UserRepository;

@SpringBootApplication
public class Gr4AppApplication implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public Gr4AppApplication(BookRepository bookRepository, CommentRepository commentRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
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
        AppUser user = AppUser
                .builder()
                .email("adam@us.edu.pl")
                .password(encoder.encode("1234"))
                .roles("USER,ADMIN")
                .build();
        userRepository.save(user);
    }
}
