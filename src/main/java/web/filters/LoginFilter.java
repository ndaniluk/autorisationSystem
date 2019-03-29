package web.filters;

import repositories.DbOperations;
import web.PasswordAuth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static repositories.ConnectDatabase.userDB;

@WebFilter("/login")
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DbOperations dbOperations = new DbOperations();
        PasswordAuth passwordAuth = new PasswordAuth();
        int userIndex = dbOperations.checkUsername(username);

        if (userIndex >= 0) {
            try {
                if (passwordAuth.validatePassword(userDB.get(userIndex).getPassword(), password)) {
                    chain.doFilter(request, response);
                } else
                    response.getWriter().println("Invalid password");
            } catch (InvalidKeySpecException e) {
                response.getWriter().println("Wrong key in SecretKeyFactory");
            } catch (NoSuchAlgorithmException e) {
                response.getWriter().println("Wrong hashing algorithm");
            }
        } else
            response.getWriter().println("Invalid username");
    }

    public void destroy() {

    }
}
