/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.dto;

import com.homework.entity.Student;
import java.util.List;

/**
 *
 * @author sujan
 */
public class SchoolDto {
    private Long id;
    private String schoolName;
    private String address;
    private List<Student> students;
    private HeadMasterDto headMaster;

    public SchoolDto() {
    }
    
    public SchoolDto(HeadMasterDto headMaster) {
        this.headMaster = headMaster;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public HeadMasterDto getHeadMaster() {
        return headMaster;
    }

    public void setHeadMaster(HeadMasterDto headMaster) {
        this.headMaster = headMaster;
    }

}
