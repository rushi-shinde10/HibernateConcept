package com.bmc.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bmc.entity.Product;

public class HibernateConfiguration {
	
	public static SessionFactory getSessionFactory() {

		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml").addAnnotatedClass(Product.class);
		SessionFactory sf = cfg.buildSessionFactory();

		
		return sf;
	}
	
}
