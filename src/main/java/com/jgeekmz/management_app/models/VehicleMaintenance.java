package com.jgeekmz.management_app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VehicleMaintenance extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "vehicleid", insertable = false, updatable = false)
    private Vehicle vehicle;
    private Integer vehicleid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String price;

    @ManyToOne
    @JoinColumn(name = "supplierid", insertable = false, updatable = false)
    private Supplier supplier;
    private Integer supplierid;

    private String remarks;

    public VehicleMaintenance() {
    }

    public VehicleMaintenance(String createdBy, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate, int id, Vehicle vehicle, Integer vehicleid, Date startDate, Date endDate, String price, Supplier supplier, Integer supplierid, String remarks) {
        super(createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.id = id;
        this.vehicle = vehicle;
        this.vehicleid = vehicleid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.supplier = supplier;
        this.supplierid = supplierid;
        this.remarks = remarks;
    }

    public VehicleMaintenance(int id, Vehicle vehicle, Integer vehicleid, Date startDate, Date endDate, String price, Supplier supplier, Integer supplierid, String remarks) {
        this.id = id;
        this.vehicle = vehicle;
        this.vehicleid = vehicleid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.supplier = supplier;
        this.supplierid = supplierid;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "VehicleMaintenance{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", vehicleid=" + vehicleid +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price='" + price + '\'' +
                ", supplier=" + supplier +
                ", supplierid=" + supplierid +
                ", remarks='" + remarks + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", lastModifiedBy=" + lastModifiedBy +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}