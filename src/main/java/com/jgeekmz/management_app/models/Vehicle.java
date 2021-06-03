package com.jgeekmz.management_app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "vehicletypeid", insertable = false, updatable = false)
    private VehicleType vehicleType;
    private Integer vehicletypeid;

    private String vehicleNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String registrationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String acquisitionDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "vehiclemakeid", insertable = false, updatable = false)
    private VehicleMake vehicleMake;
    private Integer vehiclemakeid;

    private String power;
    private String fuelCapacity;
    @ManyToOne
    @JoinColumn(name = "vehiclestatusid", insertable = false, updatable = false)
    private VehicleStatus vehicleStatus;
    private Integer vehiclestatusid;

    private String netWeight;

    @ManyToOne
    @JoinColumn(name = "employeeid", insertable = false, updatable = false)
    private Employee inCharge;
    private Integer employeeid;

    @ManyToOne
    @JoinColumn(name = "vehiclemodelid", insertable = false, updatable = false)
    private VehicleModel vehicleModel;
    private Integer vehiclemodelid;

    @ManyToOne
    @JoinColumn(name = "locationid", insertable = false, updatable = false)
    private Location currentLocation;
    private Integer locationid;

    private String remarks;

    public Vehicle(int id, String name, VehicleType vehicleType, Integer vehicletypeid, String vehicleNumber, String registrationDate, String acquisitionDate, String description, VehicleMake vehicleMake, Integer vehiclemakeid, String power, String fuelCapacity, VehicleStatus vehicleStatus, Integer vehiclestatusid, String netWeight, Employee inCharge, Integer employeeid, VehicleModel vehicleModel, Integer vehiclemodelid, Location currentLocation, Integer locationid, String remarks) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.vehicletypeid = vehicletypeid;
        this.vehicleNumber = vehicleNumber;
        this.registrationDate = registrationDate;
        this.acquisitionDate = acquisitionDate;
        this.description = description;
        this.vehicleMake = vehicleMake;
        this.vehiclemakeid = vehiclemakeid;
        this.power = power;
        this.fuelCapacity = fuelCapacity;
        this.vehicleStatus = vehicleStatus;
        this.vehiclestatusid = vehiclestatusid;
        this.netWeight = netWeight;
        this.inCharge = inCharge;
        this.employeeid = employeeid;
        this.vehicleModel = vehicleModel;
        this.vehiclemodelid = vehiclemodelid;
        this.currentLocation = currentLocation;
        this.locationid = locationid;
        this.remarks = remarks;
    }

    public Vehicle() {

    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vehicleType=" + vehicleType +
                ", vehicletypeid=" + vehicletypeid +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", acquisitionDate=" + acquisitionDate +
                ", description='" + description + '\'' +
                ", vehicleMake=" + vehicleMake +
                ", vehiclemakeid=" + vehiclemakeid +
                ", power='" + power + '\'' +
                ", fuelCapacity='" + fuelCapacity + '\'' +
                ", vehicleStatus=" + vehicleStatus +
                ", vehiclestatusid=" + vehiclestatusid +
                ", netWeight='" + netWeight + '\'' +
                ", inCharge=" + inCharge +
                ", employeeid=" + employeeid +
                ", vehicleModel=" + vehicleModel +
                ", vehiclemodelid=" + vehiclemodelid +
                ", currentLocation=" + currentLocation +
                ", locationid=" + locationid +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getVehicletypeid() {
        return vehicletypeid;
    }

    public void setVehicletypeid(Integer vehicletypeid) {
        this.vehicletypeid = vehicletypeid;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public Integer getVehiclemakeid() {
        return vehiclemakeid;
    }

    public void setVehiclemakeid(Integer vehiclemakeid) {
        this.vehiclemakeid = vehiclemakeid;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(String fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Integer getVehiclestatusid() {
        return vehiclestatusid;
    }

    public void setVehiclestatusid(Integer vehiclestatusid) {
        this.vehiclestatusid = vehiclestatusid;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public Employee getInCharge() {
        return inCharge;
    }

    public void setInCharge(Employee inCharge) {
        this.inCharge = inCharge;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getVehiclemodelid() {
        return vehiclemodelid;
    }

    public void setVehiclemodelid(Integer vehiclemodelid) {
        this.vehiclemodelid = vehiclemodelid;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
