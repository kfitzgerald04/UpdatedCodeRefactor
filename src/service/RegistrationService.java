package service;

// Imports
import model.Course;
import model.Enrollment;
import model.Student;
import repo.CourseRepository;
import repo.EnrollmentRepository;
import repo.StudentRepository;
import util.Validation;
import java.io.IOException;
import java.util.Collection;

public class RegistrationService {

    private Enrollment enrollment = new Enrollment();
    private StudentRepository studentRepo;
    private CourseRepository courseRepo;
    private EnrollmentRepository enrollmentRepo;

    // Constructor
    public RegistrationService(StudentRepository studentRepo, CourseRepository courseRepo, EnrollmentRepository enrollmentRepo) {

        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;

    }

    public void addStudent(String id, String name, String email) throws Exception {

        Validation.validateStudent(id, name, email);
        enrollment.addStudent(new Student(id, name, email));

    }

    public void addCourse(String code, String title, int capacity) throws Exception {

        Validation.validateCourse(code, title, capacity);
        enrollment.addCourse(new Course(code, title, capacity));

    }

    public boolean enroll(String bannerId, String courseCode) {

        return enrollment.enroll(bannerId, courseCode);

    }

    public boolean drop(String bannerId, String courseCode) {

        return enrollment.drop(bannerId, courseCode);

    }

    public Collection<Student> listStudents() { return enrollment.getAllStudents(); }
    public Collection<Course> listCourses() { return enrollment.getAllCourses(); }

    public void save() throws IOException {

        studentRepo.save(enrollment.getAllStudents());
        courseRepo.save(enrollment.getAllCourses());
        enrollmentRepo.save(enrollment.getAllCourses());

    }

    public void load() throws IOException {

        for (Student s : studentRepo.load())
            enrollment.addStudent(s);
        for (Course c : courseRepo.load())
            enrollment.addCourse(c);

    }

}
