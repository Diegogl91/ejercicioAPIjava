package ejercicioapi.controller;

import ejercicioapi.model.User;
import ejercicioapi.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;



import java.util.*;

@RestController
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

    @GetMapping("/{id}")
    public ResponseEntity<?> consultarUsuario(@PathVariable("id") Long id){
        Optional<User> user = service.findById(id);
        if(user.isEmpty()){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user.get());
    }

    @PostMapping("/crearUsuario")
     public ResponseEntity<?> crearUsuario(@RequestBody User user){
        User emailUser = service.findByEmail(user.getemail());
        if(emailUser != null){
            return new ResponseEntity<>("El usuario ya existe",HttpStatus.BAD_REQUEST);
        }if(!service.validarEmail(user.getemail())){
            return new ResponseEntity<>("Formato de correo incorrecto",HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(service.save(user),HttpStatus.CREATED);

    }
//ResponseEntity.status(HttpStatus.CREATED).body(usuario);
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
