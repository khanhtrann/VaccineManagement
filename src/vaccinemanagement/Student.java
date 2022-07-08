package vaccinemanagement;

public class Student implements Comparable<Student> {

    String studentID;
    String studentName;

    public Student() {
    }

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return studentID + ',' + studentName;
    }

    @Override
    public int compareTo(Student t) {
        return this.studentID.compareTo(t.studentID);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
