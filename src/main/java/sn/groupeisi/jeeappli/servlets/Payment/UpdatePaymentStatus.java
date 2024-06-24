package sn.groupeisi.jeeappli.servlets.Payment;

import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.PaymentDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updatePaymentStatus")
public class UpdatePaymentStatus extends HttpServlet {

    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        paymentDAO = new PaymentDAO(sessionFactory);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long paymentId = Long.parseLong(request.getParameter("paymentId"));
            paymentDAO.updatePaymentStatus(paymentId, "payé");
            response.sendRedirect(request.getContextPath() + "/userPayment");
        } catch (Exception e) {
            throw new ServletException("Error updating payment status", e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.shutdown();
    }
}
