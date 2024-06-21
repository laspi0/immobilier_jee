package sn.groupeisi.jeeappli.servlets.property;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.PropertyDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;

import java.io.IOException;

@WebServlet("/deleteProperty")
public class DeleteProperty extends HttpServlet {

    private PropertyDAO propertyDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        propertyDAO = new PropertyDAO(sessionFactory);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String propertyIdStr = request.getParameter("propertyId");

        if (propertyIdStr != null) {
            try {
                int propertyId = Integer.parseInt(propertyIdStr);
                propertyDAO.deleteProperty(propertyId);
                response.sendRedirect(request.getContextPath() + "/listProperties");
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid property ID.");
            }
        } else {
            response.getWriter().write("Property ID not provided.");
        }
    }
}
