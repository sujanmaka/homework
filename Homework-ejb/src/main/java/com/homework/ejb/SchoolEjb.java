/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.ejb;

import com.homework.dto.HeadMasterDto;
import com.homework.dto.SchoolDto;
import com.homework.entity.HeadMaster;
import com.homework.entity.School;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface SchoolEjb {
    public List<SchoolDto> getAllSchools();
    public School getSchoolById(Long id);
    public SchoolDto saveSchool(SchoolDto schoolDto);
    public SchoolDto mergeSchool(SchoolDto schoolDto);
    public void deleteSchool(SchoolDto schoolDto);
    public SchoolDto editSchool(SchoolDto schoolDto);
    public HeadMasterDto getHeadMasterDto(Long id);
    public void saveHeadMaster(HeadMaster headMaster);
    public List<HeadMaster> getAllHeadMasters();
    public String executeComplexQuery1(String s);
    public String executeComplexQuery2(String address, String education, String query);
}
