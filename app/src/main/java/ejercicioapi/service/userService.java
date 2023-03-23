package ejercicioapi.service;

import ejercicioapi.model.User;

import java.util.Optional;

public interface userService {

    Optional<User> findById(Long Id);

    User save(User user);

    void deleteById(Long id);
}
