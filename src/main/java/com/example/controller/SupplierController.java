package com.example.controller;

import com.example.dao.SupplierDAO;
import com.example.entity.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/supplier/*")
public class SupplierController extends HttpServlet {
    private SupplierDAO supplierDAO = new SupplierDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();
        if (path == null) {
            resp.sendRedirect(req.getContextPath() + "/supplier/list");
            return;
        }

        switch (path) {
            case "/list":
                listSuppliers(req, resp);
                break;
            case "/form":
                showForm(req, resp);
                break;
            case "/view":
                viewSupplier(req, resp);
                break;
            case "/delete":
                deleteSupplier(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();
        if (path == null || !path.equals("/save")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String action = req.getParameter("action");
        if ("add".equals(action)) {
            addSupplier(req, resp);
        } else if ("edit".equals(action)) {
            updateSupplier(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void listSuppliers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Supplier> suppliers = supplierDAO.listSuppliers();
        req.setAttribute("suppliers", suppliers);
        req.getRequestDispatcher("/WEB-INF/supplier/list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    Supplier supplier = supplierDAO.getSupplierById(id);
                    if (supplier != null) {
                        req.setAttribute("supplier", supplier);
                    }
                } catch (NumberFormatException e) {
                    // 忽略错误
                }
            }
        }
        req.setAttribute("action", action);
        req.getRequestDispatcher("/WEB-INF/supplier/form.jsp").forward(req, resp);
    }

    private void viewSupplier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Supplier supplier = supplierDAO.getSupplierById(id);
                if (supplier != null) {
                    req.setAttribute("supplier", supplier);
                }
            } catch (NumberFormatException e) {
                // 忽略错误
            }
        }
        req.getRequestDispatcher("/WEB-INF/supplier/view.jsp").forward(req, resp);
    }

    private void deleteSupplier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                supplierDAO.deleteSupplier(id);
            } catch (NumberFormatException e) {
                // 忽略错误
            }
        }
        resp.sendRedirect(req.getContextPath() + "/supplier/list");
    }

    private void addSupplier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Supplier supplier = new Supplier();
        supplier.setSupplierName(req.getParameter("supplierName"));
        supplier.setContactName(req.getParameter("contactName"));
        supplier.setPhone(req.getParameter("phone"));
        supplier.setAddress(req.getParameter("address"));
        supplier.setEmail(req.getParameter("email"));

        try {
            supplierDAO.addSupplier(supplier);
            resp.sendRedirect(req.getContextPath() + "/supplier/list");
        } catch (Exception e) {
            req.setAttribute("error", "新增供应商失败：" + e.getMessage());
            req.setAttribute("supplier", supplier);
            req.setAttribute("action", "add");
            req.getRequestDispatcher("/WEB-INF/supplier/form.jsp").forward(req, resp);
        }
    }

    private void updateSupplier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Supplier supplier = supplierDAO.getSupplierById(id);
                if (supplier != null) {
                    supplier.setSupplierName(req.getParameter("supplierName"));
                    supplier.setContactName(req.getParameter("contactName"));
                    supplier.setPhone(req.getParameter("phone"));
                    supplier.setAddress(req.getParameter("address"));
                    supplier.setEmail(req.getParameter("email"));
                    supplierDAO.updateSupplier(supplier);
                }
            } catch (NumberFormatException e) {
                // 忽略错误
            }
        }
        resp.sendRedirect(req.getContextPath() + "/supplier/list");
    }
}