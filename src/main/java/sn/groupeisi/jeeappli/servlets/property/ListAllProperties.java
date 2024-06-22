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
import java.util.List;

@WebServlet("/listAllProperties")
public class ListAllProperties extends HttpServlet {

    private PropertyDAO propertyDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        propertyDAO = new PropertyDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Property> properties = propertyDAO.getAllProperties();
        request.setAttribute("properties", properties);
        request.getRequestDispatcher("/property/all.jsp").forward(request, response);
    }
}
