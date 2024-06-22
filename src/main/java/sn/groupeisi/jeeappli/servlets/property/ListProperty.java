package sn.groupeisi.jeeappli.servlets.property;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.PropertyDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Property;
import sn.groupeisi.jeeappli.entiies.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/listProperties")
public class ListProperty extends HttpServlet {

    private PropertyDAO propertyDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        propertyDAO = new PropertyDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // Redirection vers la page de connexion si l'utilisateur n'est pas connecté
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Property> properties = propertyDAO.getPropertiesForUser(user);
        request.setAttribute("properties", properties);
        request.getRequestDispatcher("/property/index.jsp").forward(request, response);
    }
}
