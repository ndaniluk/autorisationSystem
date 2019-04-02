package web.servlets;

import models.User;
import models.UserUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserUtils userUtils = new UserUtils();

        try {
            User user = userUtils.retrieveUserFromRequest(request);
            session.setAttribute("userObject", user);
            response.sendRedirect("/userProfile");
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
