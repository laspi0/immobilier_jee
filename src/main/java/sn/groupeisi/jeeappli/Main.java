package sn.groupeisi.jeeappli;

import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.ImmeubleDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Immeuble;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Créer une instance de ImmeubleDAO
        ImmeubleDAO immeubleDAO = new ImmeubleDAO(sessionFactory);

        // Créer un objet Utilisateur de test
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(7); // Remplacez par un identifiant d'utilisateur valide dans votre base de données

        // Appeler la méthode obtenirImmeublesPourUtilisateur
        List<Immeuble> immeubles = immeubleDAO.obtenirImmeublesPourUtilisateur(utilisateur);

        // Vérifier si des immeubles ont été récupérés
        if (immeubles != null && !immeubles.isEmpty()) {
            System.out.println("Immeubles récupérés pour l'utilisateur " + utilisateur.getId() + " :");

            // Parcourir et afficher les informations des immeubles
            for (Immeuble immeuble : immeubles) {
                System.out.println("Nom : " + immeuble.getNom());
                System.out.println("Adresse : " + immeuble.getAdresse());
                System.out.println("Description : " + immeuble.getDescription());
                System.out.println("Équipements : " + immeuble.getEquipements());
                System.out.println();
            }
        } else {
            System.out.println("Aucun immeuble trouvé pour l'utilisateur " + utilisateur.getId());
        }

        // Fermer la SessionFactory
        sessionFactory.close();
    }
}