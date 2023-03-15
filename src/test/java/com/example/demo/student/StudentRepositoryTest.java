package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailExists() {
        // Given
        Student student = new Student(
            "Catalyst",
            "catalyst@test.com",
            Gender.MALE
        );

        // When
        underTest.save(student);
        Boolean emailExists = underTest.selectExistsEmail(student.getEmail());

        // Then
        assertThat(emailExists).isTrue();
    }

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExist() {
        // Given
        String email = "catalyst@test.com";
        // When
        Boolean emailExists = underTest.selectExistsEmail(email);
        // Then
        assertThat(emailExists).isFalse();
    }
}