package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sn.groupeisi.jeeappli.entiies.Unit;

import java.util.List;

public class UnitDAO {
    private SessionFactory sessionFactory;

    public UnitDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUnit(Unit unit) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(unit);
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

    public List<Unit> getUnitsByUserId(int userId) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Unit where userId = :userId", Unit.class)
                    .setParameter("userId", userId)
                    .list();
        } finally {
            session.close();
        }
    }



    public void updateUnit(Unit unit) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(unit);
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

    public void deleteUnit(int unitId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Unit unit = session.get(Unit.class, unitId);
            if (unit != null) {
                session.delete(unit);
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

    public Unit getUnit(int unitId) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Unit.class, unitId);
        } finally {
            session.close();
        }
    }

    public List<Unit> getAllUnits() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Unit", Unit.class).list();
        } finally {
            session.close();
        }
    }
}
