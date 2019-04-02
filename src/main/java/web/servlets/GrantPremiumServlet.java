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

@WebServlet("/grantPremium")
public class GrantPremiumServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/grantPremium.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        UserUtils userUtils = new UserUtils();
        try {
            User user = userUtils.retrieveUserFromRequest(request);
            if(user.isPremium())
                response.getWriter().println("User is already VIP");
            else{
                userUtils.changePremiumState(true, user.getUsername());
                request.getSession().setAttribute("isPremium", true);
                response.getWriter().println("Granted VIP privileges to " + user.getUsername());
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
