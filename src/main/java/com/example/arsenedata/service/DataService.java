package com.example.arsenedata.service;

import com.example.arsenedata.controller.DataController;
import com.example.arsenedata.dataEntity.Course;
import com.example.arsenedata.dataEntity.Staff;
import com.example.arsenedata.dataEntity.Student;
import com.example.arsenedata.repository.CourseRepository;
import com.example.arsenedata.repository.DepartmentRepository;
import com.example.arsenedata.repository.StaffRepository;
import com.example.arsenedata.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DataService
{
    private static final Logger log = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CourseRepository courseRepository;


    public List<Student> getAllStudents()
    {
        return new ArrayList<>(this.studentRepository.findAll());
    }

    public Student addAstudent(Student student) throws NoSuchElementException
    {
        return  studentRepository.save(student);
    }

    public Optional<Student> getStudentById(int id)
    {
        Optional<Student> std =  this.studentRepository.findById(id);
        return std;
    }

    public void deleteStudent(int id) throws NoSuchElementException
    {
        this.studentRepository.deleteById(id);
    }


    public List<Staff> getAllStaffMembers()
    {
        log.info("###############################");
        List<Staff> staffMembers = new ArrayList<>();

        staffRepository.findAll()
                .forEach(staffMembers::add);

        log.info("Number of staffs: " + staffRepository.getNumberOfStuff());

        System.out.println("\nFind all staff members");
//        staffMembers.forEach(System.out::println);

        return staffMembers;
    }


    public List<Course> getAllCourses()
    {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll()
                .forEach(courses::add);

//        courses.forEach(System.out::println);
        return courses;
    }

    public List<Student> getFullTimeStudent(boolean check)
    {
        List<Student> students = new ArrayList<>();
        studentRepository.findByFullTime(true).forEach(students::add);

        System.out.println("\nFind full time olf students");
//        studentRepository.findByFullTime(true).forEach(System.out::println);

        return students;
    }

    public List<Student> getStudentAbove(int age)
    {
        List<Student> students = new ArrayList<>();
        studentRepository.getStudentAbove(age).forEach(students::add);

        System.out.println("\nFind all students greater than 20");
//        studentRepository.getStudentAbove(age).forEach(System.out::println);

        return students;
    }

    public List<Student> getStudentByAge(int age)
    {
        List<Student> students = new ArrayList<>();

        studentRepository.findByAge(age).forEach(students::add);

        System.out.println("\nFind 25 year old students || *******");
//        studentRepository.findByAge(age).forEach(System.out::println);

        return students;
    }

    public List<Student> getStudentByLastName(String lastName) throws NoSuchElementException
    {
        List<Student> students = new ArrayList<>();
        studentRepository.findByAttendeeLastName(lastName).forEach(students::add);

        System.out.println("\nFind students with 'doe' last name");
//        studentRepository.findByAttendeeLastName(lastName).forEach(System.out::println);

        return students;
    }

    public boolean checkIfEquals(String name)
    {
        String name1 = "hello";
        if(name.equals(name1))
            return true;
        return false;
    }

}
