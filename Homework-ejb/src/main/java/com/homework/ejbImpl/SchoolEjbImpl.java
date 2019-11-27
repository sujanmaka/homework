/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.ejbImpl;

import com.homework.converter.DtoToEntityConverter;
import com.homework.converter.EntityToDtoConverter;
import com.homework.dto.HeadMasterDto;
import com.homework.dto.SchoolDto;
import com.homework.ejb.SchoolEjb;
import com.homework.entity.HeadMaster;
import com.homework.entity.School;
import com.homework.entity.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SchoolEjbImpl implements SchoolEjb {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SchoolDto saveSchool(SchoolDto schoolDto) {
        em.persist(DtoToEntityConverter.convertSchoolDtoToSchool(schoolDto));
        em.flush();
        return schoolDto;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SchoolDto mergeSchool(SchoolDto schoolDto) {
        em.merge(em.find(Student.class, schoolDto.getId()));
        return schoolDto;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteSchool(SchoolDto schoolDto) {
        try {
            Query q = em.createQuery("select s from School s where s.id=:id");
            q.setParameter("id", schoolDto.getId());
            School school = (School) q.getSingleResult();
            em.remove(school);
            em.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SchoolDto editSchool(SchoolDto schoolDto) {
        try {
            Query q = em.createQuery("select s from School s where s.id=:id");
            q.setParameter("id", schoolDto.getId());
            School school = (School) q.getSingleResult();
            school.setAddress(schoolDto.getAddress());
            school.setSchoolName(schoolDto.getSchoolName());
            em.merge(school);
            em.flush();
            return schoolDto;
        } catch (Exception e) {

            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        Query q = em.createQuery("select s from School s");
        List<School> schools = (List<School>) q.getResultList();
        return EntityToDtoConverter.convertSchoolsToSchoolDtos(schools);
    }

    @Override
    public HeadMasterDto getHeadMasterDto(Long id) {
        Query q = em.createQuery("Select s.headMaster from School s where s.id=:id");
        q.setParameter("id", id);
        HeadMaster headMaster = (HeadMaster) q.getSingleResult();
        return EntityToDtoConverter.convertHeadMasterToDto(headMaster);

    }

    @Override
    public School getSchoolById(Long id) {
        Query q = em.createQuery("Select s from School s where s.id=:id");
        q.setParameter("id", id);
        return (School) q.getSingleResult();
    }

    @Override
    public void saveHeadMaster(HeadMaster headMaster) {
        em.persist(headMaster);
        em.flush();
    }

    @Override
    public List<HeadMaster> getAllHeadMasters() {
        Query q = em.createQuery("Select h from HeadMaster h");
        return (List<HeadMaster>) q.getResultList();
    }

//    
//    List<Object[]> results = em.createQuery("SELECT p.firstName, p.lastName, n.phoneNumber FROM Person p, PhoneBookEntry n WHERE p.firstName = n.firstName AND p.lastName = n.lastName").getResultList();
//
//for (Object[] result : results) {
//	log.info(result[0] + " " + result[1] + " - " + result[2]);
//}
    @Override
    public String executeComplexQuery1(String query) {
        try {
            Query q = em.createQuery("SELECT st.userName from student st INNER JOIN st.school sc where sc.schoolName=:query or sc.headMaster.name=:query and st.faculty IS NOT NULL");
            q.setParameter("query", query);
            String result = (String) q.getResultList().get(0);
            return result;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String executeComplexQuery2(String address, String education, String query) {
        try {
            Query q = em.createQuery("Select sc.headMaster.name from School sc INNER JOIN sc.students st where sc.address=:address and sc.headMaster.education=:education and st.email LIKE :query");
            q.setParameter("address", address);
            q.setParameter("education", education);
            q.setParameter("query", query + "%");
            String result = (String) q.getResultList().get(0);
            return result;
        } catch (Exception e) {
            return "";
        }
    }

}
