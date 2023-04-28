package pl.us.spring.gr4app.service;

import pl.us.spring.gr4app.model.Book;
import pl.us.spring.gr4app.model.Comment;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBooks();

    Optional<Book> findBookById(long id);

    Comment saveComment(Comment comment);
}
