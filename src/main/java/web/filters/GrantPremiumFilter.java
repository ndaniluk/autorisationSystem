package web.filters;

import models.User;
import repositories.DbConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebFilter("/grantPremium")
public class GrantPremiumFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = request.getParameter("username");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        User user;
        try {
            if(user.checkUsername(username) >= 0)
                chain.doFilter(request, response);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public void destroy() {

    }
}
