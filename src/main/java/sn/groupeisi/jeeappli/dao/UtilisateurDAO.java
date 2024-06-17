package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

import java.util.List;

public class UtilisateurDAO {

    private final SessionFactory sessionFactory;

    public UtilisateurDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean inscrire(Utilisateur utilisateur) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(utilisateur);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Utilisateur getByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Utilisateur> query = session.createQuery("FROM Utilisateur WHERE email = :email", Utilisateur.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Utilisateur> getAllNonAdminUsers() {
        Session session = sessionFactory.openSession();
        List<Utilisateur> utilisateurs = null;
        try {
            String hql = "FROM Utilisateur WHERE role != 'admin'";
            Query<Utilisateur> query = session.createQuery(hql, Utilisateur.class);
            utilisateurs = query.getResultList();
        } finally {
            session.close();
        }
        return utilisateurs;
    }

    public void toggleUserStatusById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Utilisateur utilisateur = session.get(Utilisateur.class, id);
            if (utilisateur != null) {
                // Inverser le statut
                if ("actif".equals(utilisateur.getStatus())) {
                    utilisateur.setStatus("inactif");
                } else {
                    utilisateur.setStatus("actif");
                }
                session.update(utilisateur);
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
}
