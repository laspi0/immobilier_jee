package sn.groupeisi.jeeappli;

import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UtilisateurDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

import java.util.List;


public class MainApp {


    public static void main(String[] args) {
        // Initialiser la sessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(sessionFactory);

        // Exemple : Inverser le statut de l'utilisateur avec l'ID 1
        utilisateurDAO.toggleUserStatusById(4);

        // Fermer la sessionFactory
        sessionFactory.close();
    }

}

