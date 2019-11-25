/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Sujan Maka
 */
@Entity
@Table(name = "user_faculties")
public class Faculties implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "faculty")
    private String faculty;
    
    @ManyToOne
    @JoinColumn(name = "user_fk")
    private Student user_faculties;

    public Faculties() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Student getUser_faculties() {
        return user_faculties;
    }

    public void setUser_faculties(Student user_faculties) {
        this.user_faculties = user_faculties;
    }

    @Override
    public String toString() {
        return faculty ;
    }
    
    
    
}
