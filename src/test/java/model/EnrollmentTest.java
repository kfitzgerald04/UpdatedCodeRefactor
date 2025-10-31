/**
 * EnrollmentTest.java tests higher-level features involving students, courses,
 * and enrollment logic that are not covered by CourseTest.java.
 */

package model;

import model.Course;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repo.CourseRepository;
import repo.EnrollmentRepository;
import repo.StudentRepository;
import service.RegistrationService;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentTest {

    private RegistrationService service;
    private File studentFile, courseFile, enrollFile;

    @BeforeEach
    void setUp() {
        // Temporary test files (isolated from production data)
        studentFile = new File("test_students.csv");
        courseFile = new File("test_courses.csv");
        enrollFile = new File("test_enrollments.txt");

        StudentRepository sRepo = new StudentRepository(studentFile.getPath());
        CourseRepository cRepo = new CourseRepository(courseFile.getPath());
        EnrollmentRepository eRepo = new EnrollmentRepository(enrollFile.getPath());

        service = new RegistrationService(sRepo, cRepo, eRepo);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        studentFile.delete();
        courseFile.delete();
        enrollFile.delete();
    }

    @Test
    @DisplayName("ET-01 — Add and retrieve students")
    void testAddAndRetrieveStudents() throws Exception {
        service.addStudent("B01", "Kendra F.", "kf@uca.edu");
        service.addStudent("B02", "Johnny C.", "jc@uca.edu");
        service.addStudent("B03", "Nick A.", "na@uca.edu");

        assertEquals(3, service.listStudents().size(), "Three students should be added");
        assertTrue(service.listStudents().stream().anyMatch(s -> s.getBannerId().equals("B03")));
    }

    @Test
    @DisplayName("ET-02 — Add and retrieve courses")
    void testAddAndRetrieveCourses() throws Exception {
        service.addCourse("CS101", "Intro to CS", 2);
        service.addCourse("CS102", "Data Structures", 3);

        assertEquals(2, service.listCourses().size(), "Two courses should be added");
        assertTrue(service.listCourses().stream().anyMatch(c -> c.getCode().equals("CS102")));
    }

    @Test
    @DisplayName("ET-03 — Enroll a student in a course")
    void testEnrollStudentInCourse() throws Exception {
        service.addStudent("B01", "Kendra F.", "kf@uca.edu");
        service.addCourse("CS103", "Algorithms", 1);

        boolean enrolled = service.enroll("B01", "CS103");

        assertTrue(enrolled, "Student should be enrolled in course");
        Course c = service.listCourses().iterator().next();
        assertEquals(1, c.getEnrolled().size(), "Course should have one enrolled student");
    }

    @Test
    @DisplayName("ET-04 — Drop student and promote from waitlist")
    void testDropStudentFromCourse() throws Exception {
        service.addStudent("B01", "Kendra F.", "kf@uca.edu");
        service.addStudent("B02", "Johnny C.", "jc@uca.edu");
        service.addStudent("B03", "Nick A.", "na@uca.edu");
        service.addCourse("CS104", "Operating Systems", 2);

        service.enroll("B01", "CS104"); // fills course
        service.enroll("B02", "CS104"); // fills course
        service.enroll("B03", "CS104"); // waitlisted

        service.drop("B01", "CS104");   // triggers promotion

        Course course = service.listCourses().iterator().next();

        assertTrue(course.getEnrolled().stream().anyMatch(s -> s.getBannerId().equals("B03")),
                "Nick should now be enrolled after Kendra dropped");
        assertTrue(course.getWaitlist().isEmpty(), "Waitlist should now be empty");
    }

}