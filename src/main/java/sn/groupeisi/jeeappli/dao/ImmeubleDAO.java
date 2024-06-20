package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sn.groupeisi.jeeappli.entiies.Immeuble;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

public class ImmeubleDAO {

    private final SessionFactory sessionFactory;

    public ImmeubleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean ajouterImmeuble(Immeuble immeuble) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Sauvegarder l'utilisateur d'abord
            Utilisateur utilisateur = immeuble.getUtilisateur();
            if (utilisateur != null && utilisateur.getId() == 0) {
                session.save(utilisateur);
            }

            // Ensuite sauvegarder l'immeuble
            session.save(immeuble);

            transaction.commit();
            return true; // Retourner true si l'ajout est réussi
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false; // Retourner false en cas d'échec
    }
}
