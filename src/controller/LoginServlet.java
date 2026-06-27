package controller;

import auth.AuthService;
import model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService =
            new AuthService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        User user =
                authService.authenticate(
                        username,
                        password
                );

        if (user == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/jsp/login.jsp?error=1"
            );

            return;
        }

        HttpSession session =
                request.getSession();

        session.setAttribute(
                "user",
                user
        );

        switch (user.getRole()) {

            case "ADMIN":
    response.sendRedirect(
        request.getContextPath()
        + "/dashboard"
    );
    break;

            case "HOD":
                response.sendRedirect(
                        request.getContextPath()
                        + "/jsp/hod-dashboard.jsp"
                );
                break;

            case "PROFESSOR":
                response.sendRedirect(
                        request.getContextPath()
                        + "/jsp/professor-dashboard.jsp"
                );
                break;

            case "EXAM_STAFF":
                response.sendRedirect(
                        request.getContextPath()
                        + "/jsp/exam-dashboard.jsp"
                );
                break;
        }
    }
}