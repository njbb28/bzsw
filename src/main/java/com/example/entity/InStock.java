package com.example.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class InStock {
    private Integer id;
    private Integer materialId;
    private Integer supplierId;
    private Integer quantity;
    private BigDecimal price;
    private String handler;
    private String keeper;
    private Date inDate;
    private String materialName;
    private String supplierName;
    private String inStockNo;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getMaterialId() { return materialId; }
    public void setMaterialId(Integer materialId) { this.materialId = materialId; }

    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getHandler() { return handler; }
    public void setHandler(String handler) { this.handler = handler; }

    public String getKeeper() { return keeper; }
    public void setKeeper(String keeper) { this.keeper = keeper; }

    public Date getInDate() { return inDate; }
    public void setInDate(Date inDate) { this.inDate = inDate; }

    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getInStockNo() { return inStockNo; }
    public void setInStockNo(String inStockNo) { this.inStockNo = inStockNo; }
}