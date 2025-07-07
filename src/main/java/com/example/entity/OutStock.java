package com.example.entity;

import java.sql.Date;

public class OutStock {
    private Integer id;
    private String outStockNo;
    private Integer materialId;
    private String materialName;
    private Integer quantity;
    private Date outDate;
    private Integer deptId;
    private String deptName;
    private Integer requesterId;
    private String requesterName;
    private Integer keeperId;
    private String keeperName;
    private String remark;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getOutStockNo() { return outStockNo; }
    public void setOutStockNo(String outStockNo) { this.outStockNo = outStockNo; }
    public Integer getMaterialId() { return materialId; }
    public void setMaterialId(Integer materialId) { this.materialId = materialId; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Date getOutDate() { return outDate; }
    public void setOutDate(Date outDate) { this.outDate = outDate; }
    public Integer getDeptId() { return deptId; }
    public void setDeptId(Integer deptId) { this.deptId = deptId; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
    public Integer getRequesterId() { return requesterId; }
    public void setRequesterId(Integer requesterId) { this.requesterId = requesterId; }
    public String getRequesterName() { return requesterName; }
    public void setRequesterName(String requesterName) { this.requesterName = requesterName; }
    public Integer getKeeperId() { return keeperId; }
    public void setKeeperId(Integer keeperId) { this.keeperId = keeperId; }
    public String getKeeperName() { return keeperName; }
    public void setKeeperName(String keeperName) { this.keeperName = keeperName; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}