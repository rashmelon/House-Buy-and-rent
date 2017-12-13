package Servlets;

import Entities.User;
import Models.UserModel;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel userModel = new UserModel();
        User user = userModel.selectByEmailAndPassword(request.getAttribute("email").toString()
                , request.getAttribute("password").toString());
//        User user = new User(1, "ahmed", "ahmed@hamada","01141118545","hamada st","sayed");
        if (user == null) {
            request.setAttribute("error", true);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
        else{
            HttpSession session = request.getSession(true);
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("address", user.getAddress());
            session.setAttribute("phone", user.getPhone());
            response.sendRedirect("index.jsp");
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Profile");
//            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}