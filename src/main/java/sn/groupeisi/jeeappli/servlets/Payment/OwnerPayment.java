package sn.groupeisi.jeeappli.servlets.Payment;

import sn.groupeisi.jeeappli.dao.PaymentDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ownerPayments")
public class OwnerPayment extends HttpServlet {

    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        paymentDAO = new PaymentDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer ownerId = (Integer) req.getSession().getAttribute("userId");
        if (ownerId == null) {
            resp.sendRedirect(req.getContextPath() + "/login"); // Redirigez vers la page de connexion si non connecté
            return;
        }

        List<Object[]> payments = paymentDAO.getPaymentsForOwner(ownerId);
        req.setAttribute("payments", payments);
        req.getRequestDispatcher("/payment/ownerPayments.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        HibernateUtil.shutdown();
    }
}
