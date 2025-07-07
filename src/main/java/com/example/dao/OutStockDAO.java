package com.example.dao;

import com.example.entity.OutStock;
import com.example.util.DBUtil;
import com.example.entity.Material; // 添加这一行

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OutStockDAO {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public List<OutStock> listOutStocks() {
        List<OutStock> outStocks = new ArrayList<>();
        String sql = "SELECT o.*, m.name as materialName, d.deptName, " +
                "u1.username as requesterName, u2.username as keeperName " +
                "FROM OutStock o " +
                "JOIN Material m ON o.materialId = m.id " +
                "JOIN Department d ON o.deptId = d.id " +
                "LEFT JOIN [User] u1 ON o.requesterId = u1.id " +
                "LEFT JOIN [User] u2 ON o.keeperId = u2.id " +
                "ORDER BY o.outDate DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                OutStock outStock = new OutStock();
                outStock.setId(rs.getInt("id"));
                outStock.setOutStockNo(rs.getString("outStockNo"));
                outStock.setMaterialId(rs.getInt("materialId"));
                outStock.setMaterialName(rs.getString("materialName"));
                outStock.setQuantity(rs.getInt("quantity"));
                outStock.setOutDate(rs.getDate("outDate"));
                outStock.setDeptId(rs.getInt("deptId"));
                outStock.setDeptName(rs.getString("deptName"));
                outStock.setRequesterId(rs.getInt("requesterId"));
                outStock.setRequesterName(rs.getString("requesterName"));
                outStock.setKeeperId(rs.getInt("keeperId"));
                outStock.setKeeperName(rs.getString("keeperName"));
                outStock.setRemark(rs.getString("remark"));
                outStocks.add(outStock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询出库记录失败", e);
        }
        return outStocks;
    }

    public void addOutStock(OutStock outStock) {
        String outStockNo = "OUT_" + sdf.format(new Date()) + "_" + UUID.randomUUID().toString().substring(0, 6);

        String sql = "INSERT INTO OutStock(outStockNo, materialId, quantity, outDate, deptId, " +
                "requesterId, keeperId, remark) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, outStockNo);
                pstmt.setInt(2, outStock.getMaterialId());
                pstmt.setInt(3, outStock.getQuantity());
                pstmt.setDate(4, outStock.getOutDate());
                pstmt.setInt(5, outStock.getDeptId());
                pstmt.setInt(6, outStock.getRequesterId());
                pstmt.setInt(7, outStock.getKeeperId());
                pstmt.setString(8, outStock.getRemark());

                pstmt.executeUpdate();

                MaterialDAO materialDAO = new MaterialDAO();
                Material material = materialDAO.getMaterialById(outStock.getMaterialId());
                if (material == null) {
                    conn.rollback();
                    throw new RuntimeException("未找到对应的物资信息，无法更新库存");
                }

                if (material.getStock() < outStock.getQuantity()) {
                    conn.rollback();
                    throw new RuntimeException("库存不足，无法出库。当前库存: " + material.getStock());
                }

                material.setStock(material.getStock() - outStock.getQuantity());
                materialDAO.updateMaterial(material);

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加出库记录失败", e);
        }
    }

    public int countMonthlyOutStocks() {
        String sql = "SELECT COUNT(*) FROM OutStock " +
                "WHERE MONTH(outDate) = MONTH(GETDATE()) AND YEAR(outDate) = YEAR(GETDATE())";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public OutStock getOutStockById(Integer id) {
        OutStock outStock = null;
        String sql = "SELECT o.*, m.name as materialName, d.deptName, " +
                "u1.username as requesterName, u2.username as keeperName " +
                "FROM OutStock o " +
                "JOIN Material m ON o.materialId = m.id " +
                "JOIN Department d ON o.deptId = d.id " +
                "LEFT JOIN [User] u1 ON o.requesterId = u1.id " +
                "LEFT JOIN [User] u2 ON o.keeperId = u2.id " +
                "WHERE o.id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    outStock = new OutStock();
                    outStock.setId(rs.getInt("id"));
                    outStock.setOutStockNo(rs.getString("outStockNo"));
                    outStock.setMaterialId(rs.getInt("materialId"));
                    outStock.setMaterialName(rs.getString("materialName"));
                    outStock.setQuantity(rs.getInt("quantity"));
                    outStock.setOutDate(rs.getDate("outDate"));
                    outStock.setDeptId(rs.getInt("deptId"));
                    outStock.setDeptName(rs.getString("deptName"));
                    outStock.setRequesterId(rs.getInt("requesterId"));
                    outStock.setRequesterName(rs.getString("requesterName"));
                    outStock.setKeeperId(rs.getInt("keeperId"));
                    outStock.setKeeperName(rs.getString("keeperName"));
                    outStock.setRemark(rs.getString("remark"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outStock;
    }
}