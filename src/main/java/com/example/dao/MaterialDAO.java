package com.example.dao;

import com.example.entity.Material;
import com.example.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    // 获取所有物资列表
    public List<Material> listMaterials() {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM [Material]"; // 保留方括号，适用于某些数据库表名需要引号的情况

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Material material = new Material();
                material.setId(rs.getInt("id"));
                material.setName(rs.getString("name"));
                material.setSpecs(rs.getString("specs"));
                material.setManufacturer(rs.getString("manufacturer"));
                material.setPrice(rs.getBigDecimal("price"));
                material.setStock(rs.getInt("stock"));
                material.setMinStock(rs.getInt("minStock"));
                material.setMaxStock(rs.getInt("maxStock"));
                material.setCreateTime(rs.getTimestamp("createTime"));
                materials.add(material);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materials;
    }

    // 根据关键词搜索物资
    public List<Material> searchMaterials(String keyword) {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM [Material] WHERE name LIKE ? OR specs LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Material material = new Material();
                    material.setId(rs.getInt("id"));
                    material.setName(rs.getString("name"));
                    material.setSpecs(rs.getString("specs"));
                    material.setManufacturer(rs.getString("manufacturer"));
                    material.setPrice(rs.getBigDecimal("price"));
                    material.setStock(rs.getInt("stock"));
                    material.setMinStock(rs.getInt("minStock"));
                    material.setMaxStock(rs.getInt("maxStock"));
                    material.setCreateTime(rs.getTimestamp("createTime"));
                    materials.add(material);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materials;
    }

    // 统计物资总数
    public int countMaterials() {
        String sql = "SELECT COUNT(*) FROM Material";

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

    // 根据ID获取单个物资
    public Material getMaterialById(Integer id) {
        Material material = null;
        String sql = "SELECT * FROM Material WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    material = new Material();
                    material.setId(rs.getInt("id"));
                    material.setName(rs.getString("name"));
                    material.setSpecs(rs.getString("specs"));
                    material.setManufacturer(rs.getString("manufacturer"));
                    material.setPrice(rs.getBigDecimal("price"));
                    material.setStock(rs.getInt("stock"));
                    material.setMinStock(rs.getInt("minStock"));
                    material.setMaxStock(rs.getInt("maxStock"));
                    material.setCreateTime(rs.getTimestamp("createTime"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return material;
    }

    // 添加新物资
    public void addMaterial(Material material) {
        String sql = "INSERT INTO Material(name, specs, manufacturer, price, minStock, maxStock, stock, createTime) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, material.getName());
            pstmt.setString(2, material.getSpecs());
            pstmt.setString(3, material.getManufacturer());
            pstmt.setBigDecimal(4, material.getPrice());
            pstmt.setInt(5, material.getMinStock());
            pstmt.setInt(6, material.getMaxStock());
            pstmt.setInt(7, material.getStock());
            pstmt.setTimestamp(8, material.getCreateTime());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加物资失败", e);
        }
    }

    // 删除物资
    public void deleteMaterial(Integer id) {
        String sql = "DELETE FROM Material WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除物资失败", e);
        }
    }

    // 更新物资信息
    public void updateMaterial(Material material) {
        String sql = "UPDATE Material SET name=?, specs=?, manufacturer=?, price=?, minStock=?, maxStock=?, stock=? " +
                "WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, material.getName());
            pstmt.setString(2, material.getSpecs());
            pstmt.setString(3, material.getManufacturer());
            pstmt.setBigDecimal(4, material.getPrice());
            pstmt.setInt(5, material.getMinStock());
            pstmt.setInt(6, material.getMaxStock());
            pstmt.setInt(7, material.getStock());
            pstmt.setInt(8, material.getId());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新物资失败", e);
        }
    }
}