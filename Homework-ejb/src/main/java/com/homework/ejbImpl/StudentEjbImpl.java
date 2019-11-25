/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.ejbImpl;


import com.homework.entity.DeletedStudents;
import com.homework.entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class StudentEjbImpl {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    public List<Student> allUsers() {
        TypedQuery<Student> sorgu = em.createNamedQuery("allUsers", Student.class);
        return sorgu.getResultList();
    }
    
    public List<DeletedStudents> allDeleteUsers() {
        TypedQuery<DeletedStudents> sorgu = em.createNamedQuery("allDeleteUsers", DeletedStudents.class);
        return sorgu.getResultList();
    }

    public Student getXUser(String username) {
        return em.find(Student.class, username);
    }

    public Student getXEmail(Long id) {
        return em.find(Student.class, id);
    }

    public List mail(String email) {
        Query q = em.createQuery("SELECT s FROM student s WHERE s.email = :email");
        q.setParameter("email", email);
        List result = (ArrayList) q.getResultList();

        return result;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Student saveUser(Student user) {
        em.persist(user);
        em.flush();
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DeletedStudents saveUserWithDeleteReason(DeletedStudents delUser) {
        em.persist(delUser);
        em.flush();
        return delUser;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Student mergeUser(Student user) {

        System.out.println("--------------------5--------------------------------");
        System.out.println(user.getId());
        System.out.println("----------------------6------------------------------");
        em.merge(em.find(Student.class, user.getId()));

        System.out.println("------------------------7----------------------------");
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteUsers(Student user) throws Exception {
        try {
            em.remove(em.merge(user));
            em.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Student editUsers(Student user) throws Exception {
        try {
            System.out.println("User hereko " + user);
            em.merge(user);
            em.flush();
            return user;
        } catch (Exception e) {

            System.out.println(e);
            return null;
        }
    }

}
