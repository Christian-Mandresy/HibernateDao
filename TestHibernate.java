package test;

import dao.DaoObject;
import model.Personne;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Date;

public class TestHibernate {

    public static void main(String args[]) throws Exception {
        Configuration config = new Configuration();
        config.addClass(Personne.class);
        SessionFactory sessionFactory = config.buildSessionFactory();

        ApplicationContext context = new
                FileSystemXmlApplicationContext(
                "applicationContext.xml"
        );
        Personne user = (Personne) context.getBean("personne");
        System.out.println(
                "Utilisateur : "+user.getNom()+" "+user.getPrenom()
        );

        DaoObject daoObject=new DaoObject();
        daoObject.setSessionFactory(sessionFactory);

        daoObject.save(user);


    }
}