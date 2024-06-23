package sn.groupeisi.jeeappli;

import sn.groupeisi.jeeappli.dao.LocationDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Location;
import sn.groupeisi.jeeappli.entiies.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {

            LocationDAO locationDAO = new LocationDAO(HibernateUtil.getSessionFactory());
            List<Object[]> locationRequests = locationDAO.getAllPendingLocationRequests();
            for (Object[] request : locationRequests) {
                Location location = (Location) request[0];
                User user = (User) request[1];
                String propertyName = (String) request[2];
                String propertyAddress = (String) request[3];
                int durationMonths = (int) request[4];
                double amount = (double) request[5];

                System.out.println("Location ID: " + location.getId());
                System.out.println("User: " + user.getFirstName() + " " + user.getLastName());
                System.out.println("Property Name: " + propertyName);
                System.out.println("Property Address: " + propertyAddress);
                System.out.println("Duration Months: " + durationMonths);
                System.out.println("Amount: " + amount);
                System.out.println("------------------------");
            }

            // Fermeture de la session Hibernate
            HibernateUtil.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
