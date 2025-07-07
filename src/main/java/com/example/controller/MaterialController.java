package com.example.controller;

import com.example.dao.MaterialDAO;
import com.example.entity.Material;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/material/*")
public class MaterialController extends HttpServlet {
    private MaterialDAO materialDAO = new MaterialDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();
        System.out.println("GET请求路径: " + path);

        if (path == null || "/".equals(path)) {
            resp.sendRedirect(req.getContextPath() + "/material/list");
            return;
        }

        try {
            switch (path) {
                case "/list":
                    listMaterials(req, resp);
                    break;
                case "/form":
                    showAddForm(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/view":
                    viewMaterial(req, resp);
                    break;
                case "/delete":
                    deleteMaterial(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "路径不存在: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "处理请求失败: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();
        System.out.println("POST请求路径: " + path);

        try {
            switch (path) {
                case "/add":
                    addMaterial(req, resp);
                    break;
                case "/update":
                    updateMaterial(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "路径不存在: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "处理请求失败: " + e.getMessage());
        }
    }

    private void listMaterials(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Material> materials;

        try {
            if (keyword != null && !keyword.trim().isEmpty()) {
                materials = materialDAO.searchMaterials(keyword);
            } else {
                materials = materialDAO.listMaterials();
            }

            req.setAttribute("materials", materials);
            req.getRequestDispatcher("/WEB-INF/material/list.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "获取物资列表失败: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/material/form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少ID参数");
            return;
        }

        try {
            Integer id = Integer.parseInt(idParam);
            Material material = materialDAO.getMaterialById(id);

            if (material == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "物资不存在: " + id);
                return;
            }

            req.setAttribute("material", material);
            req.getRequestDispatcher("/WEB-INF/material/form.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "无效的ID格式: " + idParam);
        }
    }

    private void viewMaterial(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少ID参数");
            return;
        }

        try {
            Integer id = Integer.parseInt(idParam);
            Material material = materialDAO.getMaterialById(id);

            if (material == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "物资不存在: " + id);
                return;
            }

            req.setAttribute("material", material);
            req.getRequestDispatcher("/WEB-INF/material/view.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "无效的ID格式: " + idParam);
        }
    }

    private void addMaterial(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // 获取并验证参数
            String name = req.getParameter("name");
            String specs = req.getParameter("specs");
            String manufacturer = req.getParameter("manufacturer");
            String priceParam = req.getParameter("price");
            String minStockParam = req.getParameter("minStock");
            String maxStockParam = req.getParameter("maxStock");
            String stockParam = req.getParameter("stock");

            // 参数验证
            if (name == null || name.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "物资名称不能为空");
                return;
            }

            if (priceParam == null || priceParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "价格不能为空");
                return;
            }

            if (minStockParam == null || minStockParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "最低库存不能为空");
                return;
            }

            if (maxStockParam == null || maxStockParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "最高库存不能为空");
                return;
            }

            if (stockParam == null || stockParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "当前库存不能为空");
                return;
            }

            // 转换参数类型
            BigDecimal price = new BigDecimal(priceParam);
            int minStock = Integer.parseInt(minStockParam);
            int maxStock = Integer.parseInt(maxStockParam);
            int stock = Integer.parseInt(stockParam);

            // 业务验证
            if (minStock > maxStock) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "最低库存不能大于最高库存");
                return;
            }

            Material material = new Material();
            material.setName(name);
            material.setSpecs(specs);
            material.setManufacturer(manufacturer);
            material.setPrice(price);
            material.setMinStock(minStock);
            material.setMaxStock(maxStock);
            material.setStock(stock);
            material.setCreateTime(new Timestamp(System.currentTimeMillis()));

            materialDAO.addMaterial(material);

            // 添加成功消息
            String encodedMsg = URLEncoder.encode("添加物资成功", "UTF-8");
            resp.sendRedirect(req.getContextPath() + "/material/list?msg=" + encodedMsg);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "数值格式错误: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "添加物资失败: " + e.getMessage());
        }
    }

    private void updateMaterial(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // 获取并验证参数
            String idParam = req.getParameter("id");
            String name = req.getParameter("name");
            String specs = req.getParameter("specs");
            String manufacturer = req.getParameter("manufacturer");
            String priceParam = req.getParameter("price");
            String minStockParam = req.getParameter("minStock");
            String maxStockParam = req.getParameter("maxStock");
            String stockParam = req.getParameter("stock");

            // 参数验证
            if (idParam == null || idParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "无效的ID");
                return;
            }

            if (name == null || name.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "物资名称不能为空");
                return;
            }

            if (priceParam == null || priceParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "价格不能为空");
                return;
            }

            if (minStockParam == null || minStockParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "最低库存不能为空");
                return;
            }

            if (maxStockParam == null || maxStockParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "最高库存不能为空");
                return;
            }

            if (stockParam == null || stockParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "当前库存不能为空");
                return;
            }

            // 转换参数类型
            Integer id = Integer.parseInt(idParam);
            BigDecimal price = new BigDecimal(priceParam);
            int minStock = Integer.parseInt(minStockParam);
            int maxStock = Integer.parseInt(maxStockParam);
            int stock = Integer.parseInt(stockParam);

            // 业务验证
            if (minStock > maxStock) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "最低库存不能大于最高库存");
                return;
            }

            Material material = new Material();
            material.setId(id);
            material.setName(name);
            material.setSpecs(specs);
            material.setManufacturer(manufacturer);
            material.setPrice(price);
            material.setMinStock(minStock);
            material.setMaxStock(maxStock);
            material.setStock(stock);

            materialDAO.updateMaterial(material);

            // 添加成功消息
            String encodedMsg = URLEncoder.encode("更新物资成功", "UTF-8");
            resp.sendRedirect(req.getContextPath() + "/material/list?msg=" + encodedMsg);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "数值格式错误: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新物资失败: " + e.getMessage());
        }
    }

    private void deleteMaterial(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少ID参数");
            return;
        }

        try {
            Integer id = Integer.parseInt(idParam);
            materialDAO.deleteMaterial(id);

            // 添加成功消息
            String encodedMsg = URLEncoder.encode("删除物资成功", "UTF-8");
            resp.sendRedirect(req.getContextPath() + "/material/list?msg=" + encodedMsg);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "无效的ID格式");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "删除物资失败: " + e.getMessage());
        }
    }
}