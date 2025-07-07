package com.example.dao;

import com.example.entity.Department;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    // 查询所有部门
    public List<Department> listDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT id, deptName, leaderName, contactPhone " +
                "FROM Department ORDER BY id DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Department dept = new Department();
                dept.setId(rs.getInt("id"));
                dept.setDeptName(rs.getString("deptName"));
                dept.setLeaderName(rs.getString("leaderName"));
                dept.setContactPhone(rs.getString("contactPhone"));
                departments.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询部门列表失败", e);
        }
        return departments;
    }

    // 根据ID查询部门
    public Department getDepartmentById(Integer id) {
        Department dept = null;
        String sql = "SELECT id, deptName, leaderName, contactPhone " +
                "FROM Department WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dept = new Department();
                    dept.setId(rs.getInt("id"));
                    dept.setDeptName(rs.getString("deptName"));
                    dept.setLeaderName(rs.getString("leaderName"));
                    dept.setContactPhone(rs.getString("contactPhone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取部门失败", e);
        }
        return dept;
    }

    // 添加部门
    public void addDepartment(Department department) {
        String sql = "INSERT INTO Department (deptName, leaderName, contactPhone) " +
                "VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, department.getDeptName());
            pstmt.setString(2, department.getLeaderName());
            pstmt.setString(3, department.getContactPhone());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    department.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加部门失败", e);
        }
    }

    // 更新部门
    public void updateDepartment(Department department) {
        String sql = "UPDATE Department SET deptName = ?, leaderName = ?, contactPhone = ? " +
                "WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, department.getDeptName());
            pstmt.setString(2, department.getLeaderName());
            pstmt.setString(3, department.getContactPhone());
            pstmt.setInt(4, department.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("更新部门失败", e);
        }
    }

    // 删除部门
    public void deleteDepartment(Integer id) {
        String sql = "DELETE FROM Department WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除部门失败", e);
        }
    }
}