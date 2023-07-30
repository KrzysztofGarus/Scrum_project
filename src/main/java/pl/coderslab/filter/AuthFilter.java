package pl.coderslab.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String requestURI = req.getRequestURI();
        if (requestURI.startsWith("/app")) {  // URI filtering instead of servlet address
            HttpSession session = req.getSession();
            if (session.getAttribute("userId") != null) {
                chain.doFilter(req, res);
            } else {
                res.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            chain.doFilter(req, res);
        }
    }
}
