CREATE TABLE Courses
(
    course_code VARCHAR(50) PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    capacity    INT,
    schedule    VARCHAR(100)
);

CREATE TABLE Students
(
    student_id VARCHAR(50) PRIMARY KEY,
    name       VARCHAR(100) NOT NULL
);

CREATE TABLE RegisteredCourses
(
    student_id  VARCHAR(50),
    course_code VARCHAR(50),
    PRIMARY KEY (student_id, course_code),
    FOREIGN KEY (student_id) REFERENCES Students (student_id),
    FOREIGN KEY (course_code) REFERENCES Courses (course_code)
);

-- Drop existing foreign key constraints if they exist
ALTER TABLE RegisteredCourses
DROP CONSTRAINT IF EXISTS FK_RegisteredCourses_course_code;

-- Add new foreign key constraint with ON DELETE CASCADE
ALTER TABLE RegisteredCourses
    ADD CONSTRAINT FK_RegisteredCourses_course_code
        FOREIGN KEY (course_code)
            REFERENCES Courses(course_code)
            ON DELETE CASCADE;



INSERT INTO Courses (course_code, title, description, capacity, schedule)
VALUES ('CS101', 'Introduction to Computer Science', 'Fundamentals of programming and computer systems.', 50,
        'Mon-Wed-Fri 9:00-10:30'),
       ('ENG201', 'Advanced English Writing', 'Advanced techniques in writing and communication.', 40,
        'Tue-Thu 13:00-14:30'),
       ('MATH301', 'Calculus III', 'Advanced calculus topics including multivariable calculus.', 30,
        'Mon-Wed-Fri 11:00-12:30'),
       ('CHEM101', 'General Chemistry', 'Introduction to basic concepts of chemistry.', 60, 'Tue-Thu 10:00-11:30'),
       ('PHYS202', 'Quantum Mechanics', 'Study of fundamental principles in quantum mechanics.', 35,
        'Mon-Wed 14:00-15:30'),
       ('HIST101', 'World History', 'Survey of major events and developments in world history.', 45,
        'Tue-Thu 14:00-15:30'),
       ('ARTS204', 'Modern Art Movements', 'Exploration of modern art movements and their impact.', 25,
        'Wed-Fri 10:00-11:30'),
       ('BIOL301', 'Genetics and Molecular Biology', 'In-depth study of genetics and molecular biology.', 30,
        'Mon-Wed 9:00-10:30'),
       ('BUS202', 'Marketing Strategies', 'Strategic approaches to marketing and consumer behavior.', 40,
        'Tue-Thu 11:00-12:30'),
       ('PSYC101', 'Introduction to Psychology', 'Fundamental concepts and theories in psychology.', 55,
        'Mon-Wed-Fri 13:00-14:30');

INSERT INTO Students (student_id, name)
VALUES ('S1001', 'John Smith'),
       ('S1002', 'Emily Johnson'),
       ('S1003', 'Michael Brown'),
       ('S1004', 'Sophia Martinez'),
       ('S1005', 'William Davis'),
       ('S1006', 'Isabella Garcia'),
       ('S1007', 'James Wilson'),
       ('S1008', 'Olivia Lopez'),
       ('S1009', 'Alexander Hernandez'),
       ('S1010', 'Charlotte Gonzalez');

INSERT INTO RegisteredCourses (student_id, course_code)
VALUES ('S1001', 'CS101'),
       ('S1001', 'MATH301'),
       ('S1002', 'ENG201'),
       ('S1003', 'CHEM101'),
       ('S1004', 'PHYS202'),
       ('S1005', 'HIST101'),
       ('S1006', 'ARTS204'),
       ('S1007', 'BIOL301'),
       ('S1008', 'BUS202'),
       ('S1009', 'PSYC101');
