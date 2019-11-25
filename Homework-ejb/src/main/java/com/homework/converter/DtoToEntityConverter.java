/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.converter;

import com.homework.dto.HeadMasterDto;
import com.homework.dto.SchoolDto;
import com.homework.entity.HeadMaster;
import com.homework.entity.School;


public class DtoToEntityConverter {
    public static School convertSchoolDtoToSchool(SchoolDto schoolDto) {
        School school = new School();
        school.setSchoolName(schoolDto.getSchoolName());
        school.setAddress(schoolDto.getAddress());
        school.setHeadMaster(convertHeadMasterDtoToHeadMaster(schoolDto.getHeadMaster()));
        return school;
    }
    
    public static HeadMaster convertHeadMasterDtoToHeadMaster(HeadMasterDto headMasterDto) {
        HeadMaster headMaster = new HeadMaster();
        headMaster.setName(headMasterDto.getName());
        headMaster.setAge(headMasterDto.getAge());
        headMaster.setEducation(headMasterDto.getEducation());
        return headMaster;
    }
}
