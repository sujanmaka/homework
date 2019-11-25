package com.homework.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(School.class)
public abstract class School_ {

	public static volatile SingularAttribute<School, String> address;
	public static volatile ListAttribute<School, Student> students;
	public static volatile SingularAttribute<School, HeadMaster> headMaster;
	public static volatile SingularAttribute<School, Long> id;
	public static volatile SingularAttribute<School, String> schoolName;

}

