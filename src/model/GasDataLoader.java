package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GasDataLoader {
    private double[][] data;

    public void load(File file) {
        try (Scanner sc = new Scanner(file)) {
            int size = (int) Math.sqrt(countValues(file));
            data = new double[size][size];

            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    data[r][c] = sc.nextDouble();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int countValues(File file) {
        int count = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextDouble()) {
                sc.nextDouble();
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public double[][] getData() {
        return data;
    }
}
