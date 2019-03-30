package web.filters;

import models.User;
import models.UserUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/premiumZone", "premiumZone.jsp"})
public class PremiumAccessFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserUtils userUtils = new UserUtils();
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        if (request.getParameter("userObject") != null) {
            User user = (User)session.getAttribute("userObject");
            if(user.isPremium())
                chain.doFilter(request, response);
            else
                httpResponse.sendRedirect("/userProfile");
        } else {
            httpResponse.sendRedirect("/login");
        }
    }

    public void destroy() {

    }
}
