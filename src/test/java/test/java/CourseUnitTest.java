package test.java;

import model.Course;
import model.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseUnitTest {

    @test
    public void testEnroll_AddsToWaitlistWhenFull() {
        Course course = new Course("CS101", "Intro to CS", 1);
        Student s1 = new Student("12134", "Alice", "alice.com");
        Student s2 = new Student("5467", "Bob", "bob.com");

        assertTrue(course.enroll(s1));   // should enroll
        assertFalse(course.enroll(s2));  // should be waitlisted

        assertEquals(1, course.getEnrolled().size());
        assertEquals(1, course.getWaitlist().size());
    }
}



