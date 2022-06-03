package com.model;

import javax.persistence.*;

@Entity
@Table(name = "Personne")
public class Personne extends BaseModel {

    @Column(name = "Nom")
    private String Nom;

    @Column(name = "Prenom")
    private String Prenom;

    @Column(name = "Age")
    private int age;


    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
