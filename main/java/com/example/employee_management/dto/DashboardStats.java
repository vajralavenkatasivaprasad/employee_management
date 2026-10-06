package com.example.employee_management.dto;

public class DashboardStats {

    private long totalEmployees;
    private long itEmployees;
    private long hrEmployees;
    private long financeEmployees;
    private long salesEmployees;
    private Double highestSalary;
    private Double lowestSalary;

    public DashboardStats() {
    }

    public DashboardStats(long totalEmployees, long itEmployees, long hrEmployees,
                          long financeEmployees, long salesEmployees,
                          Double highestSalary, Double lowestSalary) {
        this.totalEmployees = totalEmployees;
        this.itEmployees = itEmployees;
        this.hrEmployees = hrEmployees;
        this.financeEmployees = financeEmployees;
        this.salesEmployees = salesEmployees;
        this.highestSalary = highestSalary;
        this.lowestSalary = lowestSalary;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getItEmployees() {
        return itEmployees;
    }

    public void setItEmployees(long itEmployees) {
        this.itEmployees = itEmployees;
    }

    public long getHrEmployees() {
        return hrEmployees;
    }

    public void setHrEmployees(long hrEmployees) {
        this.hrEmployees = hrEmployees;
    }

    public long getFinanceEmployees() {
        return financeEmployees;
    }

    public void setFinanceEmployees(long financeEmployees) {
        this.financeEmployees = financeEmployees;
    }

    public long getSalesEmployees() {
        return salesEmployees;
    }

    public void setSalesEmployees(long salesEmployees) {
        this.salesEmployees = salesEmployees;
    }

    public Double getHighestSalary() {
        return highestSalary;
    }

    public void setHighestSalary(Double highestSalary) {
        this.highestSalary = highestSalary;
    }

    public Double getLowestSalary() {
        return lowestSalary;
    }

    public void setLowestSalary(Double lowestSalary) {
        this.lowestSalary = lowestSalary;
    }
}
