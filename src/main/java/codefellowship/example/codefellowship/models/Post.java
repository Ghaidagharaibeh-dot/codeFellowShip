package codefellowship.example.codefellowship.models;

import javax.persistence.*;


import javax.persistence.*;
@Entity
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String body;
    private String timestamp;

    @ManyToOne
    private ApplicationUser appUser;

    public Post(){}


    public Post(String body, String timestamp,  ApplicationUser appUser) {
        this.body = body;
        this.timestamp = timestamp;
        this.appUser = appUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ApplicationUser getAppUser() {
        return appUser;
    }

    public void setAppUser(ApplicationUser appUser) {
        this.appUser = appUser;
    }


}
