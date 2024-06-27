package sn.groupeisi.jeeappli.servlets;

import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.PaymentDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@WebServlet("/printReceipt")
public class PrintReceiptServlet extends HttpServlet {

    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        paymentDAO = new PaymentDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Object[]> payments = paymentDAO.getAllPaymentsForUser(user);

        String paymentId = request.getParameter("paymentId");
        Object[] selectedPayment = null;

        // Recherche du paiement correspondant à l'identifiant spécifié dans la liste des paiements de l'utilisateur
        if (paymentId != null && !paymentId.isEmpty()) {
            for (Object[] payment : payments) {
                if (paymentId.equals(String.valueOf(payment[0]))) { // Assume payment[0] is paymentId
                    selectedPayment = payment;
                    break;
                }
            }
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (selectedPayment != null) {
            out.println(generateReceiptContent(selectedPayment, user));
        } else {
            out.println("Paiement non trouvé pour l'identifiant: " + paymentId);
        }
    }

    private String generateReceiptContent(Object[] payment, User user) {
        StringBuilder receiptContent = new StringBuilder();
        receiptContent.append("Reçu de Paiement\n\n");
        receiptContent.append("Client: ").append(user.getFirstName()).append(" ").append(user.getLastName()).append("\n");
        receiptContent.append("Date de Paiement: ").append(Objects.toString(payment[1], "N/A")).append("\n"); // Assume payment[1] is date
        receiptContent.append("Numéro d'Unité: ").append(Objects.toString(payment[2], "N/A")).append("\n"); // Assume payment[2] is unitNumber
        receiptContent.append("Durée (mois): ").append(Objects.toString(payment[3], "N/A")).append("\n"); // Assume payment[3] is durationMonths
        receiptContent.append("Montant: ").append(Objects.toString(payment[4], "N/A")).append("\n"); // Assume payment[4] is amount
        receiptContent.append("Statut: ").append(Objects.toString(payment[5], "N/A")).append("\n"); // Assume payment[5] is status

        return receiptContent.toString();
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.shutdown();
    }
}
