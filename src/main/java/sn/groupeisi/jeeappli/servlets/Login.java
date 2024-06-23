package sn.groupeisi.jeeappli.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import sn.groupeisi.jeeappli.dao.UserDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.User;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        userDAO = new UserDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("user/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieving form parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validating user
        User user = userDAO.getByEmail(email);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            // Authentication successful
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            // Redirect based on user role
            String role = user.getRole();
            if ("admin".equals(role)) {
                response.sendRedirect("/listuser");
            } else if ("tenant".equals(role)) {
                response.sendRedirect("/allUnits");
            } else if ("owner".equals(role)) {
                response.sendRedirect("/listProperties");
            } else {
                // Handling undefined roles
                response.sendRedirect("login.jsp?error=2");
            }
        } else {
            // Authentication failed
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
