package ejercicioapi.repository;

import ejercicioapi.model.user;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface userRepository extends CrudRepository<user, UUID> {


}
