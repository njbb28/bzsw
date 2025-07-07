package com.example.controller;

import com.example.dao.*;
import com.example.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/outStock/*")
public class OutStockController extends HttpServlet {
    private OutStockDAO outStockDAO = new OutStockDAO();
    private MaterialDAO materialDAO = new MaterialDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private UserDAO userDAO = new UserDAO();

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
                listOutStocks(req, resp);
                break;
            case "/form":
                showForm(req, resp);
                break;
            case "/view":
                viewOutStock(req, resp);
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
                addOutStock(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "路径不存在: " + path);
        }
    }

    private void listOutStocks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OutStock> outStocks = outStockDAO.listOutStocks();
        req.setAttribute("outStocks", outStocks);
        // 修正 JSP 路径到 WEB-INF
        req.getRequestDispatcher("/WEB-INF/outStock/list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Material> materials = materialDAO.listMaterials();
        List<Department> departments = departmentDAO.listDepartments();
        List<User> users = userDAO.listUsers();

        req.setAttribute("materials", materials);
        req.setAttribute("depts", departments);
        req.setAttribute("users", users);

        java.util.Date today = new java.util.Date();
        req.setAttribute("defaultDate", new java.sql.Date(today.getTime()));
        // 修正 JSP 路径到 WEB-INF
        req.getRequestDispatcher("/WEB-INF/outStock/form.jsp").forward(req, resp);
    }

    private void viewOutStock(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        OutStock outStock = outStockDAO.getOutStockById(id);
        req.setAttribute("outStock", outStock);
        // 修正 JSP 路径到 WEB-INF
        req.getRequestDispatcher("/WEB-INF/outStock/view.jsp").forward(req, resp);
    }

    private void addOutStock(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            int materialId = Integer.parseInt(req.getParameter("materialId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            Date outDate = Date.valueOf(req.getParameter("outDate"));
            int deptId = Integer.parseInt(req.getParameter("deptId"));
            int requesterId = Integer.parseInt(req.getParameter("requesterId"));
            int keeperId = Integer.parseInt(req.getParameter("keeperId"));
            String remark = req.getParameter("remark");

            Material material = materialDAO.getMaterialById(materialId);
            if (material.getStock() < quantity) {
                req.setAttribute("error", "库存不足，无法出库");
                showForm(req, resp);
                return;
            }

            OutStock outStock = new OutStock();
            outStock.setMaterialId(materialId);
            outStock.setQuantity(quantity);
            outStock.setOutDate(outDate);
            outStock.setDeptId(deptId);
            outStock.setRequesterId(requesterId);
            outStock.setKeeperId(keeperId);
            outStock.setRemark(remark);

            outStockDAO.addOutStock(outStock);
            resp.sendRedirect(req.getContextPath() + "/outStock/list");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "出库失败: " + e.getMessage());
            showForm(req, resp);
        }
    }
}