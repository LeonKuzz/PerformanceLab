package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner((System.in));
        String path = scanner.nextLine();
        List<String> list = Files.readAllLines(Path.of(path));
        double average = 0;
        for (int i = 0; i < list.size(); i++) {
            average = average + Double.parseDouble(list.get(i));
        }
        average = Math.round((average / list.size()));
        int i = 0;
        for (String number : list) {
            int num = Integer.parseInt(number);
            while (num != average) {
                if (num < average) {
                  num++;
                  i++;
                }
                if (num > average) {
                    num--;
                    i++;
                }
            }
        }

        System.out.println(i);
    }
}