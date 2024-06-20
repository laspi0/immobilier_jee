package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Immeuble;
import sn.groupeisi.jeeappli.entiies.Utilisateur;
import org.hibernate.query.Query;



import java.util.List;

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


    public List<Immeuble> obtenirImmeublesPourUtilisateur(Utilisateur utilisateur) {
        try (Session session = sessionFactory.openSession()) {
            Query<Immeuble> query = session.createQuery("SELECT i FROM Immeuble i JOIN FETCH i.equipements WHERE i.utilisateur = :utilisateur", Immeuble.class);
            query.setParameter("utilisateur", utilisateur);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void supprimerImmeuble(int immeubleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Immeuble immeuble = session.get(Immeuble.class, immeubleId);
            if (immeuble != null) {
                // Supprimer les équipements associés
                session.createNativeQuery("DELETE FROM equipements WHERE immeuble_id = :immeubleId")
                        .setParameter("immeubleId", immeubleId)
                        .executeUpdate();

                // Supprimer l'immeuble
                session.delete(immeuble);
                System.out.println("Immeuble et équipements associés supprimés.");
            } else {
                System.out.println("Aucun immeuble trouvé avec l'ID : " + immeubleId);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public Immeuble obtenirImmeubleParId(int id) {
        try (Session session = sessionFactory.openSession()) {
            Immeuble immeuble = session.get(Immeuble.class, id);
            return immeuble;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
