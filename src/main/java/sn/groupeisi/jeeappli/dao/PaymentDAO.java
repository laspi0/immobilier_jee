package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sn.groupeisi.jeeappli.entiies.Location;
import sn.groupeisi.jeeappli.entiies.Payment;
import sn.groupeisi.jeeappli.entiies.Unit;
import sn.groupeisi.jeeappli.entiies.User;

import java.util.Date;
import java.util.List;

public class PaymentDAO {
    private SessionFactory sessionFactory;
    public PaymentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addPayment(Unit unit, User user, long locationId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Location location = session.get(Location.class, locationId);
            if (location == null) {
                throw new IllegalArgumentException("Invalid location ID: " + locationId);
            }

            Payment payment = new Payment();
            payment.setPaymentDate(new Date());
            payment.setUnit(unit);
            payment.setUser(user);
            payment.setLocation(location);
            payment.setStatus("en attente"); // Statut par défaut
            session.save(payment);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public List<Object[]> getAllPaymentsForUser(User user) {
        Session session = null;
        Transaction transaction = null;
        List<Object[]> payments = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "SELECT p.paymentDate, u.unitNumber, l.durationMonths, l.amount, p.status, p.id " +
                    "FROM Payment p " +
                    "LEFT JOIN p.unit u " +
                    "LEFT JOIN p.location l " +
                    "WHERE p.user.id = :userId";
            payments = session.createQuery(hql)
                    .setParameter("userId", user.getId())
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return payments;
    }


    public void updatePaymentStatus(long paymentId, String newStatus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Payment payment = session.get(Payment.class, paymentId);
            if (payment != null) {
                payment.setStatus(newStatus);
                session.update(payment);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public List<Object[]> getPaymentsForOwner(int ownerId) {
        List<Object[]> results;
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT p.payment_date, u.unit_number, l.duration_months, l.amount, p.status " +
                    "FROM payments p " +
                    "INNER JOIN locations l ON p.location_id = l.id " +
                    "INNER JOIN units u ON l.unit_id = u.id " +
                    "INNER JOIN users o ON u.user_id = o.id " +
                    "WHERE o.id = :ownerId";

            results = session.createNativeQuery(sql)
                    .setParameter("ownerId", ownerId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            results = null;
        }
        return results;
    }
}

