package codefellowship.example.codefellowship.controllers;

import codefellowship.example.codefellowship.models.ApplicationUser;
import codefellowship.example.codefellowship.models.Post;
import codefellowship.example.codefellowship.repos.ApplicationUserRepo;
import codefellowship.example.codefellowship.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Controller
public class Index {

    @Autowired
    ApplicationUserRepo repo;

    @Autowired
    PostRepo postRepo;



    @GetMapping("/")
    public String getHome(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "index";
    }



    @GetMapping("/profile")
    public String getProfile(Model model,  Principal principal){
    ApplicationUser data =  repo.findByUsername(principal.getName());
    model.addAttribute("user", data);
        return "profile";
}



    @PostMapping("/post")
    public RedirectView addPost(Principal principal, String body) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date timeStamp = new Date();

        ApplicationUser theUser = repo.findByUsername(principal.getName());
        Post post = new Post(body, dateFormat.format(timeStamp), theUser);
        postRepo.save(post);
        return new RedirectView("/index");
    }



    @GetMapping("/index")
    public  String showPosts(Model model){
        List<Post> data = (ArrayList<Post>) postRepo.findAll();
        List<ApplicationUser> allUsers = (List<ApplicationUser>) repo.findAll();
        model.addAttribute("posts", data);
        model.addAttribute("users", allUsers);
        return "index";
    }




    @GetMapping("/users/{id}")
    public String getUserPage(Principal principal,Model model, @PathVariable int id){
            ApplicationUser user = repo.findUserById(id);
            ApplicationUser me = repo.findByUsername(principal.getName());
            model.addAttribute("userForOwner", user);
            model.addAttribute("me", me);
            return "users";

    }




    @PostMapping("/follow")
    public RedirectView follow(Principal principal, Model m, int id) {

        ApplicationUser currentUser = repo.findByUsername(principal.getName());
        ApplicationUser theUser = repo.findById(id).get();

        currentUser.getFriends().add(theUser);
        repo.save(currentUser);

        return new RedirectView("/users/" + theUser.getId());
    }




    @GetMapping(value = "/feed")
    public String getFeed(Model m, Principal p) {
        //get current user
        ApplicationUser currentUser = repo.findByUsername(p.getName());
        Set<ApplicationUser> followFriends = currentUser.getFriends();
        m.addAttribute("followingFriends", followFriends);
        //for the nav bar

        return "feed";
    }






    }


