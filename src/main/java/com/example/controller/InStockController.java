package com.example.controller;

import com.example.dao.InStockDAO;
import com.example.dao.MaterialDAO;
import com.example.dao.SupplierDAO;
import com.example.entity.InStock;
import com.example.entity.Material;
import com.example.entity.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/inStock/*")
public class InStockController extends HttpServlet {
    private InStockDAO inStockDAO = new InStockDAO();
    private MaterialDAO materialDAO = new MaterialDAO();
    private SupplierDAO supplierDAO = new SupplierDAO();

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
                listInStocks(req, resp);
                break;
            case "/form":
                showForm(req, resp);
                break;
            case "/view":
                viewInStock(req, resp);
                break;
            case "/delete":
                deleteInStock(req, resp);
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
                addInStock(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "路径不存在: " + path);
        }
    }

    private void listInStocks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<InStock> inStocks = inStockDAO.listInStocks();
        req.setAttribute("inStocks", inStocks);
        // 修正路径为小写 instock（与实际目录一致）
        req.getRequestDispatcher("/WEB-INF/instock/list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Material> materials = materialDAO.listMaterials();
        List<Supplier> suppliers = supplierDAO.listSuppliers();
        req.setAttribute("materials", materials);
        req.setAttribute("suppliers", suppliers);
        // 修正路径为小写 instock
        req.getRequestDispatcher("/WEB-INF/instock/form.jsp").forward(req, resp);
    }

    private void viewInStock(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        InStock inStock = inStockDAO.getInStockById(id);
        req.setAttribute("inStock", inStock);
        // 修正路径为小写 instock
        req.getRequestDispatcher("/WEB-INF/instock/view.jsp").forward(req, resp);
    }

    private void addInStock(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InStock inStock = new InStock();
        inStock.setMaterialId(Integer.parseInt(req.getParameter("materialId")));
        inStock.setSupplierId(Integer.parseInt(req.getParameter("supplierId")));
        inStock.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        inStock.setPrice(new java.math.BigDecimal(req.getParameter("price")));
        inStock.setHandler(req.getParameter("handler"));
        inStock.setKeeper(req.getParameter("keeper"));

        try {
            java.util.Date utilDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("inDate"));
            inStock.setInDate(new java.sql.Date(utilDate.getTime()));
        } catch (Exception e) {
            inStock.setInDate(new java.sql.Date(System.currentTimeMillis()));
        }

        inStockDAO.addInStock(inStock);
        resp.sendRedirect(req.getContextPath() + "/inStock/list");
    }

    private void deleteInStock(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        inStockDAO.deleteInStock(id);
        resp.sendRedirect(req.getContextPath() + "/inStock/list");
    }
}