package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
        ArrayList<Integer> numList = new ArrayList<>();
        for (String number : list) {
            int num = Integer.parseInt(number);
            numList.add(num);
        }
        System.out.println(numList);
        numList.sort((o1, o2) -> o1.compareTo(o2));
        System.out.println(numList);
        int middle = numList.size() / 2;
        int median = numList.get(middle);
        System.out.println(middle + " ," + median);
        int ii = 0;
        for (Integer num : numList) {
            while (num != median) {
                if (num < median) {
                    num++;
                    ii++;
                }
                if (num > median) {
                    num--;
                    ii++;
                }
            }
        }
        System.out.println(i);
        System.out.println(ii);
        if (i <= ii) {
            System.out.println(i);
        } else {
            System.out.println(ii);
        }

    }
}