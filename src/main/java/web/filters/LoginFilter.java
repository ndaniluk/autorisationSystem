package web.filters;

import auth.PasswordAuth;
import models.User;
import models.UserUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static repositories.DbConnection.userDB;

@WebFilter("/login")
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserUtils userUtils = new UserUtils();
        try {
            User user = userUtils.retrieveUserFromRequest((HttpServletRequest) request);

            PasswordAuth passwordAuth = new PasswordAuth();
            int userIndex = userUtils.checkUsername(user.getUsername());

            if (userIndex >= 0) {
                if (passwordAuth.validatePassword(userDB.get(userIndex).getPassword(), user.getPassword())) {
                    chain.doFilter(request, response);
                } else
                    response.getWriter().println("Invalid password");
            } else
                response.getWriter().println("Invalid username");

        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {

    }
}
