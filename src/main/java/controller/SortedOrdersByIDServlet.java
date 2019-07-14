package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import model.entity.Order;
import service.OrderService;
import utils.constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/byID")
public class SortedOrdersByIDServlet extends HttpServlet {
    private static final long serialVersionUID = -4261734414574007077L;

    public SortedOrdersByIDServlet() { super();}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> list = new ArrayList<>();
        request.getSession().setAttribute("errorMassage", "");
        try {
            list = OrderService.findAllSortedByID();
        } catch (DaoException e) {
            GlobalExceptionHandler.handleException(e, request);
        }
        request.getSession().setAttribute("list", list);
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.LIST_ORDERS);
        dispatcher.forward(request, response);

    }
}
