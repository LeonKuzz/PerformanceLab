package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        int walker = 1;
        String rout = "";
        ArrayList <String> intervals = new ArrayList<>();
        while (true) {
            int ii = 1;
            String interval = "";
            for (int i = 1; i <= m; i++) {
                interval = interval + walker;
                if ( i != m) {
                    walker++;
                }
                if (walker > n) {
                    walker = 1;
                }
            }
            intervals.add(interval);
            String lastSymbol = interval.substring(interval.length() - 1);
            String firstSymbol = interval.substring(0, 1);
            rout = rout + firstSymbol;
            if (lastSymbol.equals("1")) {
                break;
            }
        }
        System.out.println("Интервалы: " + intervals);
        System.out.println("Путь: " + rout);
        System.out.println("Длины пути: "+ rout.length());
    }
}