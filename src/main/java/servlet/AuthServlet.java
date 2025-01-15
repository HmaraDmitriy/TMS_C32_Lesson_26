package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String action = req.getParameter("action");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            resp.getWriter().write("Invalid input!");
            return;
        }

        if ("login".equals(action)) {
            if (UserRepository.isValid(username, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                req.getRequestDispatcher("/page/todo-list.html").forward(req, resp);
            } else {
                resp.getWriter().write("Invalid username or password.");
            }
        } else if ("register".equals(action)) {
            if (UserRepository.isValid(username, password)) {
                resp.getWriter().write("User " + username + " already exists!");
            } else {
                UserRepository.addUser(username, password);
                req.setAttribute("username", username);
                req.getRequestDispatcher("/registration.html").forward(req, resp);
            }
        } else {
            resp.getWriter().write("Invalid action.");
        }
    }
}
