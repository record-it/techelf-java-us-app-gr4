package pl.us.spring.gr4app.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.us.spring.gr4app.model.Book;
import pl.us.spring.gr4app.model.Comment;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBookDto {
    private long id;
    private String title;
    private String author;
    private int commentCount;

    public static ResponseBookDto of(Book book){
        return ResponseBookDto
                .builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .commentCount(book.getComments().size())
                .build();
    }
}
