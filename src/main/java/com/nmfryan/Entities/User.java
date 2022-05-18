package com.nmfryan.Entities;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) UUID id;

    private String firstName;

    private String secondName;

    private String email;


    public User(String firstName, String secondName, String email){
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public User() {

    }

    public UUID getId(){ return id; }

    public void setId(UUID id){ this.id = id; }

    public String getFirstName(){ return firstName;}

    public void setFirstName(String firstName){ this.firstName = firstName;}

    public String getSecondName(){ return secondName;}

    public void setSecondName(String secondName){ this.secondName = secondName;}

    public String getEmail(){ return email;}

    public void setEmail(){ this.email = email;}

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User person = (User) o;
        return Objects.equals(this.id, person.id) && Objects.equals(this.firstName, person.firstName)
                && Objects.equals(this.secondName, person.secondName)
                && Objects.equals(this.email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.secondName, this.email);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", secondName='" + this.secondName + '\'' + ", email='" + this.email + '\''+ '}';
    }



}
