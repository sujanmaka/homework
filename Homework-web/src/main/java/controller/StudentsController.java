/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.homework.ejb.SchoolEjb;
import com.homework.ejbImpl.StudentEjbImpl;
import com.homework.entity.DeletedStudents;
import com.homework.entity.Student;
import com.homework.entity.Faculties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "students")
@SessionScoped
public class StudentsController implements Serializable {

    @EJB
    private StudentEjbImpl userEJB;
    
    @EJB
    private SchoolEjb schoolEjb;

    private Student user;
    private Student editUser;

    Set<String> faculties = new HashSet<>();

    private List<Student> userList = new ArrayList();
    private String exceptions;
    private String deleteReason;
    private Long deleteId;
    private String deletedName;
    private List<DeletedStudents> deleteList = new ArrayList<>();
    private Long schoolId;

    @PostConstruct
    public void getAllUsersList() {
        user = new Student();
        userList = userEJB.allUsers();
        deleteList = userEJB.allDeleteUsers();

        editUser = new Student();

    }

    public List<String> SubJectList() {
        //this list to be rendered
        ArrayList<String> list = new ArrayList<>();

        list.add("physics");
        list.add("chemistry");
        list.add("math");
        return list;
    }

    public Student getEditUser() {
        return editUser;
    }

    public void setEditUser(Student editUser) {
        this.editUser = editUser;
    }

    @Override
    public String toString() {
        return "UsersController{" + "editUser=" + editUser + '}';
    }

    public String editUserss() {
        System.out.println("id ayo ki nai" + getEditUser());
        this.user = getEditUser();

        return "editStudent.xhtml";
    }

    public String editUser() {

        try {

            System.out.println("tala tira value haru check " + getEditUser());
            System.out.println("id ayo ki nai tala tira" + this.user);
            System.out.println("id ayo ki nai getUser" + getUser());

            userEJB.editUsers(this.user);
            userList = userEJB.allUsers();
            return "indexStudent.xhtml?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }

    }

    public String addUser() {

        System.out.println("Email : " + userEJB.mail(user.getEmail()));

        List data = userEJB.mail(user.getEmail());
        System.out.println(data.toString());
        if (!data.isEmpty()) {
            exceptions = "The user with email id already existed";
            System.out.println("Exceptions added " + getExceptions());

        } else {
            try {

                //Converting set to array
                String strArray[] = getFaculties().toArray(new String[getFaculties().size()]);

                Set<Faculties> faculties = new HashSet<>();
                for (int i = 0; i < getFaculties().size(); i++) {

                    Faculties u = new Faculties();
                    u.setFaculty(strArray[i]);
                    u.setUser_faculties(user);
                    faculties.add(u);

                }

                user.setFaculties(faculties);
                user.setSchool(schoolEjb.getSchoolById(schoolId));

                user = userEJB.saveUser(user);
                user = new Student();

                userList = userEJB.allUsers();
                return "indexStudent.xhtml?faces-redirect=true";
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public void removeExceptions() {
        exceptions = "";
        System.out.println("Exceptions remove" + getExceptions());

    }

    public String deleteUser(Student getUser) {
        try {

            userEJB.deleteUsers(getUser);
            userList = userEJB.allUsers();

            DeletedStudents delete = new DeletedStudents();
            delete.setDelReason(getDeleteReason());
            delete.setUId(getDeleteId());
            delete.setuName(getDeletedName());
            userEJB.saveUserWithDeleteReason(delete);
            deleteList = userEJB.allDeleteUsers();

            return "indexStudent.xhtml?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public Student getUser() {
        return user;
    }

    public void setUser(Student user) {
        this.user = user;
    }

    public List<Student> getUserList() {
        return userList;
    }

    public void setUserList(List<Student> userList) {
        this.userList = userList;
    }

    public StudentEjbImpl getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(StudentEjbImpl userEJB) {
        this.userEJB = userEJB;
    }

    public Set<String> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<String> faculties) {
        this.faculties = faculties;
    }

    public String getExceptions() {
        return exceptions;
    }

    public void setExceptions(String exceptions) {
        this.exceptions = exceptions;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public Long getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(Long deleteId) {
        this.deleteId = deleteId;
    }

    public String getDeletedName() {
        return deletedName;
    }

    public void setDeletedName(String deletedName) {
        this.deletedName = deletedName;
    }

    public List<DeletedStudents> getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(List<DeletedStudents> deleteList) {
        this.deleteList = deleteList;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
    
}
