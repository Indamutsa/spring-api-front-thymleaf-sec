package com.example.arsenedata.dataEntity;


import javax.persistence.*;

@Entity //This will be treated as a table
@Table(name = "student") //We define the name of the table
public class Student
{
    @Id //This is a primary
    @GeneratedValue(strategy = GenerationType.AUTO) //this will be auto-incremented
    @Column(name = "student_id")
    private int studentId;

    @Embedded//This will make spring aware that this is an embedded doc
    private Person attendee;

    @Column(name = "full_time")
    private boolean fullTime;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String emailAddress;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "phonenumber")
    private String phoneNumber;

    public Student()
    {

    }

    public Student(Person attendee, boolean fullTime, int age,  String emailAddress, String address,
                   String country, String state, String phoneNumber)
    {
        this.attendee = attendee;
        this.fullTime = fullTime;
        this.age = age;
        this.emailAddress = emailAddress;
        this.address = address;
        this.country = country;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    /* ************************ Setters are used by JPA to set and get a values as in OOP *********************************/

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public Person getAttendee()
    {
        return attendee;
    }

    public void setAttendee(Person attendee)
    {
        this.attendee = attendee;
    }

    public boolean isFullTime()
    {
        return fullTime;
    }

    public void setFullTime(boolean fullTime)
    {
        this.fullTime = fullTime;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
