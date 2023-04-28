package pl.us.spring.gr4app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.us.spring.gr4app.model.AppUser;
import pl.us.spring.gr4app.repository.UserRepository;

import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {
    private final UserRepository repository;

    public AppUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<AppUser> optionalAppUser = repository.findAppUserByEmail(email);
        if (optionalAppUser.isEmpty()){
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }
        return optionalAppUser.get();
    }
}
