package web.servlets;

import models.User;
import web.PasswordAuth;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static repositories.ConnectDatabase.userDB;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user;
        try {
            user = retrieveUserFromRequest(request);
            userDB.add(user);

            response.sendRedirect("/userProfile");
        } catch (InvalidKeySpecException e) {
            response.getWriter().println("Wrong key in SecretKeyFactory");
        } catch (NoSuchAlgorithmException e) {
            response.getWriter().println("Wrong hashing algorithm");
        }
    }

    private User retrieveUserFromRequest(HttpServletRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isPremium = Boolean.parseBoolean(request.getParameter("isPremium"));

        PasswordAuth passwordAuth = new PasswordAuth();
        password = passwordAuth.hashPassword(password);

        return new User(username, password, isPremium);

    }
}

