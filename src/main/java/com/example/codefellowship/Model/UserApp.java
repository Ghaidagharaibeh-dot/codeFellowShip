package com.example.codefellowship.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
@Entity
public class UserApp implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true)
    String username;
    String password;
    String firstName;
    String lastName;
    String dateOfBirth;
    String bio;
    @OneToMany(mappedBy = "applicationUser")
    List<Post> allposts;
    public UserApp(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }
    public UserApp(){

    }
    public List<Post> getAllposts() {
        return allposts;
    }
    public void setAllposts(List<Post> allposts) {
        this.allposts = allposts;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getFirstname() {
        return firstName;
    }
    public String getLastname() {
        return lastName;
    }
    public String getDateofbirth() {
        return dateOfBirth;
    }
    public String getBio() {
        return bio;
    }
    public Integer getId() {
        return id;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFirstName(String firstname){
        this.firstName = firstName;
    }
    public void setLastName(String lastname) {
        this.lastName = lastName;
    }
    public void setDateOfBirth(String dateofbirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
