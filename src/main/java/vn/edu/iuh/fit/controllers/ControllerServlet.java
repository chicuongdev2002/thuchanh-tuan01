package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Role;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GantAccessRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet  extends HttpServlet {


    private AccountRepository AccRe = new AccountRepository();
    private RoleRepository RollRe = new RoleRepository();
    private GantAccessRepository GrantRe = new GantAccessRepository();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void setHeaderContent(HttpServletRequest req, Account acc, String nameRole) {
        req.setAttribute("user", acc);
        req.setAttribute("rolename", nameRole);
    }

    private void setContentAdmin(HttpServletRequest req) {
        ArrayList<Account> accounts = AccRe.getAccount();
        ArrayList<Role> roles = RollRe.getRoles();
        System.out.println(accounts);
        req.setAttribute("users", accounts);
        req.setAttribute("roles", roles);
    }

    private void CRUD(HttpServletRequest req, HttpServletResponse resp, String action) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session != null) {

            String roleName = (String) session.getAttribute("roleName");
            Account acc = (Account) session.getAttribute("userLogin");


            switch (action) {
                case "Create":
                    setHeaderContent(req, acc, roleName);
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    String email = req.getParameter("email");
                    String phone = req.getParameter("phone");
//                    int status =Integer.parseInt(req.getParameter("status")) ;
                    String roleID = req.getParameter("role");
                    System.out.println(roleID);
                    String uid = req.getParameter("id");
                    Account acc_new = new Account(uid, username, password, email, phone, 1);


                    Boolean result_add_account = AccRe.addAccount(acc_new);
                    Boolean result_add_Grant_Access = GrantRe.addGrantAccess(acc_new, new Role(roleID));
                    if (result_add_account && result_add_Grant_Access) {
                        setHeaderContent(req, acc, roleName);
                        setContentAdmin(req);
                        req.getRequestDispatcher("WEB-INF/trangchu.jsp").forward(req, resp);
                    }
                    resp.getWriter().println("them that bai !!!");


                    break;
                case "Delete":

                    break;

                case "Update":

                    break;
                default:

            }


        } else {
            resp.getWriter().println("please login !!!");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");


        if (action.equalsIgnoreCase("login")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Account acc = AccRe.checkAccount(username, password);

            if (acc == null) {
                resp.getWriter().println("wrong password or username !!");
            } else {


                String uid = acc.getAccountId();
                Role role = GrantRe.getRoleByUserId(uid);


                if (role != null) {


                    String nameRole = role.getRoleName();
                    System.out.println(nameRole);
                    //store session
                    HttpSession session = req.getSession();
                    session.setAttribute("userLogin", acc);
                    session.setAttribute("roleName", nameRole);
                    //store table log

                    //
                    setHeaderContent(req, acc, nameRole);
                    setContentAdmin(req);


                    if (nameRole.equalsIgnoreCase("administrator")) {

                        req.getRequestDispatcher("WEB-INF/trangchu.jsp").forward(req, resp);
                    } else {

                        req.getRequestDispatcher("WEB-INF/trangchu.jsp").forward(req, resp);
                    }


                } else {
                    resp.getWriter().println("can not access !! !");
                }

            }
        } else if (action.equalsIgnoreCase("addUser")) {
            CRUD(req, resp, "Create");
        } else if (action.equalsIgnoreCase("removeUser")) {
            CRUD(req, resp, "Delete");
        } else if (action.equalsIgnoreCase("UpdateUser")) {
            CRUD(req, resp, "Update");
        }


    }
}
