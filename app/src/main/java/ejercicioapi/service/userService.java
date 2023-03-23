package ejercicioapi.service;

import ejercicioapi.model.User;

import java.util.Optional;

public interface userService {

    Optional<User> findById(Long Id);

    public User findByEmail(String email);

    User save(User user);

    boolean deleteById(Long id);

    boolean validateEmail(String email);
    public User modifyUser(Long id, User user);
}
