package sn.groupeisi.jeeappli.servlets.Payment;

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
import java.util.List;

@WebServlet("/userPayment")
public class UserPayment extends HttpServlet {

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
        request.setAttribute("payments", payments);
        request.getRequestDispatcher("/payment/userPayment.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.shutdown();
    }
}
