package web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter("/register")
public class RegisterFilter implements Filter {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

            if (username.length() > 0) {
                if (password.length() >= 8) {
                    if (password.equals(repassword)) {
                        if (validate(email)) {
                            try {
                                chain.doFilter(request, response);
                            } catch (ServletException e) {
                                response.getWriter().println("Can't find /register servlet");
                            }
                        } else
                            response.getWriter().println("Invalid email address");
                    } else
                        response.getWriter().println("Passwords don't match");
                } else
                    response.getWriter().println("Provided password has to be at least 8 characters long");
            } else
                response.getWriter().println("Empty username provided");
    }

    public void destroy() {

    }
}
