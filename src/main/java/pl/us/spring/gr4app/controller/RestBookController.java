package pl.us.spring.gr4app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.us.spring.gr4app.dto.RequestBookDto;
import pl.us.spring.gr4app.dto.RequestCommentDto;
import pl.us.spring.gr4app.model.Book;
import pl.us.spring.gr4app.model.Comment;

import java.net.URI;
import java.util.*;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping("/api/v1/books")
public class RestBookController {
    public List<Book> books = new ArrayList<>(
            List.of(
                    Book
                            .builder()
                            .id(1)
                            .author("Bloch")
                            .title("Java")
                            .editionYear(2022)
                            .build(),
                    new Book(2, "Spring", "Pivotal", 2020),
                    Book
                            .builder()
                            .id(3)
                            .editionYear(2000)
                            .title("Java JEE2")
                            .build()
            )
    );

    public List<Comment> comments = new ArrayList<>(
            List.of(
                    Comment
                            .builder()
                            .content("ABC")
                            .rating(6)
                            .author("CDE")
                            .book(books.get(0))
                            .id(1)
                            .build(),
                    Comment
                            .builder()
                            .content("DEF")
                            .rating(9)
                            .author("TYU")
                            .book(books.get(0))
                            .id(2)
                            .build()
            )
    );
    int index = 3;
    int commentIndex = 2;

    @GetMapping("/favorite")
    public Map<String, Object> book() {
        Map<String, Object> book = new HashMap<>();
        book.put("title", "Java");
        book.put("authors", List.of("Bloch", "Freeman"));
        book.put("editionYear", 2022);
        return book;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getOneBook(@PathVariable long bookId) {
        final Optional<Book> any = books.stream().filter(book -> book.getId() == bookId).findAny();
        return ResponseEntity.of(any);
    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return books;
    }

    @PostMapping("/")
    public ResponseEntity<Book> addBook(@Valid @RequestBody RequestBookDto dto) {
        final Book book = dto.withId(++index);
        books.add(book);
        return ResponseEntity.created(URI.create("/api/v1/books/" + index)).body(book);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@PathVariable long bookId) {
        final Optional<Book> first = findBookById(bookId);
        if (first.isPresent()) {
            books.remove(first.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity updateBook(@RequestBody Book book, @PathVariable long bookId) {
        if (bookId != book.getId()) {
            return ResponseEntity.badRequest().build();
        }
        final Optional<Book> first = findBookById(bookId);
        if (first.isPresent()) {
            books.remove(first.get());
            books.add(book);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Optional<Book> findBookById(long bookId) {
        final Optional<Book> first = books.stream().filter(b -> b.getId() == bookId).findFirst();
        return first;
    }

    // zmodyfikuj metodę, aby przyjmowała RequestCommentDto
    @PostMapping("/{bookId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable long bookId, @RequestBody RequestCommentDto dto) {
        final Comment comment = dto.withId(++commentIndex);
        final Optional<Book> optionalBook = findBookById(bookId);
        if (optionalBook.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        comment.setBook(optionalBook.get());
        comments.add(comment);
        return ResponseEntity.created(URI.create("/api/v1/books/"+ bookId+"/comments/" + commentIndex)).body(comment);
    }

}
