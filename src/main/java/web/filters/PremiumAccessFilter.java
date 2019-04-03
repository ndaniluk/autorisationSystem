package web.filters;

import models.User;
import models.UserUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static repositories.DbConnection.userDB;

@WebFilter("/premiumZone")
public class PremiumAccessFilter implements Filter {
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        User user = (User) session.getAttribute("userObject");

        UserUtils userUtils = new UserUtils();
        int userIndex = userUtils.checkUsername(user.getUsername());

        if (userDB.get(userIndex).isPremium() || userDB.get(userIndex).isAdmin())
            chain.doFilter(request, response);
        else
            httpResponse.sendRedirect("/userProfile");
    }

    public void destroy() {

    }
}
