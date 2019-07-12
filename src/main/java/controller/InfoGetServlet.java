package controller;

import constants.Path;
import model.entity.UserAccount;
import model.entity.UserInfo;
import service.UserInfoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/infoGet")
public class InfoGetServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 9055632404847275937L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserAccount account = (UserAccount) request.getSession().getAttribute("loginedUser");
        UserInfo info = UserInfoService.findInfoByID(account.getId());
        request.setAttribute("infoForm", info);

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.INFO_GET);

        dispatcher.forward(request, response);
    }
}