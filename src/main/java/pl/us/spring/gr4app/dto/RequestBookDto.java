package pl.us.spring.gr4app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.us.spring.gr4app.model.Book;

import java.io.Serializable;

@Data
public class RequestBookDto implements Serializable {
    @Length(min = 1, max = 50)
    private String title;

    @NotEmpty
    private String author;
    @Min(1990)
    private int editionYear;

    public Book withId(long id){
        return Book
                .builder()
                .id(id)
                .title(title)
                .editionYear(editionYear)
                .author(author)
                .build();
    }

    public Book of(){
        return Book
                .builder()
                .title(title)
                .editionYear(editionYear)
                .author(author)
                .build();
    }
}
