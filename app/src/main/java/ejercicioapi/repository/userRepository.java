package ejercicioapi.repository;

import ejercicioapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


public interface userRepository extends CrudRepository<User, Long> {
     User findByEmail(String email);

}
