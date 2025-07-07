package com.example.dao;

import com.example.entity.PurchasePlan;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PurchasePlanDAO {
    // 添加采购计划（无需修改，确认 planDate 列名正确）
    public void addPurchasePlan(PurchasePlan plan) {
        String planNo = "PLAN_" + new java.text.SimpleDateFormat("yyyyMMdd").format(new Date()) + "_" + UUID.randomUUID().toString().substring(0, 6);

        String sql = "INSERT INTO PurchasePlan(planNo, materialId, materialName, " +
                "requiredQuantity, planDate, status, remark) " +
                "VALUES(?, ?, ?, ?, ?, '未处理', ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, planNo);
            pstmt.setInt(2, plan.getMaterialId());
            pstmt.setString(3, plan.getMaterialName());
            pstmt.setInt(4, plan.getRequiredQuantity());
            pstmt.setDate(5, new java.sql.Date(plan.getPlanDate().getTime()));
            pstmt.setString(6, plan.getRemark());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // 可以考虑记录日志
        }
    }

    // 更新采购计划（无需修改，确认 planDate 列名正确）
    public void updatePurchasePlan(PurchasePlan plan) {
        String sql = "UPDATE PurchasePlan SET materialName=?, requiredQuantity=?, " +
                "planDate=?, status=?, remark=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plan.getMaterialName());
            pstmt.setInt(2, plan.getRequiredQuantity());
            pstmt.setDate(3, plan.getPlanDate()); // 确认数据库列名是 planDate
            pstmt.setString(4, plan.getStatus());
            pstmt.setString(5, plan.getRemark());
            pstmt.setInt(6, plan.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // 可以考虑记录日志
        }
    }

    // 删除采购计划（无需修改）
    public void deletePurchasePlan(Integer id) {
        String sql = "DELETE FROM PurchasePlan WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // 可以考虑记录日志
        }
    }

    // 获取单个采购计划（无需修改，依赖 listPurchasePlans 的修正）
    public PurchasePlan getPurchasePlanById(Integer id) {
        PurchasePlan plan = null;
        String sql = "SELECT * FROM PurchasePlan WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    plan = new PurchasePlan();
                    plan.setId(rs.getInt("id"));
                    plan.setPlanNo(rs.getString("planNo"));
                    plan.setMaterialId(rs.getInt("materialId"));
                    plan.setMaterialName(rs.getString("materialName"));
                    plan.setRequiredQuantity(rs.getInt("requiredQuantity"));
                    plan.setPlanDate(rs.getDate("planDate")); // 确认数据库列名是 planDate
                    plan.setStatus(rs.getString("status"));
                    plan.setRemark(rs.getString("remark"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 可以考虑记录日志
        }
        return plan;
    }

    // 获取所有采购计划（确认 planDate 列名正确）
    public List<PurchasePlan> listPurchasePlans() {
        List<PurchasePlan> plans = new ArrayList<>();
        String sql = "SELECT * FROM PurchasePlan ORDER BY planDate DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PurchasePlan plan = new PurchasePlan();
                plan.setId(rs.getInt("id"));
                plan.setPlanNo(rs.getString("planNo"));
                plan.setMaterialId(rs.getInt("materialId"));
                plan.setMaterialName(rs.getString("materialName"));
                plan.setRequiredQuantity(rs.getInt("requiredQuantity"));
                plan.setPlanDate(rs.getDate("planDate")); // 确认数据库列名是 planDate
                plan.setStatus(rs.getString("status"));
                plan.setRemark(rs.getString("remark"));
                plans.add(plan);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 可以考虑记录日志
        }
        return plans;
    }
}