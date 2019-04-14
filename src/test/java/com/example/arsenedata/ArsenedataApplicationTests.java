package com.example.arsenedata;

import com.example.arsenedata.dataEntity.Person;
import com.example.arsenedata.repository.CourseRepository;
import com.example.arsenedata.repository.DepartmentRepository;
import com.example.arsenedata.repository.StaffRepository;
import com.example.arsenedata.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArsenedataApplicationTests
{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    @Test
    public void contextLoads()
    {
        System.out.println("Hello");
    }

    @Test
    public void simpleQuery()
    {
        System.out.println("\nFind 25 year old students || *******");
        studentRepository.findByAge(20).forEach(System.out::println);

        System.out.println("\nFind full time olf students");
        studentRepository.findByFullTime(true).forEach(System.out::println);

        System.out.println("\nFind all students greater than 20");
        studentRepository.getStudentAbove(20).forEach(System.out::println);

        System.out.println("\nFind students with 'doe' last name");
        studentRepository.findByAttendeeLastName("doe").forEach(System.out::println);
    }

    @Test
    public void intermediateQueries()
    {
        System.out.println("\nFind students by name and traverse entities");
        System.out.println(studentRepository.findByAttendeeFirstNameAndAttendeeLastName("jane", "doe"));

        System.out.println("\nFind students by name with Person object");
        System.out.println(studentRepository.findByAttendee(new Person("jane", "doe")));

        System.out.println("\nFind students older than 19");
        studentRepository.findByAgeGreaterThan(19).forEach(System.out::println);

        System.out.println("\nFind students less than 19");
        studentRepository.findByAgeLessThan(19).forEach(System.out::println);

        System.out.println("\nFind students with last name Doe, depsite the case");
        studentRepository.findByAttendeeLastNameIgnoreCase("Doe").forEach(System.out::println);

        System.out.println("\nFind students with an i in the last name");
        studentRepository.findByAttendeeLastNameLike("%i%").forEach(System.out::println);

        System.out.println("\nFind students in alphabet order");
        studentRepository.findByOrderByAttendeeFirstNameAsc().forEach(System.out::println);

        System.out.println("\nFind the first student by order");
        studentRepository.findFirstByOrderByAttendeeLastNameAsc().forEach(System.out::println);

        System.out.println("\nFind the oldest students");
        System.out.println(studentRepository.findTopByOrderByAgeDesc());

        System.out.println("\nFind 3 oldest students");
        System.out.println(studentRepository.findTop3ByOrderByAgeDesc());
    }

}

