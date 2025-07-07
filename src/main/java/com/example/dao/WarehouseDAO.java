package com.example.dao;

import com.example.entity.Warehouse;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {
    public List<Warehouse> listWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT * FROM [Warehouse]"; // 添加方括号
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setId(rs.getInt("id"));
                warehouse.setWarehouseName(rs.getString("warehouseName"));
                warehouses.add(warehouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouses;
    }

    public void addWarehouse(Warehouse warehouse) {
        String sql = "INSERT INTO Warehouse(warehouseName) VALUES(?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, warehouse.getWarehouseName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateWarehouse(Warehouse warehouse) {
        String sql = "UPDATE Warehouse SET warehouseName = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, warehouse.getWarehouseName());
            pstmt.setInt(2, warehouse.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWarehouse(Integer id) {
        String sql = "DELETE FROM Warehouse WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Warehouse getWarehouseById(Integer id) {
        Warehouse warehouse = null;
        String sql = "SELECT * FROM Warehouse WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    warehouse = new Warehouse();
                    warehouse.setId(rs.getInt("id"));
                    warehouse.setWarehouseName(rs.getString("warehouseName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouse;
    }
}