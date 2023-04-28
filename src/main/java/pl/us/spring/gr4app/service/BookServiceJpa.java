package pl.us.spring.gr4app.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.us.spring.gr4app.model.Book;
import pl.us.spring.gr4app.model.Comment;
import pl.us.spring.gr4app.repository.BookRepository;
import pl.us.spring.gr4app.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class BookServiceJpa implements BookService{
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public BookServiceJpa(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }
}
