/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.ejbImpl;

import com.homework.ejb.LoginDAO;
import com.homework.entity.Admin;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LoginDAOImpl implements LoginDAO {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    @Override
    public boolean validate(String user, String password) {
        try {
            Query q = em.createQuery("Select a from Admin a where a.uname=:uname and a.upass=:upass");
            q.setParameter("uname", user);
            q.setParameter("upass", password);
            Admin admin = (Admin) q.getSingleResult();
            if (admin != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
