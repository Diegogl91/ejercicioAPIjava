package ejercicioapi.controller;

import ejercicioapi.model
        .user;
import ejercicioapi.service.userService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class userController {

    @Autowired
    private userService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> consultarUsuario(@PathVariable Long id){
        Optional<user> user = service.findById(id);
        if(user.isEmpty()){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user.get());
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<?> crearUsuario(@RequestBody user user){
        user usuario = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

}
