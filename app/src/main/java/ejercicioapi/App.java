/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ejercicioapi;

import ejercicioapi.controller.userController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(userController.class, args);

    }
}
