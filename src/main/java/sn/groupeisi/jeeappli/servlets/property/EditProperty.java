package sn.groupeisi.jeeappli.servlets.property;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.PropertyDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Property;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/editProperty")
public class EditProperty extends HttpServlet {

    private PropertyDAO propertyDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        propertyDAO = new PropertyDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int propertyId = Integer.parseInt(request.getParameter("propertyId"));
        Property property = propertyDAO.getPropertyWithEquipments(propertyId);
        if (property != null) {
            request.setAttribute("property", property);
            request.getRequestDispatcher("/property/edit.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/listProperties");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int propertyId = Integer.parseInt(request.getParameter("propertyId"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String[] equipments = request.getParameterValues("equipments");

        Property property = propertyDAO.getPropertyWithEquipments(propertyId);
        if (property != null) {
            property.setName(name);
            property.setAddress(address);
            property.setDescription(description);
            property.setEquipments(Arrays.asList(equipments));

            propertyDAO.updateProperty(property);
        }

        response.sendRedirect(request.getContextPath() + "/listProperties");
    }
}
