package repo;

// Imports
import model.Course;
import java.io.*;
import java.util.*;

public class CourseRepository {

    // Repository file
    private File file;

    // Constructor
    public CourseRepository(String filePath) {

        this.file = new File(filePath);

    }

    // Save current courses to file
    public void save(Collection<Course> courses) throws IOException {

        if (!file.exists())
            file.createNewFile();

        try (PrintWriter output = new PrintWriter(new FileWriter(file))) {

            for (Course c : courses) {

                output.println(c.getCode() + ", " + c.getTitle() + ", " + c.getCapacity());

            }

        }

    }

    // Load courses from file
    public List<Course> load() throws IOException {

        List<Course> list = new ArrayList<>();

        if (!file.exists())
            return list;

        try (BufferedReader input = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = input.readLine()) != null) {

                String[] group = line.split(",", -1);

                if (group.length == 3) {

                    try {

                        int cap = Integer.parseInt(group[2]);
                        list.add(new Course(group[0], group[1], cap));

                    } catch (NumberFormatException ignored) {}

                }
            }
        }

        return list;

    }

}
