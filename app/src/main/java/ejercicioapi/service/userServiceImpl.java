package ejercicioapi.service;

import ejercicioapi.model.User;
import ejercicioapi.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class userServiceImpl implements userService {

    public static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$");

    public static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).*$");
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
        user.setActive(true);
        user.setCreateAt(new Date());
        user.setUpdateAt(new Date());
        user.setlastLogin(new Date());
        return repository.save(user);
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            repository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean validateEmail(String email){
        return EMAIL_REGEX.matcher(email).matches();
    }

    @Override
    public boolean validatePassword(String pwd){
        return PASSWORD_REGEX.matcher(pwd).matches();
    }

    @Override
    public User modifyUser(Long id, User user){
        User userFind = repository.findById(id).get();
        userFind.setemail(user.getemail());
        userFind.setname(user.getname());
        userFind.setPhones(user.getPhones());
        userFind.setUpdateAt(new Date());
        return repository.save(userFind);
    }
}
