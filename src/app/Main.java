package app;

import model.Course;
import model.Student;
import repo.StudentRepository;
import repo.CourseRepository;
import repo.EnrollmentRepository;
import service.RegistrationService;
import app.Run;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        // Wire repositories to simple files in the working directory.
        StudentRepository studentRepo = new StudentRepository("students.csv");
        CourseRepository courseRepo = new CourseRepository("courses.csv");
        EnrollmentRepository enrollRepo = new EnrollmentRepository("enrollments.txt");

        // Service holds the domain logic and coordinates repositories
        RegistrationService svc = new RegistrationService(studentRepo, courseRepo, enrollRepo);

        // Try to load existing data (students & courses).
        try
        {
            svc.load();
        } catch (IOException e)
        {
            System.out.println("couldn’t load existing data: " + e.getMessage());
        }

        // Start the menu loop
        // RegistrationService serv =  new RegistrationService(studentRepo, courseRepo, enrollRepo);
        Run.run(svc);
    }


}
