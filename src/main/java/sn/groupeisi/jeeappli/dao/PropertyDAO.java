package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sn.groupeisi.jeeappli.database.HibernateUtil;

import org.hibernate.query.Query;
import sn.groupeisi.jeeappli.entiies.Property;
import sn.groupeisi.jeeappli.entiies.User;

import java.util.List;

public class PropertyDAO {

    private final SessionFactory sessionFactory;

    public PropertyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Property getProperty(int propertyId) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Property.class, propertyId);
        } finally {
            session.close();
        }
    }



    public boolean addProperty(Property property) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Save the user first
            User user = property.getUser();
            if (user != null && user.getId() == 0) {
                session.save(user);
            }

            // Then save the property
            session.save(property);

            transaction.commit();
            return true; // Return true if addition is successful
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false; // Return false if addition fails
    }


    public List<Property> getPropertiesForUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Property> query = session.createQuery("SELECT p FROM Property p JOIN FETCH p.equipments WHERE p.user = :user", Property.class);
            query.setParameter("user", user);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteProperty(int propertyId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Property property = session.get(Property.class, propertyId);
            if (property != null) {
                // Delete associated equipments
                session.createNativeQuery("DELETE FROM equipments WHERE property_id = :propertyId")
                        .setParameter("propertyId", propertyId)
                        .executeUpdate();

                // Delete the property
                session.delete(property);
                System.out.println("Property and associated equipments deleted.");
            } else {
                System.out.println("No property found with ID: " + propertyId);
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


    public Property getPropertyWithEquipments(int propertyId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT p FROM Property p JOIN FETCH p.equipments WHERE p.id = :id", Property.class)
                    .setParameter("id", propertyId)
                    .uniqueResult();
        }
    }

    public void updateProperty(Property property) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(property);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List<Property> getAllProperties() {
        try (Session session = sessionFactory.openSession()) {
            Query<Property> query = session.createQuery("SELECT p FROM Property p JOIN FETCH p.equipments", Property.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
