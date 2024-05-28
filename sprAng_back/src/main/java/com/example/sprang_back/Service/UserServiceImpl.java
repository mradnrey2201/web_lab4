package com.example.sprang_back.Service;

import com.example.sprang_back.Model.Client;
import com.example.sprang_back.Repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UserServiceImpl implements UserDetailsService {

    private static ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                client.getUsername(),
                client.getPswd(),
                new ArrayList<>()
        );
    }
    private static PasswordEncoder passwordEncoder;
    public void registerClient(Client client) {
        // Хеширование пароля перед сохранением в базу данных
        client.setPswd(passwordEncoder.encode(client.getPswd()));
        clientRepository.save(client);
    }





    public static void save(Client client) {
        // Хеширование пароля перед сохранением в базу данных
        client.setPswd(passwordEncoder.encode(client.getPswd()));
        clientRepository.save(client);
    }

    public Client findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }
}
