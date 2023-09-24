package com.epam.jdbc.entity;

public class Department {
    private String departmentNumber;
    private String departmentName;

    public Department(){

    }

    public Department(String departmentNumber, String departmentName){
        this.departmentName = departmentName;
        this.departmentNumber = departmentNumber;
    }


    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentNumber=" + departmentNumber +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
