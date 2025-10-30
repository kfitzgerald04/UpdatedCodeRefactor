Peer Programming/Refactoring - UCA Course Registration(JAVA)

OVERVIEW:
This repository is designated for the SE Refactor Assignment regarding class enrollment. The project was refactored to follow a layerd architecture for improved 
organization, maintainability, and scalability. 

MODEL LAYER:
Defines the core entities (Student, Course, Enrollment).

REPO LAYER: 
Repositories act as a DB, holding student/course information (CourseRepository, 
StudentRepository, EnrollmentRepository).

SERVICE LAYER:
Handles registration services.

UTIL LAYER:
Handles input-validation.

APP LAYER:
Acts as the main layer, where the code is ran.

RUN INSTRUCTIONS:
1. Clone repo
2. Run the code from the APP package (src/main/java/app/Main.java) 
3. Add students/courses
4. Confirm that each option yields the correct results.

MAVEN INSTRUCTIONS:
1. To test any menu feature associated with Course funtionality, run from the test MODEL package (src/test/java/model/CourseTest.java)
2. You can either right-click to run Maven, or go directly inside the class itself. 


