package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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

    public void addPayment(Unit unit, User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Payment payment = new Payment();
            payment.setPaymentDate(new Date());
            payment.setUnit(unit);
            payment.setUser(user);
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
    public List<Payment> getAllPaymentsForUser(User user) {
        List<Payment> payments = null;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Payment p WHERE p.user = :user");
            query.setParameter("user", user);
            payments = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }
}
