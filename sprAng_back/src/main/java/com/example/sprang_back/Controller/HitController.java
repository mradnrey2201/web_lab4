package com.example.sprang_back.Controller;

import com.example.sprang_back.Model.Client;
import com.example.sprang_back.Model.Hit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HitController {

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/email")
    public ResponseEntity<?> Hits(@RequestBody Hit hit){
        System.out.println("Ответ на сервере&&& "+ isHit(hit.getX(), hit.getY(), hit.getR()));

        return ResponseEntity.ok( isHit(hit.getX(), hit.getY(), hit.getR()));
    }
public boolean isHit(Double x, Double y, Double r){


        return ((x <= 0 && y >= 0 && (y - 2 * x <= r)) ||
            (x >= 0 && y >= 0 && y <= r && x <= r) ||
            (x >= 0 && y <= 0 && (x * x + y * y <= (r / 2) * (r / 2))))
            ;
}

}
