/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author MstfDryl
 */
@Entity(name = "student")
@NamedQueries({
    @NamedQuery(name = "allUsers", query = "SELECT u FROM student u")
    ,
    @NamedQuery(name = "getXUser", query = "SELECT u FROM student u WHERE u.userName = :username")
    ,
    @NamedQuery(name = "getXEmail", query = "SELECT u FROM student u WHERE u.email = :email")
})
public class Student implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 200, nullable = false)
    private String userName;

    @Column(name = "mobile", length = 255, nullable = false)
    private String mobile;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "faculty", length = 255, nullable = false)
    private String faculty;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user_faculties", orphanRemoval = true)
    private Set<Faculties> faculties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    public Student() {
    }

    public Student(Long id, String userName, String mobile, String email, String faculty, Set<Faculties> faculties) {
        this.id = id;
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.faculty = faculty;
        this.faculties = faculties;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Set<Faculties> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculties> faculties) {
        this.faculties = faculties;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
    
}
