This document details the major changes made to refactor the code in order produce
a layered structure dividing tasks into their own classes.

MODEL LAYER
1. First, we began creating a package called model, and in it, we created a Student, Course, and
   Enrollment class.
2. The structural changes here were made in 7 areas. Before, the code organization was all in Main.java,
   after, the model package was created, we were able to improve readability and modularity.
3. The next area was creating the Student class. Before, all student data was stored in a
   global map, now it's in an independent class, which improves reuse and encapsulates the data.
4. The next area was the course logic. Before, enrollment and waitlist logic was in Main, now,
   Course is its own class, which adopts a more OO design.
5. Next, instead of handling the enrollment in Main directly linking students and courses,
   the Enrollment class coordinates between them, centralizing the logic, and creating consistency.
6. The persistence logic was completely removed from the model layer.
7. When it came to naming methods, we went with a more generic approach (e.g. putIfAbscent), which
   improves the clarity of what the code is trying to accomplish.

REPO LAYER
1. We created 3 separate classes for handling file imports and exports
2. Each file creates and exports a collection to its own file (Student, Course, Enrollment)

SERVICE LAYER
1. Added RegistrationService.java to handle course enrollment

UTIL LAYER
1. Implemented input validation via validateStudent / validateCourse methods

APP LAYER
1. Added the Run class which took over printing the menu and cases from the messy main class. Run
   uses Scanner to read user input and delegate to a service from RegistrationService. This is all done
   within a continuous loop so that the user can continue going through the menu until they choose to end.
2. The Main class now sets up the data files for storing students, courses and enrollments,
   as well as creates the services and give them the files. after you save the files you can go into
   your folder and look at the CSV or the .txt to see them.
3. Our idea was to keep main concise and use other classes (such as run) to delegate services and parse
   through the menu.