package com.stacksimplify.restservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="employee")
public class EmployeeManagement {
    @Id
    @GeneratedValue
    @JsonView(EmployeeViews.normalViews.class)
    private Long empid;
    @Column(name = "EmployeeName", length = 50, nullable = false,unique = true)
    @NotEmpty(message = "name should not be empty. Please enter the employee name")
    @JsonView(EmployeeViews.normalViews.class)
    private String name;
    @Column(name = "Department", length = 50, nullable = true,unique = false)
    @JsonView(EmployeeViews.normalViews.class)
    private String department;
    @NotEmpty(message = "Must be enter the loginTime.")
    @Column(name = "LoginTime", length = 50, nullable = false)
    @JsonView(EmployeeViews.managerViews.class)
    private String loginTime;
    @Column(name = "LogoutTime", length = 50, nullable = false)
    @NotEmpty(message = "Must be enter the logOutTime.")
    @JsonView(EmployeeViews.managerViews.class)
    private String logoutTime;
    @JsonView(EmployeeViews.hrViews.class)
    @Column(name = "Salary", nullable = true)
    private Long salary;
    @JsonView(EmployeeViews.hrViews.class)
    @Column(name = "LastPromotionDate", length = 50, nullable = true)
    private String lastPromotionDate;


    //default constructor
    public EmployeeManagement() {

    }

    //Field Constructor

    public EmployeeManagement(Long empid, @NotEmpty(message = "name should not be empty. Please enter the employee name") String name, String department, @NotEmpty(message = "Must be enter the loginTime.") String loginTime, @NotEmpty(message = "Must be enter the logOutTime.") String logoutTime, @NotEmpty(message = "Must be enter the Salary.") Long salary, String lastPromotionDate) {
        this.empid = empid;
        this.name = name;
        this.department = department;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.salary = salary;
        this.lastPromotionDate = lastPromotionDate;
    }

    //Getter & Setter
    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getLastPromotionDate() {
        return lastPromotionDate;
    }

    public void setLastPromotionDate(String lastPromotionDate) {
        this.lastPromotionDate = lastPromotionDate;
    }

    //ToString

    @Override
    public String toString() {
        return "EmployeeManagement{" +
                "empid=" + empid +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", logoutTime='" + logoutTime + '\'' +
                ", salary=" + salary +
                ", lastPromotionDate=" + lastPromotionDate +
                '}';
    }
}
