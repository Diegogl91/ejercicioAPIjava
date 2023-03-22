package ejercicioapi.service;

import ejercicioapi.model.user;

import java.util.Optional;

public interface userService {

    Optional<user> findById(Long Id);

    user save(user user);

    void deleteById(Long Id);
}
