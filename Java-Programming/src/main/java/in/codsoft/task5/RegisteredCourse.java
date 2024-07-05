package in.codsoft.task5;

public class RegisteredCourse {
    private String studentId;
    private String courseCode;

    public RegisteredCourse(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {
        return "RegisteredCourse{" +
                "studentId='" + studentId + '\'' +
                ", courseCode='" + courseCode + '\'' +
                '}';
    }
}
