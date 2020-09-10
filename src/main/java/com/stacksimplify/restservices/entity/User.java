package com.stacksimplify.restservices.entity;

//import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

//Entity
@ApiModel(description = "This model is to create a user")
@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"firstname","lastname"})
//@JsonFilter(value = "userFilter")
public class User extends RepresentationModel<User> {
    @ApiModelProperty(notes="Auto Generated Value", required = true,position = 1)
    @Id
    @GeneratedValue
    @JsonView(Views.External.class)
    private Long userid;

    @ApiModelProperty(notes="UserName should be in formate flname",example = "sperumal", position = 2)
    @JsonView(Views.External.class)
    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "Username is Mandatory field. Please provide username")
    private String username;
    @ApiModelProperty(notes = "First name of the User.", example = "sabarinathan", position = 3)
    @JsonView(Views.External.class)
    @NotEmpty(message = "FirstName is Mandatory field. Please provide FirstName")
    @Size(min=2,max = 50, message="FirstName should have atleast 2 characters")
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstname;
    @JsonView(Views.External.class)
    @NotEmpty(message = "LastName is Mandatory field. Please provide LastName")
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastname;
    @JsonView(Views.External.class)
    @NotEmpty(message = "Username is Mandatory field. Please provide Email")
    @Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
    private String email;
    @JsonView(Views.Internal.class)
    @Column(name = "ROLE", length = 50, nullable = false)
    private String role;
    @ApiModelProperty(notes = "SSN of the User.", example = "SSN1010", required = true, position = 4)
    @JsonView(Views.Internal.class)
    @Column(name = "SSN", length = 50, nullable = false, unique = true)
  //  @JsonIgnore
    private String ssn;
    @Column(name="Address", length=50)
    private String address;

    @OneToMany(mappedBy = "user")
    @JsonView(Views.Internal.class)
    private List<Order> orders;
    // No Argument Constructor
    public User(){
    }

    // Fields Constructor

    public User(List<Link> initialLinks, Long userid, @NotEmpty(message = "Username is Mandatory field. Please provide username") String username, @NotEmpty(message = "FirstName is Mandatory field. Please provide FirstName") @Size(min = 2, message = "FirstName should have atleast 2 characters") String firstname, @NotEmpty(message = "LastName is Mandatory field. Please provide LastName") String lastname, @NotEmpty(message = "Username is Mandatory field. Please provide Email") String email, String role, String ssn, String address, List<Order> orders) {
        super(initialLinks);
        this.userid = userid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
