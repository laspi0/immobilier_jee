package sn.groupeisi.jeeappli.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UserDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;

import java.io.IOException;

@WebServlet("/toggleUserStatus")
public class ToggleUserStatusServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        userDAO = new UserDAO(sessionFactory);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            userDAO.toggleUserStatusById(userId);
        }
        response.sendRedirect("/listuser");
    }
}
