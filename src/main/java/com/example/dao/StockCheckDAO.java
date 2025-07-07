package com.example.dao;

import com.example.entity.StockCheck;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StockCheckDAO {

    // 添加库存盘点记录
    public void addStockCheck(StockCheck check) {
        String checkNo = "CK" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 6);

        String sql = "INSERT INTO StockCheck " +
                "(materialId, warehouseId, actualQuantity, systemQuantity, difference, " +
                "checkDate, checkerId, status, remark, processResult, processDate, checkNo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, check.getMaterialId());
            pstmt.setInt(2, check.getWarehouseId());
            pstmt.setInt(3, check.getActualQuantity());
            pstmt.setInt(4, check.getSystemQuantity());
            pstmt.setInt(5, check.getDifference());
            pstmt.setDate(6, new java.sql.Date(check.getCheckDate().getTime()));
            pstmt.setInt(7, check.getCheckerId());
            pstmt.setString(8, check.getStatus());
            pstmt.setString(9, check.getRemark());
            pstmt.setString(10, check.getProcessResult());
            pstmt.setTimestamp(11, new java.sql.Timestamp(check.getProcessDate() != null ? check.getProcessDate().getTime() : System.currentTimeMillis()));
            pstmt.setString(12, checkNo);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加库存盘点记录失败", e);
        }
    }

    // 查询所有库存盘点记录（完全匹配数据库表结构）
    public List<StockCheck> listStockChecks() {
        List<StockCheck> checks = new ArrayList<>();
        String sql = "SELECT " +
                "sc.id, " +
                "sc.materialId, " +
                "sc.warehouseId, " +
                "sc.actualQuantity, " +
                "sc.systemQuantity, " +
                "sc.difference, " +
                "sc.checkDate, " +
                "sc.checkerId, " +
                "sc.status, " +
                "sc.remark, " +
                "sc.processResult, " +
                "sc.processDate, " +
                "sc.checkNo, " +
                "m.name AS materialName, " +  // 假设 Material 表名称列为 name
                "w.name AS warehouseName " +    // 假设 Warehouse 表名称列为 name
                "FROM StockCheck sc " +
                "JOIN Material m ON sc.materialId = m.id " +  // Material 表主键为 id
                "JOIN Warehouse w ON sc.warehouseId = w.id " +  // Warehouse 表主键为 id
                "ORDER BY sc.checkDate DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                StockCheck check = new StockCheck();
                check.setId(rs.getInt("id"));
                check.setMaterialId(rs.getInt("materialId"));
                check.setMaterialName(rs.getString("materialName"));
                check.setWarehouseId(rs.getInt("warehouseId"));
                check.setWarehouseName(rs.getString("warehouseName"));
                check.setActualQuantity(rs.getInt("actualQuantity"));
                check.setSystemQuantity(rs.getInt("systemQuantity"));
                check.setDifference(rs.getInt("difference"));
                check.setCheckDate(rs.getDate("checkDate"));
                check.setCheckerId(rs.getInt("checkerId"));
                check.setStatus(rs.getString("status"));
                check.setRemark(rs.getString("remark"));
                check.setProcessResult(rs.getString("processResult"));
                check.setProcessDate(rs.getTimestamp("processDate"));
                check.setCheckNo(rs.getString("checkNo"));
                checks.add(check);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询库存盘点记录失败", e);
        }
        return checks;
    }

    // 根据 ID 获取库存盘点记录
    public StockCheck getStockCheckById(Integer id) {
        String sql = "SELECT " +
                "sc.id, " +
                "sc.materialId, " +
                "sc.warehouseId, " +
                "sc.actualQuantity, " +
                "sc.systemQuantity, " +
                "sc.difference, " +
                "sc.checkDate, " +
                "sc.checkerId, " +
                "sc.status, " +
                "sc.remark, " +
                "sc.processResult, " +
                "sc.processDate, " +
                "sc.checkNo, " +
                "m.name AS materialName, " +
                "w.name AS warehouseName " +
                "FROM StockCheck sc " +
                "JOIN Material m ON sc.materialId = m.id " +
                "JOIN Warehouse w ON sc.warehouseId = w.id " +
                "WHERE sc.id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    StockCheck check = new StockCheck();
                    check.setId(rs.getInt("id"));
                    check.setMaterialId(rs.getInt("materialId"));
                    check.setMaterialName(rs.getString("materialName"));
                    check.setWarehouseId(rs.getInt("warehouseId"));
                    check.setWarehouseName(rs.getString("warehouseName"));
                    check.setActualQuantity(rs.getInt("actualQuantity"));
                    check.setSystemQuantity(rs.getInt("systemQuantity"));
                    check.setDifference(rs.getInt("difference"));
                    check.setCheckDate(rs.getDate("checkDate"));
                    check.setCheckerId(rs.getInt("checkerId"));
                    check.setStatus(rs.getString("status"));
                    check.setRemark(rs.getString("remark"));
                    check.setProcessResult(rs.getString("processResult"));
                    check.setProcessDate(rs.getTimestamp("processDate"));
                    check.setCheckNo(rs.getString("checkNo"));
                    return check;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取库存盘点记录失败", e);
        }
        return null;
    }

    // 更新库存盘点记录
    public void updateStockCheck(StockCheck check) {
        String sql = "UPDATE StockCheck " +
                "SET materialId = ?, " +
                "warehouseId = ?, " +
                "actualQuantity = ?, " +
                "systemQuantity = ?, " +
                "difference = ?, " +
                "checkDate = ?, " +
                "checkerId = ?, " +
                "status = ?, " +
                "remark = ?, " +
                "processResult = ?, " +
                "processDate = ?, " +
                "checkNo = ? " +
                "WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, check.getMaterialId());
            pstmt.setInt(2, check.getWarehouseId());
            pstmt.setInt(3, check.getActualQuantity());
            pstmt.setInt(4, check.getSystemQuantity());
            pstmt.setInt(5, check.getDifference());
            pstmt.setDate(6, new java.sql.Date(check.getCheckDate().getTime()));
            pstmt.setInt(7, check.getCheckerId());
            pstmt.setString(8, check.getStatus());
            pstmt.setString(9, check.getRemark());
            pstmt.setString(10, check.getProcessResult());
            pstmt.setTimestamp(11, new java.sql.Timestamp(check.getProcessDate() != null ? check.getProcessDate().getTime() : System.currentTimeMillis()));
            pstmt.setString(12, check.getCheckNo());
            pstmt.setInt(13, check.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("更新库存盘点记录失败", e);
        }
    }

    // 删除库存盘点记录
    public void deleteStockCheck(Integer id) {
        String sql = "DELETE FROM StockCheck WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除库存盘点记录失败", e);
        }
    }
}