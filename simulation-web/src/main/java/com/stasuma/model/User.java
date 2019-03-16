package com.stasuma.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends Model{
    @Size(min = 3, max = 16, message = "Length of login must be between 3 and 20 characters")
    @Column(name = "login", unique = true, length = 20, nullable = false)
    private String login;

    @Size(min = 6, max = 16, message = "Length of password must be between 6 and 20 characters")
    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Size(min = 3, max = 16, message = "Length of first name must be between 3 and 20 characters")
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Size(min = 3, max = 16, message = "Length of first name must be between 3 and 20 characters")
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
