package model;

import java.time.LocalDate;

public class Budgets {
    private Integer id;
    private String name;
    private String item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQnt() {
        return qnt;
    }

    public void setQnt(Integer qnt) {
        this.qnt = qnt;
    }

    public Double getUnt_val() {
        return unt_val;
    }

    public void setUnt_val(Double unt_val) {
        this.unt_val = unt_val;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    public Sectors getSector() {
        return sector;
    }

    public void setSector(Sectors sector) {
        this.sector = sector;
    }

    public Budgets_type getBudget_type() {
        return budget_type;
    }

    public void setBudget_type(Budgets_type budget_type) {
        this.budget_type = budget_type;
    }

    private Integer qnt;
    private Double unt_val;
    private Integer status;
    private Integer active;
    private LocalDate created;
    private LocalDate modified;
    private Sectors sector;
    private Budgets_type budget_type;
}
