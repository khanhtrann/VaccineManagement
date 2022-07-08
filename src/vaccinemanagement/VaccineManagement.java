package vaccinemanagement;

import java.util.ArrayList;
import java.util.Scanner;
import menu.Menu;

public class VaccineManagement {

    public static void main(String[] args) {
        Menu menu = new Menu();
        ArrayList<String> stringList = new ArrayList();
        System.out.println("Welcome to Vaccine Management - @ 2021 by <SE160867 - Tran Thi Van Khanh>");
        stringList.add("Show information of all of the injection");
        stringList.add("Add a new injection information");
        stringList.add("Updating information of students' injection");
        stringList.add("Delete a student's injection information");
        stringList.add("Search for injection information by student ID");
        stringList.add("Search for injection information by student name");
        stringList.add("Store data to injection.txt file");
        stringList.add("Quit");

        int choice;
        String answer;
        Scanner sc = new Scanner(System.in);

        Cabinet cabinet = new Cabinet();
        String injectionFile = "injection.txt";
        cabinet.addFromFile(injectionFile);

        do {
            choice = menu.getIntChoice(stringList);
            switch (choice) {
                case 1:
                    cabinet.print();
                    cabinet.writeToFile();
                    break;
                case 2:
                    do {
                        cabinet.addInjection();
                        System.out.print("Do you want to continue? (Y/N): ");
                        answer = sc.nextLine();
                    } while (answer.equalsIgnoreCase("Y"));
                    cabinet.writeToFile();
                    break;
                case 3:
                    cabinet.updateInjection();
                    cabinet.writeToFile();
                    break;
                case 4:
                    cabinet.removeInjectionById();
                    cabinet.writeToFile();
                    break;
                case 5:
                    cabinet.searchInjectionByStudentID();
                    break;
                case 6:
                    cabinet.searchInjectionByStudentName();
                    break;               
                case 7:
                    System.out.println("See you next time!");
                    break;
            }
        } while (choice != 7);
    }

}
