package sn.groupeisi.jeeappli.servlets.Unit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.PropertyDAO;
import sn.groupeisi.jeeappli.dao.UnitDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Property;
import sn.groupeisi.jeeappli.entiies.Unit;


import java.io.IOException;

@WebServlet("/editUnit")
public class EditUnit extends HttpServlet {

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
        int unitId = Integer.parseInt(request.getParameter("unitId"));
        Unit unit = unitDAO.getUnit(unitId);
        request.setAttribute("unit", unit);
        request.setAttribute("properties", propertyDAO.getAllProperties());
        request.getRequestDispatcher("/unit/editUnit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int unitId = Integer.parseInt(request.getParameter("unitId"));
        String unitNumber = request.getParameter("unitNumber");
        int numberOfRooms = Integer.parseInt(request.getParameter("numberOfRooms"));
        double area = Double.parseDouble(request.getParameter("area"));
        double rent = Double.parseDouble(request.getParameter("rent"));
        int propertyId = Integer.parseInt(request.getParameter("propertyId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        Property property = propertyDAO.getProperty(propertyId);
        Unit unit = new Unit(unitNumber, numberOfRooms, area, rent, property);
        unit.setId(unitId);
        unit.setUserId(userId);

        unitDAO.updateUnit(unit);

        response.sendRedirect(request.getContextPath() + "/listUnits");
    }
}
