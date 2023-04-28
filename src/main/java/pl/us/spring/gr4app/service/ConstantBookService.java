package pl.us.spring.gr4app.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.us.spring.gr4app.model.Book;
import pl.us.spring.gr4app.model.Comment;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ConstantBookService implements BookService{
    @Override
    public List<Book> findAllBooks() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Book> findBookById(long id) {
        if (id != 1){
            return Optional.empty();
        }
        return Optional.of(Book.builder()
                .comments(Collections.emptyList())
                .id(1)
                .title("Constant book title").build());
    }

    @Override
    public Comment saveComment(Comment comment) {
        return null;
    }
}
