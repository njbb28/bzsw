package com.example.controller;

import com.example.dao.StockCheckDAO;
import com.example.entity.StockCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/stockCheck/*")
public class StockCheckController extends HttpServlet {
    private StockCheckDAO stockCheckDAO = new StockCheckDAO();

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
                listStockChecks(req, resp);
                break;
            case "/form":
                showForm(req, resp);
                break;
            case "/view":
                viewStockCheck(req, resp);
                break;
            case "/delete":
                deleteStockCheck(req, resp);
                break;
            case "/process":
                processStockCheck(req, resp);
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
                addStockCheck(req, resp);
                break;
            case "/update":
                updateStockCheck(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "路径不存在: " + path);
        }
    }

    private void listStockChecks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StockCheck> checks = stockCheckDAO.listStockChecks();
        req.setAttribute("checks", checks);
        req.getRequestDispatcher("/WEB-INF/stockcheck/list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            StockCheck check = stockCheckDAO.getStockCheckById(id);
            req.setAttribute("check", check);
        }

        // 这里需要添加获取物资列表和仓库列表的逻辑
        // 示例：
        // req.setAttribute("materials", materialDAO.listMaterials());
        // req.setAttribute("warehouses", warehouseDAO.listWarehouses());

        req.getRequestDispatcher("/WEB-INF/stockcheck/form.jsp").forward(req, resp);
    }

    private void viewStockCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        StockCheck check = stockCheckDAO.getStockCheckById(id);
        req.setAttribute("check", check);
        req.getRequestDispatcher("/WEB-INF/stockcheck/view.jsp").forward(req, resp);
    }

    private void addStockCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StockCheck check = new StockCheck();
        check.setMaterialId(Integer.parseInt(req.getParameter("materialId")));
        check.setWarehouseId(Integer.parseInt(req.getParameter("warehouseId")));
        check.setActualQuantity(Integer.parseInt(req.getParameter("actualQuantity")));
        check.setSystemQuantity(Integer.parseInt(req.getParameter("systemQuantity")));
        check.setDifference(Integer.parseInt(req.getParameter("difference")));
        check.setCheckDate(new java.sql.Date(System.currentTimeMillis()));
        check.setCheckerId(Integer.parseInt(req.getParameter("checkerId")));
        check.setStatus("待处理");
        check.setRemark(req.getParameter("remark"));

        stockCheckDAO.addStockCheck(check);
        resp.sendRedirect(req.getContextPath() + "/stockCheck/list");
    }

    private void updateStockCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StockCheck check = new StockCheck();
        check.setId(Integer.parseInt(req.getParameter("id")));
        check.setMaterialId(Integer.parseInt(req.getParameter("materialId")));
        check.setWarehouseId(Integer.parseInt(req.getParameter("warehouseId")));
        check.setActualQuantity(Integer.parseInt(req.getParameter("actualQuantity")));
        check.setSystemQuantity(Integer.parseInt(req.getParameter("systemQuantity")));
        check.setDifference(Integer.parseInt(req.getParameter("difference")));
        check.setCheckDate(new java.sql.Date(System.currentTimeMillis()));
        check.setCheckerId(Integer.parseInt(req.getParameter("checkerId")));
        check.setStatus(req.getParameter("status"));
        check.setRemark(req.getParameter("remark"));
        check.setCheckNo(req.getParameter("checkNo")); // 获取已有的checkNo

        stockCheckDAO.updateStockCheck(check);
        resp.sendRedirect(req.getContextPath() + "/stockCheck/list");
    }

    private void deleteStockCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        stockCheckDAO.deleteStockCheck(id);
        resp.sendRedirect(req.getContextPath() + "/stockCheck/list");
    }

    private void processStockCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");

        StockCheck check = stockCheckDAO.getStockCheckById(id);
        check.setStatus(status);

        stockCheckDAO.updateStockCheck(check);
        resp.sendRedirect(req.getContextPath() + "/stockCheck/list");
    }
}