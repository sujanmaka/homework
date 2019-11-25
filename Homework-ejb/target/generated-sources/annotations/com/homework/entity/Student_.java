package com.homework.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ {

	public static volatile SingularAttribute<Student, School> school;
	public static volatile SingularAttribute<Student, String> mobile;
	public static volatile SingularAttribute<Student, Long> id;
	public static volatile SingularAttribute<Student, String> userName;
	public static volatile SetAttribute<Student, Faculties> faculties;
	public static volatile SingularAttribute<Student, String> email;
	public static volatile SingularAttribute<Student, String> faculty;

}

