package com.example.sprang_back.Controller;

import com.example.sprang_back.Model.Client;
import com.example.sprang_back.Service.ClientService;
import com.example.sprang_back.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {






@GetMapping("/")
public ResponseEntity<?> main(){
   return ResponseEntity.ok("LOOOOOL");
}


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Client());
        return "registration";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") Client user) {
        UserServiceImpl.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

//@GetMapping("/task")
//public ResponseEntity<?> task(){
//    return ResponseEntity.ok(new Client(2,"as"));
//}
@CrossOrigin(origins = "http://localhost:4200/")
@PostMapping("/task")
public ResponseEntity<Client> clientInfo(@RequestBody Client client){
    System.out.println("email: "+ client.getUsername());
    return ResponseEntity.ok(new Client("3","vasuka.com"+ client.getUsername()));
}
//тут сделать проверку логина по бд






}
