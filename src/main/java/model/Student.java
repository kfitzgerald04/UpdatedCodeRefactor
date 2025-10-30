package model;


/**
 * Class to keep track of students through registration
 * It will store the banner ID, student name, and email
 * Before, student data was mixed inside the maps and handled directly in UI logic,
 * now it's its own class
 *
 */

public class Student
{
    private final String bannerId;
    private final String name;
    private final String email;

    // create a new student
    public Student(String bannerID, String name, String email)
    {
        this.bannerId = bannerID;
        this.name = name;
        this.email = email;
    }

    // getters
    public String getBannerId() {return bannerId;}
    public String getName() {return name;}
    public String getEmail() {return email;}

    // to-string implementation
    public String toString() {

        return bannerId + " " + name + " " + email;

    }

}