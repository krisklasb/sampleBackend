package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:4200")
@EnableMethodSecurity
public class Controller {


    //Authentication Prozess im Hintergrund gibt bei alles ok 200 oder bei nicht ok 401. Deshalb muss die jwt validierungs Logik eig. in keinen Endpoint
    //Hier würde also ausreichen die Rollen des Users zurückzugeben
    @GetMapping("/checkAccess")
    ResponseEntity<String> checkAccess() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
