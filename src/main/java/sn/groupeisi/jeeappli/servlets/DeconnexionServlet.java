package sn.groupeisi.jeeappli.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/deconnexion")
public class DeconnexionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Récupère la session sans la créer si elle n'existe pas
        if (session != null) {
            session.invalidate(); // Invalide la session
        }
        response.sendRedirect("/connexion"); // Redirige vers la page de connexion
    }
}
