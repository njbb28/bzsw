package com.example.entity;

public class Department {
    private Integer id;
    private String deptName;
    private String leaderName;
    private String contactPhone;

    // 构造方法
    public Department() {
    }

    public Department(Integer id, String deptName, String leaderName, String contactPhone) {
        this.id = id;
        this.deptName = deptName;
        this.leaderName = leaderName;
        this.contactPhone = contactPhone;
    }

    // getter和setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", leaderName='" + leaderName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                '}';
    }
}