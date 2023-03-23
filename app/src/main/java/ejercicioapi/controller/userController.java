package ejercicioapi.controller;

import ejercicioapi.model.User;
import ejercicioapi.service.userService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor

public class userController {

    @Autowired
    private userService service;

    @GetMapping("/")
    public Map<String,Object> getStatus(){
        Map<String,Object> response = new HashMap<>();
        response.put("status",true);
        return response;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id, @RequestHeader("token") UUID token){
        Optional<User> user = service.findById(id);
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user.get());
    }

    @PostMapping("/crearUsuario")
     public ResponseEntity<?> createUser(@RequestBody User user){

        User emailUser = service.findByEmail(user.getemail());
        if(emailUser != null){
            return new ResponseEntity<>("El usuario ya existe",HttpStatus.BAD_REQUEST);
        }if(!service.validateEmail(user.getemail()) || !service.validatePassword(user.getpassword())){
            return new ResponseEntity<>("Formato de correo o contraseña incorrecto",HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(service.save(user),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable("id") Long id,@RequestBody User user,@RequestHeader("token") UUID token){
        return new ResponseEntity<>(service.modifyUser(id,user),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eraseUser(@PathVariable("id") Long id,@RequestHeader("token") UUID token){
        boolean response = service.deleteById(id);
        if(response){
            return  new ResponseEntity<>(HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
