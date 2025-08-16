package model;

public class GasCalculator {
    private double[][] baseHorizon;   // เก็บค่าพื้นชั้นหิน
    private double topThickness = 200; // ความหนาส่วนบน (เมตร)

    // กำหนดค่าพื้นชั้นหินจากข้อมูล
    public void setBaseHorizon(double[][] data) {
        this.baseHorizon = data;
    }

    // คำนวณปริมาณก๊าซในรูปแบบเปอร์เซ็นต์
    public double[][] calculateGas(double fluidContact) {
        int rows = baseHorizon.length;
        int cols = baseHorizon[0].length;

        // สร้างตารางใหม่สำหรับเก็บค่าที่คำนวณได้
        double[][] result = new double[rows][cols];

        // วนลูปทุกตำแหน่ง
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // ค่าระดับบนสุดของชั้นหิน (Top Horizon)
                double topLevel = baseHorizon[r][c] - topThickness;

                // ความสูงทั้งหมดของชั้นหิน
                double totalHeight = baseHorizon[r][c] - topLevel;

                // ระดับความสูงที่มีก๊าซ (จาก Top จนถึง Fluid Contact)
                double gasHeight = fluidContact - topLevel;

                // ตรวจสอบไม่ให้ค่าติดลบหรือเกินกว่าความสูงจริง
                if (gasHeight < 0) {
                    gasHeight = 0;
                }
                if (gasHeight > totalHeight) {
                    gasHeight = totalHeight;
                }

                // คำนวณเปอร์เซ็นต์ปริมาณก๊าซ
                result[r][c] = (gasHeight / totalHeight) * 100;
            }
        }

        return result;
    }
}
