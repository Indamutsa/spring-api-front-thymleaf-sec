package com.example.arsenedata.service;

import com.example.arsenedata.dataEntity.*;
import com.example.arsenedata.repository.CourseRepository;
import com.example.arsenedata.repository.StaffRepository;
import com.example.arsenedata.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataServiceTest
{
    private static final int NUMBER = 1;

    private LoginData signup = new LoginData("arsene", "1234", "larry", "miller");
    private User user = new User(signup.getUsername(), signup.getPassword(), new Role(),
                                    signup.getFirstName(), signup.getLastName());

    @MockBean
    private UserService userService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private StudentRepository studentRepositoryMock;

    @Mock
    private StaffRepository staffRepositoryMock;

    @Mock
    private CourseRepository courseRepositoryMock;

    @InjectMocks  //Autowire service which takes arguments
    private DataService dataServiceMock;


    @Mock
    private Person person;

    @Mock
    private Student student;

    @Test
    public void setupReturnValuesOfMockMethods()
    {
        when(studentRepositoryMock.findById(NUMBER)).thenReturn(Optional.of(student));
        when(student.getStudentId()).thenReturn(NUMBER);
        when(studentRepositoryMock.findByAttendeeLastName("arsene")).thenReturn(List.of(student));
        when(studentRepositoryMock.findByAttendee(person)).thenReturn(student);
        when(studentRepositoryMock.findByAge(NUMBER)).thenReturn(Arrays.asList(student));

    }

    @Test
    public void serviceMethods()
    {
        when(studentRepositoryMock.findByAttendeeLastNameLike("s")).thenReturn(Arrays.asList(student));

/*        //Invoke and verify getStudentById()
        assertThat(dataServiceMock.checkIfEquals("amata"), is(true));*/
    }

    @Test
    public void verifyDelete_Save_Update()
    {
        //Invoke deleteMethods
        dataServiceMock.deleteStudent(NUMBER);

        //Verify when a delete student is invoked
//        verify(studentRepositoryMock).delete(any(Student.class));
//        verify(studentRepositoryMock).save(any(Student.class));

        //Verification of insert, update and set attendee of student of object
/*        verify(dataServiceMock.addAstudent(student));
        verify(student).setAttendee(new Person("Hello", "Janvier"));*/
    }


    @Test
    public void CreateNewObject()
    {
/*        //Prepare to capture student object
        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);

        Student st = new Student();
        st.setAttendee(new Person("Matt", "Habimana"));
        st.setCountry("Rwanda");
        st.setEmailAddress("matt@gmail.com");
        st.setState("Kigali");
        st.setAddress("KG st 965");
        st.setPhoneNumber("+1(456)-565-4545-555");
        st.setAge(25);
        st.setFullTime(false);

        //Invoke add student
        dataServiceMock.addAstudent(student);

        //Verify studentRepository.save invoked once and capture the Student object
        Mockito.verify(studentRepositoryMock).save(studentCaptor.capture());

        //Verify the attributes of Student Object
        assertThat(studentCaptor.getValue().getAddress(), is("KG st 965"));
        assertThat(studentCaptor.getValue().getAge(),is(25));
        assertThat(studentCaptor.getValue().getAttendee(), is(new Person("Matt", "Habimana")));
        assertThat(studentCaptor.getValue().getCountry(), is("Rwanda"));*/
    }

    @Test
    public void signup()
    {
        when(userService.signup(signup.getUsername(), signup.getPassword(), signup.getFirstName(),
                signup.getLastName())).thenReturn(Optional.of(user));
//
//        ResponseEntity<User> response = restTemplate.exchange("/users/signup",
//                POST,
//                loggedInAs("admin", "ROLE_ADMIN", signup),
//                User.class);
    }
}
