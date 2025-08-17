package model;

public class GasCalculator {
    private double fluidContact = 2500; // ระดับของ Fluid Contact
    private double topThickness = 200;  // ความหนาส่วนบน (เมตร)

    // คำนวณเปอร์เซ็นต์ก๊าซสำหรับค่าชั้นหินหนึ่งค่า
    public double calculateGas(double base) {
        double topLevel = base - topThickness;              // ระดับบนสุดของชั้นหิน
        double totalHeight = base - topLevel;               // ความสูงรวมของชั้นหิน
        double gasHeight = Math.max(0, Math.min(fluidContact, base) - topLevel); // ความสูงก๊าซ
        return (gasHeight / totalHeight) * 100;             // เปอร์เซ็นต์ก๊าซ
    }

    // คำนวณปริมาตรก๊าซสำหรับค่าชั้นหินหนึ่งค่า
    public double calculateVolume(double base, double cellSize) {
        double topLevel = base - topThickness;
        double gasHeight = Math.max(0, Math.min(fluidContact, base) - topLevel);
        if (gasHeight <= 0) return 0;
        return cellSize * cellSize * gasHeight; // volume = พื้นที่ × สูงก๊าซ
    }

    // ปรับค่า Fluid Contact
    public void setFluidContact(double f) {
        this.fluidContact = f;
    }
}
