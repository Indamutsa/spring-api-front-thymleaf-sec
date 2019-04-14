package com.example.arsenedata.repository;

import com.example.arsenedata.dataEntity.Person;
import com.example.arsenedata.dataEntity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer>
{
    /*Simple queries*/
    @Query(nativeQuery = true, value = "SELECT * FROM student s where s.age > ?1")
    List <Student> getStudentAbove(int age);

    Optional<Student> findById(int id);

    //Find students by age
    List<Student> findByAge(int age);

    //Find students who are fulltime
    List<Student> findByFullTime(boolean b);

    //Find student who has a given lastname
    List<Student> findByAttendeeLastName(String lastName);

    //Find the students by last name and first name
    Student findByAttendeeFirstNameAndAttendeeLastName(String firstName, String lastName);

    //Find students by person(an object)
    Student findByAttendee(Person person);

    //Find students greater than
    List<Student> findByAgeGreaterThan(int i);

    //Find students less than
    List<Student> findByAgeLessThan(int i);

    //Find students last name and ignore case
    List<Student> findByAttendeeLastNameIgnoreCase(String doe);

    //Find the students who have a given string in their lastname
    List<Student>  findByAttendeeLastNameLike(String s);

    //Find the students and order them by the first name, ascending
    List<Student> findByOrderByAttendeeFirstNameAsc();

    //Find the first student by order asc
    List<Student> findFirstByOrderByAttendeeLastNameAsc();

    //Find the oldest student
    Student findTopByOrderByAgeDesc();

    //Find the top 3 older students
    List<Student> findTop3ByOrderByAgeDesc();
}
