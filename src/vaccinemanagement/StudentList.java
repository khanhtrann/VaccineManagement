package vaccinemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StudentList {

    private ArrayList<Student> sList = new ArrayList();
    
    public int size(){
        return sList.size();
    }
    
    public Student get(int i){
        return sList.get(i);
    }
    
    public void addFromFile(String fName) {
        try {
            File f = new File(fName);
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String details;
            while ((details = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String studentID = stk.nextToken().toUpperCase();
                String studentName = stk.nextToken().toUpperCase();
                Student student = new Student(studentID, studentName);
                sList.add(student);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int searchInSListStudentByID(String id) {
        if (sList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < sList.size(); i++) {
            if (sList.get(i).getStudentID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        //does not find anything
        return -1;
    }
    
    public void printStudent() {
        if (sList.isEmpty()) {
            System.out.println("The fridge is empty.");
            return;
        }
        
        System.out.println("-------STUDENT LIST-------");
        for (int i = 0; i < sList.size(); i++) {
            System.out.println(sList.get(i).toString());
        }
    }
}
