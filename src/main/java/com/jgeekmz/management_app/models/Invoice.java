package com.jgeekmz.management_app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    @ManyToOne
    @JoinColumn(name="invoicestatusid", insertable=false, updatable=false)
    private InvoiceStatus invoiceStatus;
    private Integer invoicestatusid;

    @ManyToOne
    @JoinColumn(name="clientid", insertable=false, updatable=false)
    private Client client;
    private Integer clientid;

    private String remarks;

    public Invoice(Integer id, Date invoiceDate, InvoiceStatus invoiceStatus, Integer invoicestatusid, Client client, Integer clientid, String remarks) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.invoiceStatus = invoiceStatus;
        this.invoicestatusid = invoicestatusid;
        this.client = client;
        this.clientid = clientid;
        this.remarks = remarks;
    }

    public Invoice (){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getInvoicestatusid() {
        return invoicestatusid;
    }

    public void setInvoicestatusid(Integer invoicestatusid) {
        this.invoicestatusid = invoicestatusid;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}