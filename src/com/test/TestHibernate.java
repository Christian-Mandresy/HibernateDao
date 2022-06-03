package com.test;

import com.dao.DaoObject;
import com.model.Personne;

import com.service.PersonneService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

public class TestHibernate {

    public static void main(String args[]) throws Exception{


        ApplicationContext context = new
                FileSystemXmlApplicationContext(
                "applicationContext.xml"
        );

        System.out.println(context.containsBean("DaoObject"));
        System.out.println(context.containsBean("PersonneService"));


        PersonneService personneService=new PersonneService((DaoObject)context.getBean("DaoObject"));


        Personne personne=new Personne();

        personne.setNom("Unknown");


        DaoObject daoObject=new DaoObject();
        daoObject.setSessionFactory((SessionFactory)context.getBean("sessionFactory"));
        List lista=daoObject.find(personne);

        for (int i = 0; i <lista.size() ; i++) {

            Personne pers=(Personne) lista.get(i);
            System.out.println("nom = [" + pers.getNom() + "]");
            System.out.println("prenom = [" + pers.getPrenom() + "]");
            System.out.println("age = [" + pers.getAge() + "]");
        }





    }
}