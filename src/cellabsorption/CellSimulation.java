package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {
    private CanvasWindow canvas;
    private List<Cell> cells;
    private Random rand = new Random();

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            for (Cell cell : cells) {
                cell.moveAround(canvasCenter);
            }
            handleCellInteraction();
            canvas.draw();
            canvas.pause(10);
        }
    }

    private void populateCells() {
        cells = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            double size = rand.nextInt(5) + 2;
            Cell cell = createCell(
            rand.nextDouble() * (canvas.getWidth() - size),
            rand.nextDouble() * (canvas.getWidth() - size),
            size,
            Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
        canvas.add(cell.getShape());
        cells.add(cell);
        }
    }

    private static double sqr(double x) {
        return x * x;
    }

    private Cell createCell(double x, double y, double radius, Color color){
        return new Cell(x, y, radius, color);
    }
    
    private void handleCellInteraction() {
        for (int i = 0; i < cells.size(); i++) {
            Cell cell0 = cells.get(i);
            for (int j = i + 1; j < cells.size(); j++) {
                Cell cell1 = cells.get(j);
                cell0.interactWith(cell1);
            }
        }
    }
}
