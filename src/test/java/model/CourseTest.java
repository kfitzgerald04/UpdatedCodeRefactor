/** CourseTest.java corresponds to the first Test Case (1) from the PDF, but it also
 * confirms that any Course related functionality from the menu is checked & verified here.
 * Asserts will only display if the test fails.
 */

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private Course course;
    private Student Kendra;
    private Student Johnny;
    private Student Nick;

    @BeforeEach
    void setUp() {
        // Example data
        Kendra = new Student("B01", "Kendra F.", "kf@uca.edu");
        Johnny = new Student("B02", "Johnny C.", "jc@uca.edu");
        Nick = new Student("B03", "Nick A.", "na@uca.edu");
        course = new Course("CS101", "Intro to CS", 2);
    }

    @Test
    void testEnrollWithinCapacity() {
        boolean result1 = course.enroll(Kendra);
        boolean result2 = course.enroll(Johnny);

        assertTrue(result1, "Kendra should be enrolled");
        assertTrue(result2, "Johnny should be enrolled");
        assertEquals(2, course.getEnrolled().size());
        assertTrue(course.getWaitlist().isEmpty());
    }

    @Test
    void testEnrollAddsToWaitlistWhenFull() {
        course.enroll(Kendra);
        course.enroll(Johnny);
        boolean result = course.enroll(Nick);

        assertFalse(result, "Nick should be waitlisted when course is full");
        assertEquals(2, course.getEnrolled().size());
        assertEquals(1, course.getWaitlist().size());
    }

    @Test
    void testDropMovesWaitlistedStudentUp() {
        course.enroll(Kendra);
        course.enroll(Johnny);
        course.enroll(Nick); // Nick waitlisted

        course.drop(Kendra); // should move Nick up

        List<Student> enrolled = course.getEnrolled();
        Queue<Student> waitlist = course.getWaitlist();

        assertTrue(enrolled.contains(Nick), "Nick should be enrolled now");
        assertEquals(2, enrolled.size());
        assertTrue(waitlist.isEmpty());
    }

    @Test
    void testDropNonEnrolledStudentDoesNothing() {
        course.enroll(Kendra);
        boolean dropped = course.drop(Nick);

        assertFalse(dropped, "Dropping non-enrolled student should return false");
        assertEquals(1, course.getEnrolled().size());
        assertTrue(course.getWaitlist().isEmpty());
    }

    @Test
    void testShowsCourseDetails() {
        String result = course.toString();
        assertTrue(result.contains("CS101"));
        assertTrue(result.contains("Intro to CS"));
    }

    @Test
    void testDuplicateEnrollmentNotAllowed() {
        course.enroll(Kendra);
        boolean duplicate = course.enroll(Kendra);

        assertFalse(duplicate, "Duplicate enrollment should not be allowed");
        assertEquals(1, course.getEnrolled().size(), "Only one instance of Kendra should exist");
    }

}
