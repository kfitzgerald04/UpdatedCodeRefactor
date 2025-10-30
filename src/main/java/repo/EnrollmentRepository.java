package repo;

// Imports
import model.Course;
import model.Student;
import java.io.*;
import java.util.*;

public class EnrollmentRepository {

    // Repository file
    private File file;

    public EnrollmentRepository(String filePath) {

        this.file = new File(filePath);

    }

    // Save enrollment data to file
    public void save(Collection<Course> courses) throws IOException {

        if (!file.exists())
            file.createNewFile();

        try (PrintWriter output = new PrintWriter(new FileWriter(file))) {

            for (Course c : courses) {

                for (Student s : c.getEnrolled())
                    output.println(c.getCode() + " | " + s.getBannerId() + " | Enrolled");

                for (Student s : c.getWaitlist())
                    output.println(c.getCode() + " | " + s.getBannerId() + " | On Waitlist");

            }

        }

    }

}
