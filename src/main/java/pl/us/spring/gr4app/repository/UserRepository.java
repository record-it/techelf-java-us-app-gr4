package pl.us.spring.gr4app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.us.spring.gr4app.model.AppUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByEmail(String email);
}
