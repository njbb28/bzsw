package com.example.controller;

import com.example.dao.WarehouseDAO;
import com.example.entity.Warehouse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/warehouse/*")
public class WarehouseController extends HttpServlet {
    private WarehouseDAO warehouseDAO = new WarehouseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();
        if (path == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少路径参数");
            return;
        }

        switch (path) {
            case "/list":
                listWarehouses(req, resp);
                break;
            case "/form":
                showForm(req, resp);
                break;
            case "/view":
                viewWarehouse(req, resp);
                break;
            case "/delete":
                deleteWarehouse(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "路径不存在: " + path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();
        if (path == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少路径参数");
            return;
        }

        switch (path) {
            case "/add":
                addWarehouse(req, resp);
                break;
            case "/update":
                updateWarehouse(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "路径不存在: " + path);
        }
    }

    private void listWarehouses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Warehouse> warehouses = warehouseDAO.listWarehouses();
        req.setAttribute("warehouses", warehouses);
        // 修正 JSP 路径到 WEB-INF
        req.getRequestDispatcher("/WEB-INF/warehouse/list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Warehouse warehouse = warehouseDAO.getWarehouseById(id);
            req.setAttribute("warehouse", warehouse);
        }
        // 修正 JSP 路径到 WEB-INF
        req.getRequestDispatcher("/WEB-INF/warehouse/form.jsp").forward(req, resp);
    }

    private void viewWarehouse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Warehouse warehouse = warehouseDAO.getWarehouseById(id);
        req.setAttribute("warehouse", warehouse);
        // 修正 JSP 路径到 WEB-INF
        req.getRequestDispatcher("/WEB-INF/warehouse/view.jsp").forward(req, resp);
    }

    private void addWarehouse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName(req.getParameter("warehouseName"));

        warehouseDAO.addWarehouse(warehouse);
        resp.sendRedirect(req.getContextPath() + "/warehouse/list");
    }

    private void updateWarehouse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(Integer.parseInt(req.getParameter("id")));
        warehouse.setWarehouseName(req.getParameter("warehouseName"));

        warehouseDAO.updateWarehouse(warehouse);
        resp.sendRedirect(req.getContextPath() + "/warehouse/list");
    }

    private void deleteWarehouse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        warehouseDAO.deleteWarehouse(id);
        resp.sendRedirect(req.getContextPath() + "/warehouse/list");
    }
}