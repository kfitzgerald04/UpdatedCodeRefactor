package model;
import java.util.*;

/**
 * Class handles the enrollment of connecting Student and Courses
 * It jhs a collection of all students/courses
 * you can add, enroll, or drop
 * Before, main handled maps and enrollment logic,
 * now this class has all that logic
 * Main (UI) will call these methods
 *
 */

public class Enrollment
{
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();

    // add a student
    public void addStudent(Student s)
    {
        students.putIfAbsent(s.getBannerId(), s);
    }

    // add a new course
    public void addCourse(Course c)
    {
        courses.putIfAbsent(c.getCode(), c);
    }

    // enrolls a student in a specific course
    public boolean enroll(String bannerId, String courseCode)
    {
        Student s = students.get(bannerId);
        Course c = courses.get(courseCode);
        // return true if enrolled, false if waitlisted
        if (s == null || c == null) return false;
        return c.enroll(s);
    }

    // drop a student from a class, move waitlist student in
    public boolean drop(String bannerId, String courseCode)
    {
        Student s = students.get(bannerId);
        Course c = courses.get(courseCode);
        if (s == null || c == null) return false;
        return c.drop(s);
    }

    // access the fields
    public Collection<Student> getAllStudents() { return students.values(); }
    public Collection<Course> getAllCourses() { return courses.values(); }
}