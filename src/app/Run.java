package app;

import model.Course;
import model.Student;
import service.RegistrationService;

import java.util.Scanner;

public class Run
{
    // Print & parse loop. delegates actual work to the service
    public static void run(RegistrationService svc)
    {
        Scanner in = new Scanner(System.in);

        while (true)
        {
            printMenu();
            System.out.print("Choice: ");
            String choice = in.nextLine().trim();

            try
            {
                switch (choice)
                {
                    case "1": // Add student
                    {
                        System.out.print("Banner ID: ");
                        String id = in.nextLine();
                        System.out.print("Name: ");
                        String name = in.nextLine();
                        System.out.print("Email: ");
                        String email = in.nextLine();
                        svc.addStudent(id, name, email);
                        System.out.println("✓ Student saved.");
                        break;
                    }

                    case "2": // List students
                        for (Student s : svc.listStudents())
                        {

                            System.out.println(s);
                        }
                        break;

                    case "3": //  Add course
                    {
                        System.out.print("Course code: ");
                        String code = in.nextLine();
                        System.out.print("Title: ");
                        String title = in.nextLine();
                        System.out.print("Class Size:  ");
                        int cap = Integer.parseInt(in.nextLine());
                        svc.addCourse(code, title, cap);
                        System.out.println("✓ Course saved.");
                        break;
                    }

                    case "4": // List courses
                        for (Course c : svc.listCourses())
                        {
                            System.out.println(c);
                        }
                        break;

                    case "5": // Enroll
                    {
                        System.out.print("Banner ID: ");
                        String id = in.nextLine();
                        System.out.print("Course code: ");
                        String code = in.nextLine();

                        // Service returns true if placed in "enrolled", false if placed on "waitlist"
                        boolean enrolled = svc.enroll(id, code);
                        System.out.println(enrolled ? "Result: ENROLLED" : "Result: WAITLISTED");
                        break;
                    }

                    case "6": // Drop a class by providing the ID and Course Code after enrolling
                    {
                        System.out.print("Banner ID: ");
                        String id = in.nextLine();
                        System.out.print("Course code: ");
                        String code = in.nextLine();

                        boolean dropped = svc.drop(id, code);
                        System.out.println(dropped ? "Dropped (and promoted from waitlist if applicable)." :
                                "Student was not enrolled/waitlisted in that course.");
                        break;
                    }

                    case "7": // Show course roster (enrolled + waitlist)
                    {
                        System.out.print("Course code: ");
                        String code = in.nextLine();
                        Course c = findCourse(svc, code);
                        if (c == null)
                        {
                            System.out.println("Course not found.");
                            break;
                        }
                        System.out.println("== " + code + " roster ==");
                        System.out.println("Enrolled:");
                        for (Student s : c.getEnrolled())
                        {
                            System.out.println("  " + s);
                        }
                        System.out.println("Waitlist:");
                        for (Student s : c.getWaitlist())
                        {
                            System.out.println("  " + s);
                        }
                        break;
                    }

                    case "8": // Save to files (students.csv, courses.csv, enrollments.txt)
                    {
                        svc.save();
                        System.out.println("✓ Data saved.");
                        break;
                    }

                    case "9": // Reload from students/courses files
                    {
                        svc.load();
                        System.out.println("✓ Data loaded.");
                        break;
                    }

                    case "0":
                        System.out.println("Bye!");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException nfe)
            {
                System.out.println("Please enter a valid number.");
            } catch (Exception ex)
            {
                // Service should throw friendly Validation/Enrollment messages on errors (bad email, duplicates, etc.)
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    // find a course by code
    private static Course findCourse(RegistrationService svc, String code)
    {
        for (Course c : svc.listCourses())
        {
            if (c.getCode().equalsIgnoreCase(code)) return c;
        }
        return null;
    }

    // menu
    private static void printMenu()
    {
        System.out.println("\n=== Registration Menu ===");
        System.out.println("1) Add student");
        System.out.println("2) List students");
        System.out.println("3) Add course");
        System.out.println("4) List courses");
        System.out.println("5) Enroll");
        System.out.println("6) Drop");
        System.out.println("7) Show course roster");
        System.out.println("8) Save");
        System.out.println("9) Load");
        System.out.println("0) Exit");
    }
}
