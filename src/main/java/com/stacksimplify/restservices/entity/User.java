package com.stacksimplify.restservices.entity;

//import org.springframework.hateoas.ResourceSupport;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

//Entity
@Entity
@Table(name = "user")
public class User extends RepresentationModel<User> {
    @Id
    @GeneratedValue
    private Long userid;

    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "Username is Mandatory field. Please provide username")
    private String username;
    @NotEmpty(message = "FirstName is Mandatory field. Please provide FirstName")
    @Size(min=2,message="FirstName should have atleast 2 characters")
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstname;
    @NotEmpty(message = "LastName is Mandatory field. Please provide LastName")
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastname;
    @NotEmpty(message = "Username is Mandatory field. Please provide Email")
    @Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
    private String email;
    @Column(name = "ROLE", length = 50, nullable = false)
    private String role;

    @Column(name = "SSN", length = 50, nullable = false, unique = true)
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    // No Argument Constructor
    public User(){
    }

    // Fields Constructor
    public User(Long userid, @NotEmpty(message = "Username is Mandatory field. Please provide username") String username, @NotEmpty(message = "FirstName is Mandatory field. Please provide FirstName") @Size(min = 2, message = "FirstName should have atleast 2 characters") String firstname, @NotEmpty(message = "LastName is Mandatory field. Please provide LastName") String lastname, @NotEmpty(message = "Username is Mandatory field. Please provide Email") String email, String role, String ssn, List<Order> orders) {
        this.userid = userid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
        this.orders = orders;
    }


    // Getter and Setter


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
// To String
    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                ", orders=" + orders +
                '}';
    }
}
