package vaccinemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class VaccineList {
    
    private ArrayList<Vaccine> vList = new ArrayList();
    
    public void addFromFile(String fName){
        try {
            File f = new File(fName);
            if(!f.exists())
                return;
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String details;
            while((details = br.readLine()) != null){
                StringTokenizer stk = new StringTokenizer(details, ",");
                String vaccineID = stk.nextToken().toUpperCase();
                String vaccineName = stk.nextToken().toUpperCase();
                Vaccine vaccine = new Vaccine(vaccineID, vaccineName);
                vList.add(vaccine);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int searchVaccineByID(String id) {
        if (vList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < vList.size(); i++) {
            if (vList.get(i).getVaccineID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        //does not find anything
        return -1;
    }
    
    public void printVaccine() {
        if (vList.isEmpty()) {
            System.out.println("The fridge is empty.");
            return;
        }        
        System.out.println("-------VACCINE LIST-------");
        for (int i = 0; i < vList.size(); i++) {
            System.out.println(vList.get(i).toString());
        }
    }
}
