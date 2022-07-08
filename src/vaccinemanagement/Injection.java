package vaccinemanagement;

import java.sql.Date;

public class Injection {

    String injectionID;
    String firstInjectionPlace, secondInjectionPlace;
    Date firstInjectionDate, secondInjectionDate;
    String studentID, vaccineID;

    public Injection() {
    }

    public Injection(Student st) {
        this.studentID = st.studentID;
        this.injectionID = "empty";
        this.vaccineID = "empty";
        this.firstInjectionDate = null;
        this.firstInjectionPlace = "empty";
        this.secondInjectionDate = null;
        this.secondInjectionPlace = "empty";
    }

    public Injection(String injectionID, String studentID, String vaccineID, Date firstInjectionDate, String firstInjectionPlace, Date secondInjectionDate, String secondInjectionPlace) {
        this.injectionID = injectionID;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.firstInjectionDate = firstInjectionDate;
        this.firstInjectionPlace = firstInjectionPlace;
        this.secondInjectionDate = secondInjectionDate;
        this.secondInjectionPlace = secondInjectionPlace;

    }

    public String getInjectionID() {
        return injectionID;
    }

    public void setInjectionID(String injectionID) {
        this.injectionID = injectionID;
    }

    public String getFirstInjectionPlace() {
        return firstInjectionPlace;
    }

    public void setFirstInjectionPlace(String firstInjectionPlace) {
        this.firstInjectionPlace = firstInjectionPlace;
    }

    public String getSecondInjectionPlace() {
        return secondInjectionPlace;
    }

    public void setSecondInjectionPlace(String secondInjectionPlace) {
        this.secondInjectionPlace = secondInjectionPlace;
    }

    public Date getFirstInjectionDate() {
        return firstInjectionDate;
    }

    public void setFirstInjectionDate(Date firstInjectionDate) {
        this.firstInjectionDate = firstInjectionDate;
    }

    public Date getSecondInjectionDate() {
        return secondInjectionDate;
    }

    public void setSecondInjectionDate(Date secondInjectionDate) {
        this.secondInjectionDate = secondInjectionDate;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    @Override
    public String toString() {
        return injectionID + "," + studentID + "," + vaccineID + "," + firstInjectionDate + "," + firstInjectionPlace + "," + secondInjectionDate + "," + secondInjectionPlace;
    }

}
