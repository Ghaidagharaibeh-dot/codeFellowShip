package com.example.codefellowship.Contrller;

import com.example.codefellowship.Model.ApplicationUser;
import com.example.codefellowship.Model.Post;
import com.example.codefellowship.Repo.PostRepo;
import com.example.codefellowship.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class UserController {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostRepo postRepo;


    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String bio){
       ApplicationUser user=new ApplicationUser(username,encoder.encode(password),firstName,lastName,dateOfBirth,bio);
        userRepo.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "/login";
    }

    @GetMapping("/")
    public String getHomePge(){
        return"root";
    }

    @GetMapping("/profile")
    public String getprofile(Principal p, Model m){
       m.addAttribute("usernamePrincipal",p.getName());
        m.addAttribute("profile",userRepo.findByUsername(p.getName()));
        return "profile";
    }
    @PostMapping("/profile")
    public RedirectView postprofile(Principal p, @RequestParam String body){
        Post newpost= new Post(body,userRepo.findByUsername(p.getName()));
        postRepo.save(newpost);
        return new RedirectView("/profile");
    }
    

}
