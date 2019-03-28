package web.filters;

import repositories.DbOperations;
import web.PasswordAuth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DbOperations dbOperations = new DbOperations();
        PasswordAuth passwordAuth = new PasswordAuth();

        if (dbOperations.checkUsername(username)) {
            if (passwordAuth.validatePassword(password)) {
                chain.doFilter(request, response);
            } else
                response.getWriter().println("Invalid password");
        } else
            response.getWriter().println("Invalid username");
    }

    public void destroy() {

    }
}
