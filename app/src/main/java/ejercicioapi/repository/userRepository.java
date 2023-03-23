package ejercicioapi.repository;

import ejercicioapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface userRepository extends CrudRepository<User, Long> {
     User findByEmail(String email);

}
