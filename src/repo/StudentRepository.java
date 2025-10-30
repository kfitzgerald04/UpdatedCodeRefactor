package repo;

// Imports
import model.Student;
import java.io.*;
import java.util.*;

public class StudentRepository {

    // Repository file
    private File file;

    // Constructor
    public StudentRepository(String filePath) {

        this.file = new File(filePath);

    }

    // Save current students to file
    public void save(Collection<Student> students) throws IOException {

        if (!file.exists())
            file.createNewFile();

        try (PrintWriter output = new PrintWriter(new FileWriter(file))) {

            for (Student s : students) {

                output.println(s.getBannerId() + ", " + s.getName() + ", " + s.getEmail());

            }

        }

    }

    // Load students from file
    public List<Student> load() throws IOException {

        List<Student> list = new ArrayList<>();

        if (!file.exists())
            return list;

        try (BufferedReader input = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = input.readLine()) != null) {

                String[] group = line.split(",", -1);

                if (group.length == 3)
                    list.add(new Student(group[0], group[1], group[2]));

            }

        }

        return list;

    }

}
