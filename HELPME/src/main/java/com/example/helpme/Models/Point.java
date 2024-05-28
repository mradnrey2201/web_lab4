package com.example.helpme.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "points")
public class Point {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

    @Column(name = "x")
    private Double x;
    @Column(name = "y")
    private Double y;
    @Column(name = "r")
    private Double r;
    @Column(name = "isHit")
    private boolean isHit;



    @Column(name="username")
    private String username;

    public Point(Double x, Double y, Double r, boolean isHit, String username) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.username = username;
    }

    public Boolean getHit() {
        return isHit;
    }

    public void setHit(Boolean hit) {
        isHit = hit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Point() {

    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", isHit=" + isHit +
                ", username='" + username + '\'' +
                '}';
    }
}

