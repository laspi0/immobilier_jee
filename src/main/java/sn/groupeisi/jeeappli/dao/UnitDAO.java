package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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

    public List<Unit> getFilteredUnits(Double minPrice, Double maxPrice, String address, Integer numberOfRooms) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder queryBuilder = new StringBuilder("from Unit u where 1=1");

            if (minPrice != null) {
                queryBuilder.append(" and u.rent >= :minPrice");
            }
            if (maxPrice != null) {
                queryBuilder.append(" and u.rent <= :maxPrice");
            }
            if (address != null && !address.isEmpty()) {
                queryBuilder.append(" and u.property.address like :address");
            }
            if (numberOfRooms != null) {
                queryBuilder.append(" and u.numberOfRooms = :numberOfRooms");
            }

            Query<Unit> query = session.createQuery(queryBuilder.toString(), Unit.class);

            if (minPrice != null) {
                query.setParameter("minPrice", minPrice);
            }
            if (maxPrice != null) {
                query.setParameter("maxPrice", maxPrice);
            }
            if (address != null && !address.isEmpty()) {
                query.setParameter("address", "%" + address + "%");
            }
            if (numberOfRooms != null) {
                query.setParameter("numberOfRooms", numberOfRooms);
            }

            return query.list();
        }
    }


    public List<String> getDistinctAddresses() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select distinct u.property.address from Unit u", String.class).list();
        }
    }

    public Unit getUnitById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Unit.class, id);
        } finally {
            session.close();
        }
    }



}
