package pl.us.spring.gr4app.dto;

import jakarta.activation.CommandObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import pl.us.spring.gr4app.model.Comment;

/**
 * 1. Zdefiniować pola klasy:
 * - content
 * - author
 * - rating
 * 2. Zdefiniować metodę mapującą na klasę Comment
 * - withId(long id)
 * 3. Dodaj adnotacje Lombok
 * 4. Dodaj adnotacje walidujące
 * - rating w zakresie do 1 do 10
 * - content co najmiej 10 znaków i nie więcej od 2000
 * - author nie pusty i nie dłuży od 50 znaków
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommentDto {

    @Length(min = 10, max = 2000)
    private String content;

    @NotEmpty
    @Length(max = 50)
    private String author;

    @Min(1)
    @Max(10)
    private int rating;

    public Comment withId(long id) {
        return Comment
                .builder()
                .id(id)
                .author(author)
                .rating(rating)
                .content(content)
                .build();
    }

    public Comment of() {
        return Comment
                .builder()
                .author(author)
                .rating(rating)
                .content(content)
                .build();
    }
}
