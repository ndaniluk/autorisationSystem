package web.servlets;

import models.User;
import models.UserUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static repositories.DbConnection.userDB;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserUtils userUtils = new UserUtils();
        try {
            User user = userUtils.retrieveUserFromRequest(request);
            userDB.add(user);
            response.sendRedirect("/index.jsp");
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

