package vn.edu.iuh.fit.controllers;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GantAccessRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;

@WebServlet(name = "LoginControl",value = "/login-control")
public class LoginControl  extends  HttpServlet{

    private AccountRepository AccRe = new AccountRepository();
    private RoleRepository RollRe = new RoleRepository();
    private GantAccessRepository GrantRe = new GantAccessRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String action=req.getParameter("action");
      if(action.equalsIgnoreCase("login")){
          String username = req.getParameter("username");
          String password = req.getParameter("password");
          System.out.println(username + " " + password);
          Account a = AccRe.checkAccount(username, password);

          if (a == null) {
              resp.getWriter().println("Thong tin dang nhap khong chinh xac");
              req.getRequestDispatcher("index.jsp").forward(req,resp);
          } else {
              System.out.println("Login thanh cong");
             resp.sendRedirect("dashboard.jsp");

          }
      }
    }
}
