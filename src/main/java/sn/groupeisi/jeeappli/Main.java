package sn.groupeisi.jeeappli;

import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.PaymentDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        PaymentDAO paymentDAO = new PaymentDAO(sessionFactory);

        // Suppose ownerId est l'ID du propriétaire dont vous voulez obtenir les paiements
        int ownerId = 2;

        // Récupérer la liste des paiements pour le propriétaire spécifié
        List<Object[]> payments = paymentDAO.getPaymentsForOwner(ownerId);

        // Vérifier si la liste de paiements n'est pas null avant de l'itérer
        if (payments != null) {
            payments.forEach(payment -> {
                // Traiter chaque paiement ici
                // Vous pouvez accéder aux éléments de l'array `payment` comme suit :
                Date paymentDate = (Date) payment[0];
                String unitNumber = (String) payment[1];
                int durationMonths = ((Number) payment[2]).intValue();

                // Vérifier le type de la colonne amount et convertir si nécessaire
                BigDecimal amount;
                if (payment[3] instanceof BigDecimal) {
                    amount = (BigDecimal) payment[3];
                } else if (payment[3] instanceof Double) {
                    amount = BigDecimal.valueOf((Double) payment[3]);
                } else {
                    amount = BigDecimal.ZERO; // ou une autre valeur par défaut appropriée
                }

                String status = (String) payment[4];

                // Afficher ou traiter les informations du paiement
                System.out.println("Payment Date: " + paymentDate);
                System.out.println("Unit Number: " + unitNumber);
                System.out.println("Duration Months: " + durationMonths);
                System.out.println("Amount: " + amount);
                System.out.println("Status: " + status);
                System.out.println("----------------------");
            });
        } else {
            System.out.println("Aucun paiement trouvé pour le propriétaire avec ID " + ownerId);
        }

        // Fermer la SessionFactory
        HibernateUtil.shutdown();
    }
}
