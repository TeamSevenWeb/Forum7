package com.example.forum.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    private String username;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "date_of_registration")
    private LocalDateTime dateOfRegistration;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_posts",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "post_id")
//    )
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "createdBy",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Post> userPosts;

@JsonManagedReference
@JsonIgnore
@OneToMany(mappedBy = "createdBy",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
)
    private Set<Comment> userComments;

    @JsonManagedReference
    @JsonIgnore
    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private UserProfilePhoto profilePictures;

    @JsonIgnore
    @Column(name = "is_blocked")
    private boolean isBlocked;

    @JsonIgnore
    @Column(name = "is_admin")
    private boolean isAdmin;

    @JsonIgnore
    @Column(name = "is_active")
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Set<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(Set<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public Set<Comment> getUserComments() {
        return userComments;
    }

    public UserProfilePhoto getUserProfilePicture() {
        return profilePictures;
    }


    public void setUserComments(Set<Comment> userComments) {
        this.userComments = userComments;
    }

    public void setUserProfilePictures(UserProfilePhoto userProfilePictures) {
        this.profilePictures = userProfilePictures;
    }


    public boolean isBlocked() {
        return this.isBlocked;
    }
    public boolean isAdmin() {
        return this.isAdmin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean setIsAdmin(boolean isAdmin) {
        return this.isAdmin = isAdmin;
    }


    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean hasProfilePicture(){
        return profilePictures != null;
    }

    public Comment getUserLastComment(){
        int lastIndex = userComments.size();
        return userComments.stream().toList().get(lastIndex - 1);
    }

    public Post getUserLastPost(){
        int lastIndex = userPosts.size();
        return userPosts.stream().toList().get(lastIndex - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
