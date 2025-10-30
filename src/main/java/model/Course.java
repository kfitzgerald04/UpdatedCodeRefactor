package model;
import java.util.*;

/**
 * This class represents the courses a student can enroll in
 * It manages the course info, such as course code, title, and capacity
 * It manages the waitlist
 * It has logic to add and drop classes
 * Before, course enrollment was done inline via procedural code, now Course
 * has all the logic in a class making it more object-oriented

 */

public class Course
{
    private String code;
    private String title;
    private int capacity;

    // lists 7 queues to keep track of enrollment
    private List<Student> enrolled = new ArrayList<>();
    private Queue<Student> waitlist = new LinkedList<>();

    // create a new course with the information above ^^
    public Course(String code, String title, int capacity)
    {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
    }

    // enroll a student & return true if the student is enrolled & false
    // if added to the waitlist
    public boolean enroll(Student student)
    {
        // get duplicates
        if (enrolled.contains(student) || waitlist.contains(student)) return false;
        // if capacity isn't met, add student, OW waitlist
        if (enrolled.size() < capacity)
        {
            enrolled.add(student);
            return true;
        } else {
            waitlist.add(student);
            return false;
        }
    }

    // drop a student from a course & if the student is dropped
    // the next student on the waitlist gets added
    public boolean drop(Student student)
    {
        if (enrolled.remove(student))
        {
            // move the next student on the waitlist up
            if (!waitlist.isEmpty())
            {
                Student next = waitlist.poll();
                enrolled.add(next);
            }
            return true;
        } else
        {
            waitlist.remove(student);
            return false;
        }
    }

    // UI access
    public String getCode()
    {
        return code;
    }
    public String getTitle()
    {
        return title;
    }
    public int getCapacity()
    {
        return capacity;
    }
    @Override
    public String toString()
    {
        return code + " | " + title + " | Capacity: " + capacity;
    }
    // return enrolled students
    public List<Student> getEnrolled() { return enrolled; }

    // return the waitlist queue
    public Queue<Student> getWaitlist()
    {
        return new LinkedList<>(waitlist);
    }

}