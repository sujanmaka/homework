/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigation")
@RequestScoped
public class NavigationController {
    
    public String navigateToSchool() {
        return "indexSchool.xhtml?faces-redirect=true";
    }
    
    public String navigateToStudent() {
        return "indexStudent.xhtml?faces-redirect=true";
    }
    
    public String navigateToTeacher() {
        return "indexTeacher.xhtml?faces-redirect=true";
    }
    
    public String navigateToHome() {
        return "home.xhtml?faces-redirect=true";
    }
    
    public String navigateToComplexQuery() {
        return "indexComplexQuery.xhtml?faces-redirect=true";
    }
    
    public String navigateToMDB() {
        return "index.jsp?faces-redirect=true";
    }
    
    public String navigateToHeadMaster() {
        return "headMaster.xhtml?faces-redirect=true";
    }
}
