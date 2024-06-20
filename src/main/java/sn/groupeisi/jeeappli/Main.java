package sn.groupeisi.jeeappli;

import sn.groupeisi.jeeappli.dao.ImmeubleDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Immeuble;

public class Main {
    public static void main(String[] args) {
        // Obtenez la SessionFactory à partir de HibernateUtil
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Créez une instance de ImmeubleDAO avec la SessionFactory
        ImmeubleDAO immeubleDAO = new ImmeubleDAO(sessionFactory);

        int immeubleIdASupprimer = 4; // Remplacez par l'ID réel de l'immeuble que vous voulez supprimer

        immeubleDAO.supprimerImmeuble(immeubleIdASupprimer);

        verifierSuppressionImmeuble(immeubleIdASupprimer, sessionFactory);

        // N'oubliez pas de fermer la SessionFactory à la fin
        sessionFactory.close();
    }

    private static void verifierSuppressionImmeuble(int immeubleId, SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Immeuble immeuble = session.get(Immeuble.class, immeubleId);
            if (immeuble == null) {
                System.out.println("L'immeuble avec l'ID " + immeubleId + " a été supprimé avec succès.");
            } else {
                System.out.println("Attention : L'immeuble avec l'ID " + immeubleId + " existe encore.");
            }
        }
    }
}