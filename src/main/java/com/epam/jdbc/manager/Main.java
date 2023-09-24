package com.epam.jdbc.manager;

import com.epam.jdbc.entity.Department;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Manager<Department, String> manager = new DepartmentManager();
//        Department department = new Department();
//        department.setDepartmentNumber("d550");
//        department.setDepartmentName("Trav2");
//        manager.create(department);
//        System.out.println(manager.getById("d550"));
//
//
//        Department department1 = new Department();
//        department1.setDepartmentNumber("A558");
//        department1.setDepartmentName("Trav1");
//        manager.create(department1);
//
//        List<Department> all = manager.getAll();
//        System.out.println(all);

        Department department = new Department();
        department.setDepartmentNumber("d009");
        department.setDepartmentName("Service Customer");
//        manager.update(department);
        System.out.println(manager.getById("d009"));
        System.out.println(manager.delete("d009"));
        System.out.println(manager.getById("d009"));

    }
}
