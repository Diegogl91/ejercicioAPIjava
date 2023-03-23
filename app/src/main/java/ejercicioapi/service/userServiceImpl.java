package ejercicioapi.service;

import ejercicioapi.model.User;
import ejercicioapi.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class userServiceImpl implements userService {

    public static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$");
    @Autowired
    private userRepository repository;
    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

      @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean validarEmail(String email){
        if (EMAIL_REGEX.matcher(email).matches()) {
            return true;
        }return false;
    }
}
