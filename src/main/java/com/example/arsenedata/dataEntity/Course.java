package com.example.arsenedata.dataEntity;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "name")
    private String name;

    @Column(name = "credits")
    private int credits;

    @Column(name = "instructor")//This is a foreign key
    private int instructor;

    @Column(name = "course_depart_id")//This is a foreign, many courses can be delivered from a single department
    private int department;

    public Course()
    {

    }

    public Course(String name, int credits, int instructor, int department)
    {
        this.name = name;
        this.credits = credits;
        this.instructor = instructor;
        this.department = department;
    }

    public int getCourseId()
    {
        return courseId;
    }

    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCredits()
    {
        return credits;
    }

    public void setCredits(int credits)
    {
        this.credits = credits;
    }

    public int getInstructor()
    {
        return instructor;
    }

    public void setInstructor(int instructor)
    {
        this.instructor = instructor;
    }

    public int getDepartment()
    {
        return department;
    }

    public void setDepartment(int department)
    {
        this.department = department;
    }
}
