package com.example.sprang_back.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "points")
public class Hit {
    private @Id @GeneratedValue Long id;
    private Double x, y, r;




@Column(name = "username")
    private String username;
@ManyToOne
@JoinColumn(name = "username",referencedColumnName = "username")
private Client client;

    public Hit(Double x, Double y, Double r, String username) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.username = username;
    }
    public Hit(){}

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}



