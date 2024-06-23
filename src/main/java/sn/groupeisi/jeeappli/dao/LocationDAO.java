package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Location;

import java.util.List;

public class LocationDAO {

    private final SessionFactory sessionFactory;

    public LocationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveLocation(Location location) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(location);
            session.getTransaction().commit();
        }
    }

    public List<Object[]> getAllPendingLocationRequests() {
        Session session = sessionFactory.openSession();
        List<Object[]> locationRequests = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT l, u, p.name, p.address, l.durationMonths, l.amount " +
                    "FROM Location l " +
                    "JOIN l.user u " +
                    "JOIN l.unit u2 " +
                    "JOIN u2.property p " +
                    "WHERE l.status = :status");
            query.setParameter("status", "en attente");
            locationRequests = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return locationRequests;
    }

    public void updateLocationStatus(Long locationId, String status) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Location location = session.get(Location.class, locationId);
            if (location != null) {
                location.setStatus(status);
                session.update(location);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public List<Object[]> getAllLocationRequestsByUser(Long userId) {
        Session session = sessionFactory.openSession();
        List<Object[]> locationRequests = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT l, u, p.name, p.address, l.durationMonths, l.amount " +
                    "FROM Location l " +
                    "JOIN l.user u " +
                    "JOIN l.unit u2 " +
                    "JOIN u2.property p " +
                    "WHERE u.id = :userId");
            query.setParameter("userId", userId);
            locationRequests = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return locationRequests;
    }



}
