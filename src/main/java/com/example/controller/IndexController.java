package com.example.controller;

import com.example.dao.MaterialDAO;
import com.example.dao.OutStockDAO;
import com.example.entity.Material;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexController extends HttpServlet {
    private MaterialDAO materialDAO = new MaterialDAO();
    private OutStockDAO outStockDAO = new OutStockDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 设置编码（统一规范）
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 2. 业务逻辑（保持不变）
        int materialCount = materialDAO.countMaterials();
        int monthlyOutStockCount = outStockDAO.countMonthlyOutStocks();
        int pendingPlanCount = 7; // 示例数据，需实际对接 DAO

        req.setAttribute("materialCount", materialCount);
        req.setAttribute("monthlyOutStockCount", monthlyOutStockCount);
        req.setAttribute("pendingPlanCount", pendingPlanCount);

        // 3. 转发路径修正：JSP 放到 WEB-INF 下
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}