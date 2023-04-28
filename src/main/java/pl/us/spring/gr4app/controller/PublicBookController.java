package pl.us.spring.gr4app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.us.spring.gr4app.dto.RequestCommentDto;
import pl.us.spring.gr4app.dto.ResponseBookDto;
import pl.us.spring.gr4app.model.Book;
import pl.us.spring.gr4app.model.Comment;
import pl.us.spring.gr4app.service.BookService;
import pl.us.spring.gr4app.service.ConstantBookService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/public/books")
public class PublicBookController {
    private final BookService bookService;

    public PublicBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public List<ResponseBookDto> getBooks(){
        return bookService
                .findAllBooks()
                .stream()
                .map(book -> ResponseBookDto.of(book))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDto> getBook(@PathVariable long id){
        final Optional<Book> optionalBook = bookService.findBookById(id);
        if (optionalBook.isPresent()){
            return ResponseEntity.ok(ResponseBookDto.of(optionalBook.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // TODO zdefiniuj metodę zwracająca komentarze do książki o podanym id
    // /api/v1/public/books/1/comments

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable long id, @Valid @RequestBody RequestCommentDto dto){
        final Comment comment = dto.of();
        comment.setBook(Book.builder().id(id).build());
        return ResponseEntity
                .created(URI.create("/api/v1/public/books/"))
                .body(bookService.saveComment(comment));
    }
}
