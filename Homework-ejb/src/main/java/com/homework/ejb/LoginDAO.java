/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.ejb;

import javax.ejb.Remote;

/**
 *
 * @author sujan
 */
@Remote
public interface LoginDAO {
    public boolean validate(String user, String password);
}
