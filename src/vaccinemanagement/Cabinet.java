package vaccinemanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.StringTokenizer;
import utils.Utils;

public class Cabinet {

    private ArrayList<Injection> iList = new ArrayList();
    StudentList studentList = new StudentList();
    VaccineList vaccineList = new VaccineList();

    public Cabinet() {
        studentList.addFromFile("student.txt");
        vaccineList.addFromFile("vaccine.txt");
    }

    public void addInjection() {
        int pos;
        String sID, vID, iID;
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);
        String answer;
        boolean check = true;
        sID = Utils.getString("Input student ID: ");
        Injection inject = searchInjectionObjectByStudentID(sID);
        if (inject != null) {
            //has not been injected
            if (inject.vaccineID.equalsIgnoreCase("empty")) {
                System.out.println("Please choose a vaccine");
                vaccineList.printVaccine();
                do {
                    vID = Utils.getString("Input vaccine ID: ");
                    pos = vaccineList.searchVaccineByID(vID);
                    if (pos >= 0) {
                        inject.vaccineID = vID;
                    } else {
                        System.out.println("Vaccine ID not found!");
                    }
                } while (pos == -1);
                do {
                    iID = Utils.getString("Input injection ID: ");
                    pos = searchInjectionByInjectionID(iID);
                    if (pos >= 0) {
                        System.out.println("The injection ID already existed. "
                                + "Input another one!");
                    }
                } while (pos >= 0);

                inject.injectionID = iID;

                inject.firstInjectionDate = Utils.getDate("First date: ");
                inject.firstInjectionPlace = Utils.getString("First place: ");

                if (Utils.getDifferenceDays(inject.firstInjectionDate, today) >= 28) {
                    answer = Utils.getString("Do you want 2nd injection? ");
                    if (answer.equalsIgnoreCase("Y")) {
                        do {
                            inject.secondInjectionDate = Utils.getDate("Second date: ");
                            if (Utils.getDifferenceDays(inject.firstInjectionDate, inject.secondInjectionDate) < 28) {
                                System.out.println("The second date must be at lease 4 weeks after first date");
                            } else {
                                check = false;
                            }
                        } while (check);
                        inject.secondInjectionPlace = Utils.getString("Second place: ");
                        System.out.println("An injection is added sucessfully!");
                    }

                }
                //has been injected once
            }
            if (inject.secondInjectionPlace.equals("empty")) {
                answer = Utils.getString("Do you want 2nd injection? ");
                if (answer.equalsIgnoreCase("Y")) {
                    do {
                        inject.secondInjectionDate = Utils.getDate("Second date: ");
                        if (Utils.getDifferenceDays(inject.firstInjectionDate, inject.secondInjectionDate) < 28) {
                            System.out.println("The second date must be at lease 4 weeks after first date");
                        } else {
                            check = false;
                        }
                    } while (check);
                    inject.secondInjectionPlace = Utils.getString("Second place: ");
                    System.out.println("An injection is added sucessfully!");
                }
            } else {
                //has been injected 2 times
                System.out.println("The student has been fully injected!");
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    public int searchInjectionByInjectionID(String id) {
        if (iList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < iList.size(); i++) {
            if (iList.get(i).getInjectionID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        //does not find anything
        return -1;
    }

    public void removeInjectionById() {
        String id;
        int pos;
        id = Utils.getString("Input injection ID you want to remove: ");
        pos = searchInjectionByInjectionID(id);

        if (pos == -1) {
            System.out.println("Not found!");
        } else {
            String answer = Utils.getString("Do you really want to delete this injection?: ");
            if (answer.equalsIgnoreCase("y")) {
                iList.remove(pos);
                System.out.println("The injection has been removed successfully!");
            } else {
                System.out.println("Remove failed!");
            }
        }
    }

    public int searchStudentByID(String id) {
        if (iList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < iList.size(); i++) {
            if (studentList.get(i).getStudentID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        //does not find anything
        return -1;
    }

    public void searchInjectionByStudentID() {
        String id = Utils.getString("Input student ID: ");
        int pos = searchStudentByID(id);
        if (pos >= 0) {
            System.out.println("-------SEARCH RESULT-------");
            System.out.println(iList.get(pos).toString());
        } else {
            System.out.println("Not found.");
        }
    }

    public void searchInjectionByStudentName() {
        if (iList.isEmpty()) {
            return;
        }
        String name = Utils.getString("Input student name: ");
        name = name.trim().toUpperCase();

        ArrayList<String> idList = new ArrayList();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentName().contains(name)) {
                idList.add(studentList.get(i).getStudentID());
            }
        }

        if (!idList.isEmpty()) {
            System.out.println("-------SEARCH RESULT-------");
            for (int i = 0; i < idList.size(); i++) {
                int pos = searchStudentByID(idList.get(i));
                System.out.println(iList.get(pos).toString());
            }
        } else {
            System.out.println("Not found.");
        }
    }

    public Injection searchInjectionObjectByStudentID(String id) {
        int pos = searchStudentByID(id);
        if (pos >= 0) {
            return iList.get(pos);
        } else {
            return null;
        }
    }

    public void updateInjection() {
        String injectionID = Utils.getString("Input injection ID: ");
        boolean check = true;
        int pos = searchInjectionByInjectionID(injectionID);
        if (pos >= 0) {
            Injection in = iList.get(pos);
            System.out.println("-------UPDATE INJECTION-------");
            in.firstInjectionDate = Utils.getDate("New first injection date: ");
            in.firstInjectionPlace = Utils.updateString(in.firstInjectionPlace, "New first injection place: ");
            do {
                in.secondInjectionDate = Utils.getDate("New second injection date: ");
                if (Utils.getDifferenceDays(in.firstInjectionDate, in.secondInjectionDate) < 28) {
                    System.out.println("The second date must be at lease 4 weeks after first date");
                } else {
                    check = false;
                }
            } while (check);
            in.secondInjectionPlace = Utils.updateString(in.secondInjectionPlace, "New second injection place: ");
            System.out.println("Update successful!");
        } else {
            System.out.println("Injection does not exist.");
        }
    }

    public boolean writeFile(String file, String data) {
        boolean check = false;
        BufferedWriter bw = null;
        try {
            Writer w = new FileWriter(file);
            bw = new BufferedWriter(w);
            bw.write(data);
            check = true;
            bw.close();
        } catch (IOException e) {
        }

        return check;
    }

    public void writeToFile() {
        String fName = "injection.txt";
        String content = "";
        for (Injection injection : iList) {
            content += injection.toString() + "\n";
        }
        content = encrypt(content, 5);
        try {
            writeFile(fName, content);
            System.out.println("Your file has been updated!");
        } catch (Exception e) {
            System.out.println("Failed to create your file.");
        }
    }

    public String encrypt(String plaintext, int shift) {
        char alphabet;
        String ciphertext = "";
        for (int i = 0; i < plaintext.length(); i++) {
            // Shift one character at a time
            alphabet = plaintext.charAt(i);

            // if alphabet lies between a and z 
            if (alphabet >= 'a' && alphabet <= 'z') {
                // shift alphabet
                alphabet = (char) (alphabet + shift);
                // if shift alphabet greater than 'z'
                if (alphabet > 'z') {
                    // reshift to starting position 
                    alphabet = (char) (alphabet + 'a' - 'z' - 1);
                }
                ciphertext = ciphertext + alphabet;
            } // if alphabet lies between 'A'and 'Z'
            else if (alphabet >= 'A' && alphabet <= 'Z') {
                // shift alphabet
                alphabet = (char) (alphabet + shift);

                // if shift alphabet greater than 'Z'
                if (alphabet > 'Z') {
                    //reshift to starting position 
                    alphabet = (char) (alphabet + 'A' - 'Z' - 1);
                }
                ciphertext = ciphertext + alphabet;
            } else {
                ciphertext = ciphertext + alphabet;
            }
        }
        return ciphertext;
    }

    public String decrypt(String ciphertext, int shift) {
        String decryptMessage = "";
        for (int i = 0; i < ciphertext.length(); i++) {
            // Shift one character at a time
            char alphabet = ciphertext.charAt(i);
            // if alphabet lies between a and z 
            if (alphabet >= 'a' && alphabet <= 'z') {
                // shift alphabet
                alphabet = (char) (alphabet - shift);

                // shift alphabet lesser than 'a'
                if (alphabet < 'a') {
                    //reshift to starting position 
                    alphabet = (char) (alphabet - 'a' + 'z' + 1);
                }
                decryptMessage = decryptMessage + alphabet;
            } // if alphabet lies between A and Z
            else if (alphabet >= 'A' && alphabet <= 'Z') {
                // shift alphabet
                alphabet = (char) (alphabet - shift);

                //shift alphabet lesser than 'A'
                if (alphabet < 'A') {
                    // reshift to starting position 
                    alphabet = (char) (alphabet - 'A' + 'Z' + 1);
                }
                decryptMessage = decryptMessage + alphabet;
            } else {
                decryptMessage = decryptMessage + alphabet;
            }
        }
        return decryptMessage;

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
                details = decrypt(details, 5);
                StringTokenizer stk = new StringTokenizer(details, ",");
                String injectionID = stk.nextToken().toUpperCase();
                String studentID = stk.nextToken().toUpperCase();
                if (studentList.searchInSListStudentByID(studentID) >= 0) {
                    String vaccineID = stk.nextToken().toUpperCase();
                    String firstDate = stk.nextToken().toUpperCase();                 
                    long t1 = Utils.toDate(firstDate);
                    Date firstInjectionDate = new Date(t1);
                    String firstInjectionPlace = stk.nextToken().toUpperCase();
                    String secondDate = stk.nextToken().toUpperCase();
                    long t2 = Utils.toDate(secondDate);
                    Date secondInjectionDate = new Date(t2);
                    String secondInjectionPlace = stk.nextToken().toUpperCase();
                    Injection injection = new Injection(injectionID, studentID, vaccineID,
                            firstInjectionDate, firstInjectionPlace, secondInjectionDate, secondInjectionPlace);
                    iList.add(injection);
                }

            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i = 0; i < studentList.size(); i++) {
            String sID = studentList.get(i).getStudentID();
            for (int j = 0; j <= iList.size(); j++) {
                if (searchStudentByID(sID) < 0) { //if not find the student in iList
                    Student st = studentList.get(studentList.searchInSListStudentByID(sID));
                    Injection in = new Injection(st);
                    iList.add(in);
                }
            }
        }
    }

    public void print() {
        if (iList.isEmpty()) {
            System.out.println("Empty List");
            return;
        }

        System.out.println("\nINJECTION LIST");
        for (Injection in : iList) {
            System.out.println(in.toString());
        }
    }

}
