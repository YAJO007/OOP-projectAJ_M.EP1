package model;

public class GasCalculator {
    private double[][] baseHorizon;
    private final double topDifference = 200; // meters

    public void setBaseHorizon(double[][] data) {
        this.baseHorizon = data;
    }

    public double[][] calculateGas(double fluidContact) {
        int rows = baseHorizon.length;
        int cols = baseHorizon[0].length;
        double[][] percent = new double[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double topHorizon = baseHorizon[r][c] - topDifference;
                double totalHeight = baseHorizon[r][c] - topHorizon;
                double gasHeight = fluidContact - topHorizon;

                if (gasHeight < 0) gasHeight = 0;
                if (gasHeight > totalHeight) gasHeight = totalHeight;

                percent[r][c] = (gasHeight / totalHeight) * 100;
            }
        }
        return percent;
    }
}
