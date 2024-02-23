package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> file1 = Files.readAllLines(Path.of("data/file1.txt"));
        List<String> file2 = Files.readAllLines(Path.of("data/file2.txt"));
        for (int i = 0; i < file2.size(); i++){
            double line = getLength(file2.get(i), file1.get(0));
            double r = Double.parseDouble(file1.get(1));
            if (line < r) {
                System.out.println(1);
            }
            if (line == r) {
                System.out.println(0);
            }
            if (line > r) {
                System.out.println(2);
            }
        }


    }
    public static double getLength(String point, String centre){
        String[] pointArray = point.split(" ");
        String xx = pointArray[0];
        Double x = Double.parseDouble(xx);
        String yy = pointArray[1];
        Double y = Double.parseDouble(yy);
        String[] centreArray = centre.split(" ");
        String xx1 = centreArray[0];
        Double x1 = Double.parseDouble(xx1);
        String yy1 = centreArray[1];
        Double y1 = Double.parseDouble(yy1);
        return Math.sqrt(((x - x1) * (x - x1)) + ((y - y1) * (y - y1)));

    }
}