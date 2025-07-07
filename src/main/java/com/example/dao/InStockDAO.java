package com.example.dao;

import com.example.entity.InStock;
import com.example.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InStockDAO {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public List<InStock> listInStocks() {
        List<InStock> inStocks = new ArrayList<>();
        String sql = "SELECT i.*, m.name as materialName, s.SupplierName " +
                "FROM InStock i " +
                "JOIN Material m ON i.materialId = m.id " +
                "JOIN Supplier s ON i.supplierId = s.SupplierID " +
                "ORDER BY i.inDate DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                InStock inStock = new InStock();
                inStock.setId(rs.getInt("id"));
                inStock.setMaterialId(rs.getInt("materialId"));
                inStock.setSupplierId(rs.getInt("supplierId"));
                inStock.setQuantity(rs.getInt("quantity"));
                inStock.setPrice(rs.getBigDecimal("price"));
                inStock.setHandler(rs.getString("handler"));
                inStock.setKeeper(rs.getString("keeper"));
                inStock.setInDate(rs.getDate("inDate"));
                inStock.setMaterialName(rs.getString("materialName"));
                inStock.setSupplierName(rs.getString("SupplierName"));
                inStocks.add(inStock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inStocks;
    }

    public void addInStock(InStock inStock) {
        String inStockNo = "IN_" + sdf.format(new Date()) + "_" + UUID.randomUUID().toString().substring(0, 6);

        String sql = "INSERT INTO InStock(inStockNo, materialId, supplierId, quantity, price, " +
                "handler, keeper, inDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, inStockNo);
                pstmt.setInt(2, inStock.getMaterialId());
                pstmt.setInt(3, inStock.getSupplierId());
                pstmt.setInt(4, inStock.getQuantity());
                pstmt.setBigDecimal(5, inStock.getPrice());
                pstmt.setString(6, inStock.getHandler());
                pstmt.setString(7, inStock.getKeeper());
                pstmt.setDate(8, inStock.getInDate());

                pstmt.executeUpdate();

                updateStock(conn, inStock.getMaterialId(), inStock.getQuantity(), 1);

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加入库记录失败", e);
        }
    }

    public void deleteInStock(Integer id) {
        String sql = "DELETE FROM InStock WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStock(Connection conn, Integer materialId, Integer quantity, int type) throws SQLException {
        String sql = "UPDATE Material SET stock = stock + ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, quantity * type);
            pstmt.setInt(2, materialId);
            pstmt.executeUpdate();
        }
    }

    public InStock getInStockById(Integer id) {
        InStock inStock = null;
        String sql = "SELECT i.*, m.name as materialName, s.SupplierName " +
                "FROM InStock i " +
                "JOIN Material m ON i.materialId = m.id " +
                "JOIN Supplier s ON i.supplierId = s.SupplierID " +
                "WHERE i.id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    inStock = new InStock();
                    inStock.setId(rs.getInt("id"));
                    inStock.setMaterialId(rs.getInt("materialId"));
                    inStock.setSupplierId(rs.getInt("supplierId"));
                    inStock.setQuantity(rs.getInt("quantity"));
                    inStock.setPrice(rs.getBigDecimal("price"));
                    inStock.setHandler(rs.getString("handler"));
                    inStock.setKeeper(rs.getString("keeper"));
                    inStock.setInDate(rs.getDate("inDate"));
                    inStock.setMaterialName(rs.getString("materialName"));
                    inStock.setSupplierName(rs.getString("SupplierName"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inStock;
    }
}