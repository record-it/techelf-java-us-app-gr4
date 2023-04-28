package pl.us.spring.gr4app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.us.spring.gr4app.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
