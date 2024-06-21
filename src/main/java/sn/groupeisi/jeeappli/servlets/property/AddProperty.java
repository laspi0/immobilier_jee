package sn.groupeisi.jeeappli.servlets.property;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.PropertyDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Property;
import sn.groupeisi.jeeappli.entiies.User;


import java.io.IOException;
import java.util.Arrays;

@WebServlet("/addProperty")
public class AddProperty extends HttpServlet {

    private PropertyDAO propertyDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        propertyDAO = new PropertyDAO(sessionFactory);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String[] equipmentsArray = request.getParameterValues("equipments");

        // Retrieve logged-in user from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Redirect to login page if user is not logged in
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Property property = new Property();
        property.setName(name);
        property.setAddress(address);
        property.setDescription(description);
        property.setEquipments(Arrays.asList(equipmentsArray));
        property.setUser(user);

        boolean addSuccessful = propertyDAO.addProperty(property);

        if (addSuccessful) {
            response.sendRedirect(request.getContextPath() + "/listProperties");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/property/add.jsp").forward(request, response);
    }
}
