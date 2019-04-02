package web.filters;

import models.UserUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static auth.Session.isSessionActive;

@WebFilter("/grantPremium")
public class GrantPremiumFilter implements Filter {
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

            if (request.getParameter("username") != null) {
                String username = request.getParameter("username");

                UserUtils userUtils = new UserUtils();
                int userIndex = userUtils.checkUsername(username);

                if (userIndex >= 0) {
                    request.setAttribute("id", userIndex);
                    chain.doFilter(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }
        }

    public void destroy() {

    }
}
