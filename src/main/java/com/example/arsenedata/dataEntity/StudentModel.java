package com.example.arsenedata.dataEntity;

public class StudentModel
{
    private String firstName;
    private String lastName;
    private int age;
    private int courses;
    private String emailAddress;
    private String country;
    private String state;
    private String phoneNumber;

    public StudentModel()
    {

    }

    public StudentModel(String firstName, String lastName, int age, int courses, String emailAddress,
                        String country, String state, String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.courses = courses;
        this.emailAddress = emailAddress;
        this.country = country;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getCourses()
    {
        return courses;
    }

    public void setCourses(int courses)
    {
        this.courses = courses;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
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

    public Student translateModelToStudent()
    {
        Student student = new Student();
        student.setAttendee(new Person(this.firstName, this.lastName));
        student.setEmailAddress(this.emailAddress);
        student.setCountry(this.country);
        student.setState(this.state);
        student.setPhoneNumber(this.phoneNumber);

        return student;
    }

}
