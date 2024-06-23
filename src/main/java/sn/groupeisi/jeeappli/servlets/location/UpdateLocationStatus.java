package sn.groupeisi.jeeappli.servlets.location;

import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.LocationDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateLocationStatus")
public class UpdateLocationStatus extends HttpServlet {

    private LocationDAO locationDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        locationDAO = new LocationDAO(sessionFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Long locationId = Long.parseLong(request.getParameter("locationId"));

        try {
            if ("accept".equals(action)) {
                locationDAO.updateLocationStatus(locationId, "accepté");
            } else if ("refuse".equals(action)) {
                locationDAO.updateLocationStatus(locationId, "refusé");
            }
            response.sendRedirect("listLocation");
        } catch (Exception e) {
            throw new ServletException("Error updating location status", e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.shutdown();
    }
}
