package in.codsoft.task5;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return String.format("Course Code: %s, Title: %s, Description: %s, Capacity: %d, Schedule: %s",
                courseCode, title, description, capacity, schedule);
    }
}
