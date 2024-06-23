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

@WebServlet("/locationByUser")
public class LocationByUser extends HttpServlet {

    private LocationDAO locationDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        locationDAO = new LocationDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            if (userId == null) {
                throw new ServletException("User not logged in");
            }

            List<Object[]> locationRequests = locationDAO.getAllLocationRequestsByUser(userId.longValue());
            request.setAttribute("locationRequests", locationRequests);
            request.getRequestDispatcher("/location/locationByUser.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }
}
