package sn.groupeisi.jeeappli;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sn.groupeisi.jeeappli.entiies.Immeuble;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Configuration Hibernate à partir de hibernate.cfg.xml
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Création d'une nouvelle session Hibernate
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Création d'un utilisateur (hypothétique)
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom("John");
            utilisateur.setPrenom("Doe");
            utilisateur.setEmail("john.doe@example.com");
            utilisateur.setMotDePasse("secret");

            // Création d'un immeuble
            Immeuble immeuble = new Immeuble();
            immeuble.setNom("Immeuble A");
            immeuble.setAdresse("123 Rue Principale");
            immeuble.setDescription("Bel immeuble moderne");
            immeuble.setEquipements(Arrays.asList("Ascenseur", "Parking", "Piscine"));
            immeuble.setUtilisateur(utilisateur);

            // Enregistrement de l'utilisateur s'il n'existe pas encore
            session.saveOrUpdate(utilisateur);

            // Enregistrement de l'immeuble
            session.save(immeuble);

            transaction.commit();

            System.out.println("Immeuble ajouté avec succès avec ID: " + immeuble.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture de la session factory à la fin de l'application
            sessionFactory.close();
        }
    }
}
