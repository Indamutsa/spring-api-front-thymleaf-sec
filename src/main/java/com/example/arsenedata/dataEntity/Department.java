package com.example.arsenedata.dataEntity;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "dept_name")
    private String name;

    @Column(name = "chair_staff")
    private int chair;

    public Department()
    {

    }

    public Department(String name, int chair)
    {
        this.name = name;
        this.chair = chair;
    }

    public int getDepartmentId()
    {
        return departmentId;
    }

    public void setDepartmentId(int departmentId)
    {
        this.departmentId = departmentId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getChair()
    {
        return chair;
    }

    public void setChair(int chair)
    {
        this.chair = chair;
    }


    @Override
    public String toString()
    {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", chair=" + chair +
                '}';
    }
}
