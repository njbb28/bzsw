package com.example.controller;

import com.example.dao.PurchasePlanDAO;
import com.example.entity.PurchasePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/purchasePlan/*")
public class PurchasePlanController extends HttpServlet {
    private PurchasePlanDAO purchasePlanDAO = new PurchasePlanDAO();

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
                listPurchasePlans(req, resp);
                break;
            case "/form":
                showForm(req, resp);
                break;
            case "/view":
                viewPurchasePlan(req, resp);
                break;
            case "/delete":
                deletePurchasePlan(req, resp);
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
                addPurchasePlan(req, resp);
                break;
            case "/update":
                updatePurchasePlan(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "路径不存在: " + path);
        }
    }

    private void listPurchasePlans(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PurchasePlan> plans = purchasePlanDAO.listPurchasePlans();
        req.setAttribute("plans", plans);
        // 修正 JSP 路径到 WEB-INF
        req.getRequestDispatcher("/WEB-INF/purchasePlan/list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            PurchasePlan plan = purchasePlanDAO.getPurchasePlanById(id);
            req.setAttribute("plan", plan);
        }
        // 修正 JSP 路径到 WEB-INF
        req.getRequestDispatcher("/WEB-INF/purchasePlan/form.jsp").forward(req, resp);
    }

    private void viewPurchasePlan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        PurchasePlan plan = purchasePlanDAO.getPurchasePlanById(id);
        req.setAttribute("plan", plan);
        // 修正 JSP 路径到 WEB-INF
        req.getRequestDispatcher("/WEB-INF/purchasePlan/view.jsp").forward(req, resp);
    }

    private void addPurchasePlan(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PurchasePlan plan = new PurchasePlan();
        plan.setMaterialId(Integer.parseInt(req.getParameter("materialId")));
        plan.setMaterialName(req.getParameter("materialName"));
        plan.setRequiredQuantity(Integer.parseInt(req.getParameter("requiredQuantity")));
        plan.setPlanDate(new java.sql.Date(System.currentTimeMillis()));
        plan.setStatus("未处理");
        plan.setRemark(req.getParameter("remark"));

        purchasePlanDAO.addPurchasePlan(plan);
        resp.sendRedirect(req.getContextPath() + "/purchasePlan/list");
    }

    private void updatePurchasePlan(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PurchasePlan plan = new PurchasePlan();
        plan.setId(Integer.parseInt(req.getParameter("id")));
        plan.setMaterialId(Integer.parseInt(req.getParameter("materialId")));
        plan.setMaterialName(req.getParameter("materialName"));
        plan.setRequiredQuantity(Integer.parseInt(req.getParameter("requiredQuantity")));
        plan.setPlanDate(new java.sql.Date(System.currentTimeMillis()));
        plan.setStatus(req.getParameter("status"));
        plan.setRemark(req.getParameter("remark"));

        purchasePlanDAO.updatePurchasePlan(plan);
        resp.sendRedirect(req.getContextPath() + "/purchasePlan/list");
    }

    private void deletePurchasePlan(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        purchasePlanDAO.deletePurchasePlan(id);
        resp.sendRedirect(req.getContextPath() + "/purchasePlan/list");
    }
}