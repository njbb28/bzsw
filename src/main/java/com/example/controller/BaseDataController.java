package com.example.controller;

import com.example.dao.DepartmentDAO;
import com.example.dao.SupplierDAO;
import com.example.dao.WarehouseDAO;
import com.example.entity.Department;
import com.example.entity.Supplier;
import com.example.entity.Warehouse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/baseData/*")
public class BaseDataController extends HttpServlet {
    private SupplierDAO supplierDAO = new SupplierDAO();
    private WarehouseDAO warehouseDAO = new WarehouseDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();
        if (path == null) {
            resp.sendRedirect(req.getContextPath() + "/baseData/list");
            return;
        }

        switch (path) {
            case "/list":
                listBaseData(req, resp);
                break;
            case "/supplier":
                listSuppliers(req, resp);
                break;
            case "/warehouse":
                listWarehouses(req, resp);
                break;
            case "/department":
                listDepartments(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "路径不存在");
        }
    }

    private void listBaseData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Supplier> suppliers = supplierDAO.listSuppliers();
        List<Warehouse> warehouses = warehouseDAO.listWarehouses();
        List<Department> departments = departmentDAO.listDepartments();

        req.setAttribute("suppliers", suppliers);
        req.setAttribute("warehouses", warehouses);
        req.setAttribute("departments", departments);
        req.getRequestDispatcher("/WEB-INF/baseData/list.jsp").forward(req, resp);
    }

    private void listSuppliers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Supplier> suppliers = supplierDAO.listSuppliers();
        req.setAttribute("suppliers", suppliers);
        req.getRequestDispatcher("/WEB-INF/supplier/list.jsp").forward(req, resp);
    }

    private void listWarehouses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Warehouse> warehouses = warehouseDAO.listWarehouses();
        req.setAttribute("warehouses", warehouses);
        req.getRequestDispatcher("/WEB-INF/warehouse/list.jsp").forward(req, resp);
    }

    private void listDepartments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentDAO.listDepartments();
        req.setAttribute("departments", departments);
        req.getRequestDispatcher("/WEB-INF/department/list.jsp").forward(req, resp);
    }
}