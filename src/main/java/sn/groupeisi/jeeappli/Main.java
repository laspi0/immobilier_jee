package sn.groupeisi.jeeappli;
/*
import sn.groupeisi.jeeappli.dao.PropertyDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Property;

public class Main {
    public static void main(String[] args) {
        // Obtenez la SessionFactory à partir de HibernateUtil
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Créez une instance de ImmeubleDAO avec la SessionFactory
        PropertyDAO propertyDAO = new PropertyDAO(sessionFactory);

        int immeubleIdASupprimer = 4; // Remplacez par l'ID réel de l'immeuble que vous voulez supprimer

        propertyDAO.supprimerImmeuble(immeubleIdASupprimer);

        verifierSuppressionImmeuble(immeubleIdASupprimer, sessionFactory);

        // N'oubliez pas de fermer la SessionFactory à la fin
        sessionFactory.close();
    }

    private static void verifierSuppressionImmeuble(int immeubleId, SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Property property = session.get(Property.class, immeubleId);
            if (property == null) {
                System.out.println("L'immeuble avec l'ID " + immeubleId + " a été supprimé avec succès.");
            } else {
                System.out.println("Attention : L'immeuble avec l'ID " + immeubleId + " existe encore.");
            }
        }
    }
}\

 */