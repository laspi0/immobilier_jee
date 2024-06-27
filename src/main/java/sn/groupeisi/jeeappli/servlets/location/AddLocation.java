package sn.groupeisi.jeeappli.servlets.location;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.LocationDAO;
import sn.groupeisi.jeeappli.dao.UnitDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Location;
import sn.groupeisi.jeeappli.entiies.Unit;
import sn.groupeisi.jeeappli.entiies.User;


import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/addLocation")
public class AddLocation extends HttpServlet {

    private LocationDAO locationDAO;
    private UnitDAO unitDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        locationDAO = new LocationDAO(sessionFactory);
        unitDAO = new UnitDAO(sessionFactory);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // Utilisateur connecté

        Long unitId = Long.parseLong(request.getParameter("unitId"));
        int durationMonths = Integer.parseInt(request.getParameter("durationMonths"));
        double amount = Double.parseDouble(request.getParameter("amount"));

        UnitDAO unitDAO = new UnitDAO(HibernateUtil.getSessionFactory());
        Unit unit = unitDAO.getUnitById(unitId);

        LocalDate startDate = LocalDate.now();

        Location newLocation = new Location(startDate, durationMonths, amount, "en attente", unit, user);

        locationDAO.saveLocation(newLocation);

        response.sendRedirect(request.getContextPath() + "/locationByUser");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long unitId = Long.parseLong(request.getParameter("unitId"));
        Unit unit = unitDAO.getUnitById(unitId);
        request.setAttribute("unit", unit);
        request.getRequestDispatcher("/location/addLocation.jsp").forward(request, response);
    }

}
