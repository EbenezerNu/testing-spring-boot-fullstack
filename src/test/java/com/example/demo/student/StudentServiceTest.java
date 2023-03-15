package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
//    private AutoCloseable autoCloseable;
    private StudentService underTest;


    @BeforeEach
    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository);
    }

    /*@AfterEach
    void tearDown() throws Exception {
       autoCloseable.close();
    }*/

    @Test
    void getAllStudents() {
       // when
        underTest.getAllStudents();
        // Then
        verify(studentRepository).findAll();
    }

    @Test
    void addStudent() {
        // given
        Student student = new Student(
                "Catalyst",
                "catalyst@test.com",
                Gender.MALE
        );

        // when
        underTest.addStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        assertThat(student).isEqualTo(studentArgumentCaptor.getValue());

    }

    @Test
    void cannotAddStudent() {
        // given
        Student student = new Student(
                "Catalyst",
                "catalyst@test.com",
                Gender.MALE
        );

        // when
        given(studentRepository.selectExistsEmail(any()))
                .willReturn(true);

        // then
       assertThatThrownBy(() -> underTest.addStudent(student))
               .hasMessageContaining("Email " + student.getEmail() + " taken")
               .isInstanceOf(BadRequestException.class);

        verify(studentRepository, never())
                .save(any());

    }

    @Test
    @Disabled
    void deleteStudent() {
    }
}