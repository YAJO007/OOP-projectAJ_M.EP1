package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GasDataLoader {
    private double[][] data;

    // โหลดไฟล์ข้อมูล
    public void load(File file) {
        ArrayList<Double> values = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String token = sc.next();
                try {
                    double value = Double.parseDouble(token);
                    values.add(value);
                } catch (NumberFormatException e) {
                }
            }


            // หาขนาดตาราง NxN
            int size = (int) Math.sqrt(values.size());
            data = new double[size][size];

            int index = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    data[r][c] = values.get(index);
                    index++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("No File: " + file.getName());
        }
    }

    // คืนค่า data ให้ class อื่นใช้
    public double[][] getData() {
        return data;
    }
}
