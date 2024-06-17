package sn.groupeisi.jeeappli.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UtilisateurDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;

import java.io.IOException;

@WebServlet("/toggleUserStatus")
public class ToggleUserStatusServlet extends HttpServlet {
    private UtilisateurDAO utilisateurDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        utilisateurDAO = new UtilisateurDAO(sessionFactory);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            utilisateurDAO.toggleUserStatusById(userId);
        }
        response.sendRedirect("/listuser");
    }
}
