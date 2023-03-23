package ejercicioapi.service;

import ejercicioapi.model.User;

import java.util.Optional;

public interface userService {

    Optional<User> findById(Long Id);

    public User findByEmail(String email);

    User save(User user);

    void deleteById(Long id);

    boolean validarEmail(String email);
}
