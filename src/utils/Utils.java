package utils;

import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static String getString(String message) {
        String result = "";
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(message);
                String tmp = sc.nextLine();
                if (!tmp.isEmpty()) {
                    result = tmp;
                    check = false;
                }
            } catch (Exception e) {

            }
        } while (check);
        return result;
    }

    public static String updateString(String oldValue, String message) {
        String result = oldValue;
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();

        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String message) {
        int result = 0;
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(message);
                result = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (check);

        return result;
    }

    public static double getDouble(String message) {
        double result = 0;
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(message);
                result = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (check);

        return result;
    }

    public static Date getDate(String message) {
        Date result = null;
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(message);
                String tmp = sc.nextLine();
                long t = toDate(tmp);
                Date d = new Date(t);
                if (!tmp.isEmpty()) {
                    if (t < 0) {
                        System.out.println("Inputted date is invalid!");

                    } else {
                        result = d;
                        check = false;
                    }

                }
            } catch (Exception e) {

            }
        } while (check);
        return result;
    }

    public static boolean isLeap(int y) {
        boolean result = false;
        if ((y % 400 == 0) || (y % 4 == 0) && (y % 100 != 0)) {
            result = true;
        }
        return result;
    }

    public static boolean isValidDate(int y, int m, int d) {
        if (y < 0 || m < 0 || m > 12 || d < 0 || d > 31) {
            return false;
        }
        int maxD = 31;
        if (m == 4 || m == 6 || m == 9 || m == 11) {
            maxD = 30;
        } else if (m == 2) {
            if (isLeap(y)) {
                maxD = 29;
            } else {
                maxD = 28;
            }
        }
        return d <= maxD;
    }

    //convert y/m/d or y-m-d to milisec
    public static long toDate(String ymd) {
        StringTokenizer stk = new StringTokenizer(ymd, "/-");
        int y = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int d = Integer.parseInt(stk.nextToken());
        if (!isValidDate(y, m, d)) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, d);
        long t = cal.getTime().getTime();
        return t;
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
