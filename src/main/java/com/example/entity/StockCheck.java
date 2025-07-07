package com.example.entity;

import java.util.Date;

public class StockCheck {
    private Integer id;
    private Integer materialId;
    private String materialName;
    private Integer warehouseId;
    private String warehouseName;
    private Integer actualQuantity;
    private Integer systemQuantity;
    private Integer difference;
    private Date checkDate;
    private Integer checkerId;
    private String status;
    private String remark;
    private String processResult;  // 添加字段
    private Date processDate;      // 添加字段
    private String checkNo;

    // getter 和 setter 方法
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getMaterialId() { return materialId; }
    public void setMaterialId(Integer materialId) { this.materialId = materialId; }

    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }

    public Integer getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Integer warehouseId) { this.warehouseId = warehouseId; }

    public String getWarehouseName() { return warehouseName; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }

    public Integer getActualQuantity() { return actualQuantity; }
    public void setActualQuantity(Integer actualQuantity) { this.actualQuantity = actualQuantity; }

    public Integer getSystemQuantity() { return systemQuantity; }
    public void setSystemQuantity(Integer systemQuantity) { this.systemQuantity = systemQuantity; }

    public Integer getDifference() { return difference; }
    public void setDifference(Integer difference) { this.difference = difference; }

    public Date getCheckDate() { return checkDate; }
    public void setCheckDate(Date checkDate) { this.checkDate = checkDate; }

    public Integer getCheckerId() { return checkerId; }
    public void setCheckerId(Integer checkerId) { this.checkerId = checkerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    // 添加 processResult 的 getter/setter
    public String getProcessResult() { return processResult; }
    public void setProcessResult(String processResult) { this.processResult = processResult; }

    // 添加 processDate 的 getter/setter
    public Date getProcessDate() { return processDate; }
    public void setProcessDate(Date processDate) { this.processDate = processDate; }

    public String getCheckNo() { return checkNo; }
    public void setCheckNo(String checkNo) { this.checkNo = checkNo; }
}