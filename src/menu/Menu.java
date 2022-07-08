package menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu<T> {

    Scanner sc = new Scanner(System.in);
    
    public int getIntChoice(ArrayList list){
        for (int i = 0; i < list.size(); i++) 
            System.out.println((i+1) + ". " + list.get(i));
        System.out.println("---------------------");
        System.out.print("Please choose " + "1..." + list.size() + ": ");
        return Integer.parseInt(sc.nextLine());
    }
    
    public T getObjChoice(ArrayList<T> list){
        int n = list.size();
        for (int i = 0; i < list.size(); i++) 
            System.out.println((i+1) + ". " + list.get(i));
        System.out.println("---------------------");
        System.out.print("Please choose " + "1..." + list.size() + ": ");
        int choiceNo = Integer.parseInt(sc.nextLine());
        return (choiceNo > 0 && choiceNo <= n) ? list.get(choiceNo - 1) : null;
    }
}
