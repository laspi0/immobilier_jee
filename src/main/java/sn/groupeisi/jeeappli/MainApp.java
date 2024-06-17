package sn.groupeisi.jeeappli;

import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UtilisateurDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

import java.util.List;


public class MainApp {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Instanciation du DAO
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(sessionFactory);

        // Appel de la méthode pour récupérer les utilisateurs non-admin
        List<Utilisateur> utilisateurs = utilisateurDAO.getAllNonAdminUsers();

        // Affichage des résultats
        System.out.println("Liste des utilisateurs non-admin:");
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println("ID: " + utilisateur.getId() +
                    ", Nom: " + utilisateur.getNom() +
                    ", Prénom: " + utilisateur.getPrenom() +
                    ", Email: " + utilisateur.getEmail() +
                    ", Rôle: " + utilisateur.getRole());
        }

        // Fermeture de la SessionFactory
        sessionFactory.close();
    }
}

