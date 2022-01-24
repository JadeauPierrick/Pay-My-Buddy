package com.paymybuddy.application.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "The email is required")
    @Column(length = 100, unique = true)
    private String email;

    @NotNull(message = "The password is required")
    @Column(length = 100)
    private String password;

    @NotNull(message = "The first name is required")
    @Column(name = "first_name", length = 100)
    private String firstName;

    @NotNull(message = "The last name is required")
    @Column(name = "last_name", length = 100)
    private String lastName;

    @OneToOne(mappedBy = "user")
    private Account account;

    @ManyToMany
    @JoinTable(
            name = "connection",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "buddy_id")
    )
    private List<User> connections;

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.connections = new ArrayList<>();
    }

    public void addAccount(Account userAccount){
        this.account = userAccount;
        userAccount.setUser(this);
    }
}
