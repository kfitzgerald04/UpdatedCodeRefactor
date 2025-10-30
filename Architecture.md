Short Overview of Architecture
App Layer:
-     Main - Entry point. Initializes repositories, creates the service, and starts the program
-     Run - User interface loop. Displays menus, captures user input, and displays results
-     Responsibility: Only parsing/printing. No business logic.
-
Service Layer:
-     RegistrationService - Coordinates all operations. Orchestrates between the UI and the model/data layers
-     Responsibility: Validation, enrollment rules, data persistence

Model Layer
-     Student - Represents a student (ID, name, email)
-     Course - Represents a course with enrollment and waitlist management
-     Enrollment - Manages the connection between students and courses
-     Responsibility: Business rules for enrollment/dropping, data storage in memory

Repo Layer:
-     StudentRepository - Saves/loads students from students.csv
-     CourseRepository - Saves/loads courses from courses.csv
-     EnrollmentRepository - Saves enrollment data to enrollments.txt
-     Responsibility: File I/O operations only

Util Layer:
-     Validation - Validates user input before creating objects
-     Responsibility: Input validation with helpful error messages
How the Data flows:
[User Input]
->
[Run.java] - Captures input & calls service methods
->
[RegistrationService] - Routes to appropriate operations
->
[Enrollment / Course / Student] - Executes business logic
->
[Repositories] - On save, save to file (students.csv, courses.csv, or enrollments.txt)

Refactored Package Diagram (view directly by hovering mouse and then right-clicking pop-up image "jump to source")
![img.png](img.png) 

