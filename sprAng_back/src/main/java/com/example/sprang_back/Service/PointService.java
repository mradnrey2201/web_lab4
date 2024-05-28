package com.example.sprang_back.Service;

import com.example.sprang_back.Model.Hit;
import com.example.sprang_back.Repository.PointRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PointService implements UserDetails
{

private final PointRepository pointRepository;
    @Autowired
    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Collection<Hit> findAllPointsByUsername(String username) {
        return pointRepository.findAllByUsername(username);
    }
    private Long id;
    private String username;

    @JsonIgnore
    private String pswd;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }





























//    private Long id;
//    private String username;
//    private String pswd;
//
//    // Хранилище клиентов
//    private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();
//
//
//    // Переменная для генерации ID клиента
//    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();
//
//    @Override
//    public void create(Client client) {
//        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
//        client.setPswd(clientId);
//        CLIENT_REPOSITORY_MAP.put(clientId, client);
//    }
//
//    @Override
//    public List<Client> readAll() {
//        return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
//    }
//
//    @Override
//    public Client read(int id) {
//        return CLIENT_REPOSITORY_MAP.get(id);
//    }
//
//    @Override
//    public boolean update(Client client, int id) {
//        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
//            client.setPswd(id);
//            CLIENT_REPOSITORY_MAP.put(id, client);
//            return true;
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean delete(int id) {
//        return CLIENT_REPOSITORY_MAP.remove(id) != null;
//    }
}
