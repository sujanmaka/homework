/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.homework.converter.EntityToDtoConverter;
import com.homework.dto.HeadMasterDto;
import com.homework.dto.NamesDto;
import com.homework.dto.SchoolDto;
import com.homework.ejb.SchoolEjb;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "schools")
@ApplicationScoped
public class SchoolsController {

    @EJB
    private SchoolEjb schoolEjb;
    private SchoolDto schoolDto;
    private HeadMasterDto headMasterDto;
    private String exceptions;
    private String query1;
    private String query2;
    private NamesDto namesDto;
    private String address;
    private String education;
    private String result1;
    private String result2;

    private List<SchoolDto> schoolList = new ArrayList();
    private List<HeadMasterDto> headMasterList = new ArrayList();

    @PostConstruct
    public void getAllStudent() {
        schoolDto = new SchoolDto(new HeadMasterDto());
        schoolList = schoolEjb.getAllSchools();
        result1 = "No result";
        result2 = "";
    }

    public String navigateToHeadmasterList() {
        headMasterList = EntityToDtoConverter.convertHeadMasterToDtos(schoolEjb.getAllHeadMasters());
        return "headMaster.xhtml?faces-redirect=true";
    }

    public String navigateToEditPage() {
        return "editSchool.xhtml?faces-redirect=true";
    }

    public String navigateToSchoolPage() {
        return "indexSchool.xhtml?faces-redirect=true";
    }

    public String editSchool() {

        try {
            schoolEjb.editSchool(schoolDto);
            schoolList = schoolEjb.getAllSchools();
            return "indexSchool.xhtml?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }

    }

    public String addSchool() {
        schoolEjb.saveSchool(schoolDto);
        schoolList = schoolEjb.getAllSchools();
        return "indexSchool.xhtml?faces-redirect=true";
    }

    public void removeExceptions() {
        exceptions = "";
        System.out.println("Exceptions remove" + getExceptions());

    }

    public String deleteSchool() {
        try {
            schoolEjb.deleteSchool(schoolDto);
            schoolList = schoolEjb.getAllSchools();
            schoolDto = new SchoolDto(new HeadMasterDto());
            return "indexSchool.xhtml?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public String viewHeadMasterInfo() {
        try {
            headMasterDto = schoolEjb.getHeadMasterDto(schoolDto.getId());
            return "headMasterInfo.xhtml?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public String complexQuery1() {
        result1 = schoolEjb.executeComplexQuery1(query1);
        if (result1.isEmpty()) {
            result1 = "No result, please enter appropriate data.";
        }
        return "indexComplexQuery.xhtml?faces-redirect=true";
    }

    public String complexQuery2() {

        result2 = schoolEjb.executeComplexQuery2(address, education, query2);
        if (result2.isEmpty()) {
            result2 = "No result, please enter appropriate data.";
        }
        return "indexComplexQuery.xhtml?faces-redirect=true";
    }

    public String getExceptions() {
        return exceptions;
    }

    public void setExceptions(String exceptions) {
        this.exceptions = exceptions;
    }

    public SchoolDto getSchoolDto() {
        return schoolDto;
    }

    public void setSchoolDto(SchoolDto schoolDto) {
        this.schoolDto = schoolDto;
    }

    public List<SchoolDto> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolDto> schoolList) {
        this.schoolList = schoolList;
    }

    public HeadMasterDto getHeadMasterDto() {
        return headMasterDto;
    }

    public void setHeadMasterDto(HeadMasterDto headMasterDto) {
        this.headMasterDto = headMasterDto;
    }

    public String getQuery1() {
        return query1;
    }

    public void setQuery1(String query1) {
        this.query1 = query1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getQuery2() {
        return query2;
    }

    public void setQuery2(String query2) {
        this.query2 = query2;
    }

    public List<HeadMasterDto> getHeadMasterList() {
        return headMasterList;
    }

    public void setHeadMasterList(List<HeadMasterDto> headMasterList) {
        this.headMasterList = headMasterList;
    }

}
