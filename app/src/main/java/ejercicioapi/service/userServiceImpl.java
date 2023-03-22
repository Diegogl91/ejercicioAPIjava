package ejercicioapi.service;

import ejercicioapi.model.user;
import ejercicioapi.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userRepository repository;
    @Override
    public Optional<user> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public user save(user user) {
        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
