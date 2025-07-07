package com.example.controller;

import com.example.dao.DepartmentDAO;
import com.example.entity.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/department/*")
public class DepartmentController extends HttpServlet {
    private DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();
        if (path == null) {
            resp.sendRedirect(req.getContextPath() + "/department/list");
            return;
        }

        switch (path) {
            case "/list":
                listDepartments(req, resp);
                break;
            case "/form":
                showForm(req, resp);
                break;
            case "/view":
                viewDepartment(req, resp);
                break;
            case "/delete":
                deleteDepartment(req, resp);
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
            addDepartment(req, resp);
        } else if ("edit".equals(action)) {
            updateDepartment(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // 部门列表
    private void listDepartments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentDAO.listDepartments();
        req.setAttribute("departments", departments);
        req.getRequestDispatcher("/WEB-INF/department/list.jsp").forward(req, resp);
    }

    // 显示表单
    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    Department department = departmentDAO.getDepartmentById(id);
                    if (department != null) {
                        req.setAttribute("department", department);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        req.setAttribute("action", action);
        req.getRequestDispatcher("/WEB-INF/department/form.jsp").forward(req, resp);
    }

    // 查看部门
    private void viewDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Department department = departmentDAO.getDepartmentById(id);
                if (department != null) {
                    req.setAttribute("department", department);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("/WEB-INF/department/view.jsp").forward(req, resp);
    }

    // 删除部门
    private void deleteDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                departmentDAO.deleteDepartment(id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/department/list");
    }

    // 新增部门
    private void addDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = new Department();
        department.setDeptName(req.getParameter("deptName"));
        department.setLeaderName(req.getParameter("leaderName"));
        department.setContactPhone(req.getParameter("contactPhone"));

        try {
            departmentDAO.addDepartment(department);
            resp.sendRedirect(req.getContextPath() + "/department/list");
        } catch (Exception e) {
            req.setAttribute("error", "新增部门失败：" + e.getMessage());
            req.setAttribute("department", department);
            req.setAttribute("action", "add");
            req.getRequestDispatcher("/WEB-INF/department/form.jsp").forward(req, resp);
        }
    }

    // 更新部门
    private void updateDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Department department = departmentDAO.getDepartmentById(id);
                if (department != null) {
                    department.setDeptName(req.getParameter("deptName"));
                    department.setLeaderName(req.getParameter("leaderName"));
                    department.setContactPhone(req.getParameter("contactPhone"));
                    departmentDAO.updateDepartment(department);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/department/list");
    }
}