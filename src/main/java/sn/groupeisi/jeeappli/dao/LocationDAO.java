package sn.groupeisi.jeeappli.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.entiies.Location;

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
}
