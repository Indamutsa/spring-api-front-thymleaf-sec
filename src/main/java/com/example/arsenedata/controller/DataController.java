package com.example.arsenedata.controller;

import com.example.arsenedata.dataEntity.Course;
import com.example.arsenedata.dataEntity.Staff;
import com.example.arsenedata.dataEntity.Student;
import com.example.arsenedata.dataEntity.StudentModel;
import com.example.arsenedata.service.DataService;
import com.example.arsenedata.service.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class DataController
{
    @Autowired
    private DataService dataService;

    @GetMapping
    public List<Student> getAllStudents()
    {
        return dataService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody StudentModel studentModel)
    {
        Student student = dataService.addAstudent(studentModel.translateModelToStudent());
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("{/id}")
                .buildAndExpand(student.getStudentId()).toUri();

        return ResponseEntity.created(location).body(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody StudentModel studentModel)
    {
        Optional<Student> student = dataService.getStudentById(id);

        if (!student.isPresent())
            throw new StudentNotFoundException("Student not found with id: " + id);

        Student student1 = studentModel.translateModelToStudent();
        student1.setStudentId(id);

        return dataService.addAstudent(student1);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteStudent(@PathVariable int id)
    {
        Optional<Student> student = dataService.getStudentById(id);

        if (!student.isPresent())
            throw new StudentNotFoundException("Student not found with id: " + id);

        dataService.deleteStudent(id);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id)
    {
        Optional<Student> student = dataService.getStudentById(id);
        if (student.isPresent())
        {
            return student.get();
        }
        throw new StudentNotFoundException("Guest not found with id: " + id);
    }

    //******************************Rest data api***************************

    @RequestMapping(method = RequestMethod.GET, value = "/staff")
    public List<Staff> getStaffMembers()
    {
        return dataService.getAllStaffMembers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/course")
    public List<Course> getAllCourses()
    {
        return dataService.getAllCourses();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fulltime")
    public List<Student> getFullTimeStudents()
    {
        return dataService.getFullTimeStudent(true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/studentsAbove")
    public List<Student> getStudentsABove()
    {
        return dataService.getStudentAbove(25);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/byage")
    public List<Student> getStudentByAge()
    {
        return dataService.getStudentByAge(24);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bylastname")
    public List<Student> getStudentByLastName()
    {
        return dataService.getStudentByLastName("doe");
    }

}
