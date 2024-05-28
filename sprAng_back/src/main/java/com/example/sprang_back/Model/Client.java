package com.example.sprang_back.Model;

//import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class Client {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String pswd;

    @Column(name = "username")
    private String username;




    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Client(String pswd, String username) {
        this.pswd = pswd;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }


public Client(){

}



}
