package pl.us.spring.gr4app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.us.spring.gr4app.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
