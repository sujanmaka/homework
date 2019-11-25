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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sujan
 */
public class EntityToDtoConverter {
    public static SchoolDto convertSchoolToSchoolDto(School school) {
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setId(school.getId());
        schoolDto.setSchoolName(school.getSchoolName());
        schoolDto.setAddress(school.getAddress());
//        schoolDto.setSchoolName(school.getSchoolName());
        return schoolDto;
    }
    
    public static List<SchoolDto> convertSchoolsToSchoolDtos(List<School> schools) {
        List<SchoolDto> schoolDtos = new ArrayList<>();
        for (School school : schools) {
            schoolDtos.add(convertSchoolToSchoolDto(school));
        }
        return schoolDtos;
    }
    
    public static HeadMasterDto convertHeadMasterToDto(HeadMaster headMaster) {
        HeadMasterDto headMasterDto = new HeadMasterDto();
        headMasterDto.setId(headMaster.getId());
        headMasterDto.setName(headMaster.getName());
        headMasterDto.setAge(headMaster.getAge());
        headMasterDto.setEducation(headMaster.getEducation());
        return headMasterDto;
    } 
    
    public static List<HeadMasterDto> convertHeadMasterToDtos(List<HeadMaster> headMasters) {
        List<HeadMasterDto> headMasterDtos = new ArrayList<>();
        for (HeadMaster headMaster : headMasters) {
            headMasterDtos.add(convertHeadMasterToDto(headMaster));
        }
        return headMasterDtos;
    }
}
