package util;

public class Validation {

    public static void validateStudent(String id, String name, String email) throws Exception {

        if (id == null || id.isBlank())
            throw new Exception ("Please enter the student's Banner ID");

        if (name == null || name.isBlank())
            throw new Exception ("Please enter the student's name");

        if (email == null || email.isBlank())
            throw new Exception ("Please enter the student's email");

    }

    public static void validateCourse(String code, String title, int capacity) throws Exception {

        if (code == null || code.isBlank())
            throw new Exception ("Please enter the course code");

        if (title == null || title.isBlank())
            throw new Exception ("Please enter the course title");

        if (capacity <= 0)
            throw new Exception ("Course must have at least one opening");

    }

}
