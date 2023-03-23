package ejercicioapi.controller;

import ejercicioapi.model.User;
import ejercicioapi.service.userService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
//@RequestMapping("/usuarios")
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

//    @PostMapping("user")
//    public user login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
//
//        String token = getJWTToken(username);
//        user user = new user();
//        user.setname(username);
//        user.setToken(token);
//        return user;
//
//    }
//
//    private String getJWTToken(String username) {
//        String secretKey = "mySecretKey";
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("ROLE_USER");
//
//        String token = Jwts
//                .builder()
//                .setId("softtekJWT")
//                .setSubject(username)
//                .claim("authorities",
//                        grantedAuthorities.stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .collect(Collectors.toList()))
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 600000))
//                .signWith(SignatureAlgorithm.HS512,
//                        secretKey.getBytes()).compact();
//
//        return "Bearer " + token;
//    }


}
