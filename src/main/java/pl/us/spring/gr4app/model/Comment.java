package pl.us.spring.gr4app.model;

// zdefiniuj klasę komentarza książki:
// id - identyfikator komentarza (long)
// bookId - identyfikator książki (long)
// content - treść komentarza
// author - nazwa autora komentarza
// rating - ocena w postaci liczby od 1 do 10 (byte)
// dodaj adnotacje lombok

import jakarta.persistence.*;
import lombok.*;

// W klasie RestBookController
// utwórz listę komentarzy z dwoma wpisami
// zdefiniuj metodę zwracającą wszystkie komentarza

// Dodaj adnotacje JPA
@Getter@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @ManyToOne(targetEntity = Book.class, optional = false)
    private Book book;      // nie dodawaj adnotacji

    @Column(length = 50)
    private String author;

    @Column(length = 2000)
    private String content;

    private int rating;
}
