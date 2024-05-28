package com.example.helpme.Controllers;

import com.example.helpme.Models.Point;
import com.example.helpme.Repositories.PointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PointController {
    @Autowired
    private PointRepo pointRepo;
    private Double x,y,r;
    private String username;
    private boolean Hitis;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/showPoint")
    public ResponseEntity<Collection<Point>> PointShow(@RequestBody Point point) {
        Collection<Point> mypoints = pointRepo.findByUsername(point.getUsername());
        System.out.println("email: " + point.getUsername() + point.getX() + point.getY() + point.getR());
        this.x = point.getX();
        this.y = point.getY();
        this.r = point.getR();
        this.username = point.getUsername();
        this.Hitis = isHited(point.getX(), point.getY(), point.getR());

        return ResponseEntity.ok(mypoints);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/PointAdd")
    public ResponseEntity<Collection<Point>> PointAdd(@RequestBody Point point) {
        boolean isHit = isHited(point.getX(), point.getY(), point.getR());
        Point po = new Point(point.getX(), point.getY(), point.getR(), isHit, point.getUsername());

        // Устанавливаем значение isHit перед сохранением
        po.setHit(isHit);
        System.out.println("Before save: " + point.toString());
        // Ваши значения x и y теперь должны быть установлены в объекте Point
        pointRepo.save(po);

        Collection<Point> points = pointRepo.findByUsername(po.getUsername());
        System.out.println("Добавлена точка с юзернеймом " + point.getUsername());
        this.x = po.getX();
        this.y = po.getY();
        this.r = po.getR();
        this.username = po.getUsername();
        this.Hitis = isHit;
        return ResponseEntity.ok(points);
    }

    public boolean isHited(Double x, Double y, Double z) {
        return ((x <= 0 && y >= 0 && (y - 2 * x <= r)) ||
                (x >= 0 && y >= 0 && y <= r && x <= r) ||
                (x >= 0 && y <= 0 && (x * x + y * y <= (r / 2) * (r / 2))));
    }
}