package pl.us.spring.gr4app.model;

// zdefiniuj klasę komentarza książki:
// id - identyfikator komentarza (long)
// bookId - identyfikator książki (long)
// content - treść komentarza
// author - nazwa autora komentarza
// rating - ocena w postaci liczby od 1 do 10 (byte)
// dodaj adnotacje lombok

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// W klasie RestBookController
// utwórz listę komentarzy z dwoma wpisami
// zdefiniuj metodę zwracającą wszystkie komentarza
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long id;

    private Book book;

    private String author;

    private String content;

    private int rating;
}
