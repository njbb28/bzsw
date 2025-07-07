package com.example.dao;

import com.example.entity.Supplier;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    public List<Supplier> listSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT SupplierID AS id, SupplierName, ContactName, Phone, Address, Email " +
                "FROM Supplier ORDER BY SupplierID DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getInt("id"));
                supplier.setSupplierName(rs.getString("SupplierName"));
                supplier.setContactName(rs.getString("ContactName"));
                supplier.setPhone(rs.getString("Phone"));
                supplier.setAddress(rs.getString("Address"));
                supplier.setEmail(rs.getString("Email"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询供应商列表失败", e);
        }
        return suppliers;
    }

    public void addSupplier(Supplier supplier) {
        String sql = "INSERT INTO Supplier (SupplierName, ContactName, Phone, Address, Email) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, supplier.getSupplierName());
            pstmt.setString(2, supplier.getContactName());
            pstmt.setString(3, supplier.getPhone());
            pstmt.setString(4, supplier.getAddress());
            pstmt.setString(5, supplier.getEmail());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    supplier.setSupplierId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加供应商失败", e);
        }
    }

    public Supplier getSupplierById(Integer id) {
        String sql = "SELECT SupplierID AS id, SupplierName, ContactName, Phone, Address, Email " +
                "FROM Supplier WHERE SupplierID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Supplier supplier = new Supplier();
                    supplier.setSupplierId(rs.getInt("id"));
                    supplier.setSupplierName(rs.getString("SupplierName"));
                    supplier.setContactName(rs.getString("ContactName"));
                    supplier.setPhone(rs.getString("Phone"));
                    supplier.setAddress(rs.getString("Address"));
                    supplier.setEmail(rs.getString("Email"));
                    return supplier;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取供应商失败", e);
        }
        return null;
    }

    public void updateSupplier(Supplier supplier) {
        String sql = "UPDATE Supplier SET SupplierName = ?, ContactName = ?, Phone = ?, " +
                "Address = ?, Email = ? WHERE SupplierID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, supplier.getSupplierName());
            pstmt.setString(2, supplier.getContactName());
            pstmt.setString(3, supplier.getPhone());
            pstmt.setString(4, supplier.getAddress());
            pstmt.setString(5, supplier.getEmail());
            pstmt.setInt(6, supplier.getSupplierId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("更新供应商失败", e);
        }
    }

    public void deleteSupplier(Integer id) {
        String sql = "DELETE FROM Supplier WHERE SupplierID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除供应商失败", e);
        }
    }
}