package model;

public class GasCalculator {
    private double[][] baseHorizon;          // ข้อมูลชั้นหิน
    private final double topDifference = 200; // ความหนาส่วนบน (เมตร)

    // เก็บข้อมูลชั้นหิน
    public void setBaseHorizon(double[][] data) {
        this.baseHorizon = data;
    }

    // คำนวณเปอร์เซ็นต์ก๊าซทุก cell ในตาราง
    public double[][] calculateGas(double fluidContact) {
        int rows = baseHorizon.length;
        int cols = baseHorizon[0].length;
        double[][] percent = new double[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double topLevel = baseHorizon[r][c] - topDifference;  // ระดับบนสุด
                double totalHeight = baseHorizon[r][c] - topLevel;    // ความสูงรวม
                double gasHeight = fluidContact - topLevel;           // ความสูงก๊าซ

                if (gasHeight < 0) gasHeight = 0;                    // ถ้า < 0 ปรับเป็น 0
                if (gasHeight > totalHeight) gasHeight = totalHeight; // ถ้าเกินปรับเป็น max

                percent[r][c] = (gasHeight / totalHeight) * 100;      // แปลงเป็น %
            }
        }
        return percent;
    }
}
