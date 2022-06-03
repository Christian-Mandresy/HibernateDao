package com.test;

import com.model.Personne;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new
                FileSystemXmlApplicationContext(
                "applicationContext.xml"
        );
        Personne user = (Personne) context.getBean("personne");
        System.out.println(
                "Utilisateur : "+user.getNom()+" "+user.getPrenom()
        );



    }
}
