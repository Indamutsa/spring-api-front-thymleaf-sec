package com.example.arsenedata.dataEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable //This means it is not a table by itself rather, it is embedded
public class Person
{
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name") //This is meant to represent a column
    private String lastName;


    public Person()
    {

    }

    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString()
    {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
