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
import java.util.List;

@WebServlet("/listLocation")
public class ListLocation extends HttpServlet {

    private LocationDAO locationDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        locationDAO = new LocationDAO(sessionFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Object[]> locationRequests = locationDAO.getAllPendingLocationRequests();
            request.setAttribute("locationRequests", locationRequests);
            request.getRequestDispatcher("/location/listLocation.jsp").forward(request, response);
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
