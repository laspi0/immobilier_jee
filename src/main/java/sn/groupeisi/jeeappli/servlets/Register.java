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

import org.mindrot.jbcrypt.BCrypt;
import sn.groupeisi.jeeappli.entiies.User;

@WebServlet("/register")
public class Register extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        userDAO = new UserDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("user/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create a new user with the hashed password
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setRole(role);

        // Register the user
        boolean registrationSuccessful = userDAO.register(user);

        if (registrationSuccessful) {
            // Redirect to a registration success page
            response.sendRedirect("registration_success.jsp");
        } else {
            // Handle registration error
            response.sendRedirect("register.jsp?error=1");
        }
    }
}
