package sn.groupeisi.jeeappli.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UserDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/listuser")
public class ListUser extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        userDAO = new UserDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println("Utilisateur: " + user.getFirstName() + " " + user.getLastName());
        }
        HttpSession session = request.getSession();
        session.setAttribute("users", users);
        request.getRequestDispatcher("user/listUser.jsp").forward(request, response);
    }


}