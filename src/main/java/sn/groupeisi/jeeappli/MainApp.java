package sn.groupeisi.jeeappli;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class MainApp {

    public static void main(String[] args) {
        // Chargement de la configuration Hibernate depuis hibernate.cfg.xml
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // Fermeture de la SessionFactory à la fin de l'application
        sessionFactory.close();
    }
}
