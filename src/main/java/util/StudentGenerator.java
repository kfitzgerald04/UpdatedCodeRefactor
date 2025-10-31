package util;

import service.RegistrationService;


/**
 * Utility class for testing non-functional requirements
 * Generates a specified number of students and enrolls them in a course
 * Tests system performance and capacity handling
 */
public class StudentGenerator
{

    public static final int MAX_BATCH_SIZE = 1000; // set the max amount of students that can be generated

    /** Generates the specified number of students and enrolls them all in the specified course */
    public static BatchTestResult generateAndEnroll(RegistrationService svc, String courseCode, int batchSize)
    {

        // Validate batch size
        if (batchSize < 1 || batchSize > MAX_BATCH_SIZE)
        {
            throw new IllegalArgumentException("Batch size must be between 1 and " + MAX_BATCH_SIZE);
        }

        BatchTestResult result = new BatchTestResult(); // used to track stats
        long startTime = System.currentTimeMillis(); // record start time - to monitor performance

        System.out.println("=== Starting Batch Test ===");
        System.out.println("Generating " + batchSize + " students...");

        // Generate and add students
        for (int i = 1; i <= batchSize; i++)
        {
            String bannerId = "STU" + String.format("%04d", i); // Stu01, stu02 etc
            String name = "Student " + i; // name will actually be student 1, student 2 etc
            String email = "stu" + i + "@university.edu";

            try
            {
                svc.addStudent(bannerId, name, email); // add to registration service
                result.studentsCreated++;
            } catch (Exception e)
            {
                // handle validation
                result.studentsFailed++;
                System.out.println("Failed to create student " + bannerId + ": " + e.getMessage());
            }
        }

        System.out.println("Successfully created " + result.studentsCreated + " students.");
        System.out.println("Enrolling students in course " + courseCode + "...");

        // Enroll all students in the specified course
        for (int i = 1; i <= batchSize; i++)
        {
            String bannerId = "STU" + String.format("%04d", i);

            try
            {
                // enroll student, add student to waitlist if enrollment fails
                boolean enrolled = svc.enroll(bannerId, courseCode);
                if (enrolled)
                {
                    result.enrolled++; // enroll success
                }
                else
                {
                    result.waitlisted++; // course full, added to waitlist
                }
            }
            catch (Exception e)
            {
                result.enrollmentsFailed++;
                System.out.println("Failed to enroll " + bannerId + ": " + e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis(); // calc total time for batch operation
        result.totalTimeMs = endTime - startTime;

        return result;
    }

    /** Inner class to store batch test result **/
    public static class BatchTestResult
    {
        public int studentsCreated = 0;
        public int studentsFailed = 0;
        public int enrolled = 0;
        public int waitlisted = 0;
        public int enrollmentsFailed = 0;
        public long totalTimeMs = 0;

        public void printSummary()
        {
            System.out.println("\n=== Batch Test Results ===");
            System.out.println("Students Created: " + studentsCreated);
            System.out.println("Students Failed: " + studentsFailed);
            System.out.println("Enrolled: " + enrolled);
            System.out.println("Waitlisted: " + waitlisted);
            System.out.println("Enrollment Failures: " + enrollmentsFailed);
            System.out.println("Total Time: " + totalTimeMs + " ms");
            System.out.println("Average Time per Student: " +
                    (studentsCreated > 0 ? (totalTimeMs / studentsCreated) : 0) + " ms");
            System.out.println("========================\n");
        }
    }
}