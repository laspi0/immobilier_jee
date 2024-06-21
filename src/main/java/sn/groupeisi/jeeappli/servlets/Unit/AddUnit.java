package sn.groupeisi.jeeappli.servlets.Unit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.PropertyDAO;
import sn.groupeisi.jeeappli.dao.UnitDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Property;
import sn.groupeisi.jeeappli.entiies.Unit;


import java.io.IOException;

@WebServlet("/addUnit")
public class AddUnit extends HttpServlet {

    private UnitDAO unitDAO;
    private PropertyDAO propertyDAO;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        unitDAO = new UnitDAO(sessionFactory);
        propertyDAO = new PropertyDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("properties", propertyDAO.getAllProperties());
        request.getRequestDispatcher("unit/addUnit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String unitNumber = request.getParameter("unitNumber");
        int numberOfRooms = Integer.parseInt(request.getParameter("numberOfRooms"));
        double area = Double.parseDouble(request.getParameter("area"));
        double rent = Double.parseDouble(request.getParameter("rent"));
        int propertyId = Integer.parseInt(request.getParameter("propertyId"));
        int userId = Integer.parseInt(request.getParameter("userId")); // Récupérer l'ID de l'utilisateur connecté

        Property property = propertyDAO.getProperty(propertyId);

        Unit unit = new Unit(unitNumber, numberOfRooms, area, rent, property);
        unit.setUserId(userId); // Définir l'ID de l'utilisateur pour l'unité

        unitDAO.addUnit(unit);

        response.sendRedirect(request.getContextPath() + "/listUnits");
    }
}
