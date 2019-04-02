package auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Session {
    public static boolean isSessionActive(HttpServletRequest request, HttpServletResponse response) {
        return request.getSession(false) != null;
    }
}
