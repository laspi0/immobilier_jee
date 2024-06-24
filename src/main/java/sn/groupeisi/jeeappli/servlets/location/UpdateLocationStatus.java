package sn.groupeisi.jeeappli.servlets.location;

import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.LocationDAO;
import sn.groupeisi.jeeappli.dao.PaymentDAO;
import sn.groupeisi.jeeappli.dao.UnitDAO;
import sn.groupeisi.jeeappli.dao.UserDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Unit;
import sn.groupeisi.jeeappli.entiies.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateLocationStatus")
public class UpdateLocationStatus extends HttpServlet {

    private LocationDAO locationDAO;
    private PaymentDAO paymentDAO;
    private UnitDAO unitDAO;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        locationDAO = new LocationDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        unitDAO = new UnitDAO(sessionFactory);
        userDAO = new UserDAO(sessionFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long locationId = Long.parseLong(request.getParameter("locationId"));
            String status = request.getParameter("status");
            Long unitId = Long.parseLong(request.getParameter("unitId"));
            Long userId = Long.parseLong(request.getParameter("userId"));

            locationDAO.updateLocationStatus(locationId, status);
            HttpSession session = request.getSession(true);

            if ("accepte".equals(status)) {
                Unit unit = unitDAO.getUnitById(unitId);
                User user = userDAO.getUserById(userId);
                paymentDAO.addPayment(unit, user, locationId);
            }
            response.sendRedirect(request.getContextPath() + "/listLocation");
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.shutdown();
    }
}

