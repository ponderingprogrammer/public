package net.ponderingprogrammer.map2d;

import java.util.ArrayList;
import java.util.Collection;

public class ManhattanFixedSquareMap2d<T> extends FixedSquareMap2d<T> {

    public ManhattanFixedSquareMap2d(int width, int height) {
        super(width, height);
    }

    @Override
    public Collection<Cell<T>> findAdjacentCells(int x, int y) {
        ArrayList<Cell<T>> adjacents = new ArrayList<>();
        getCell(x,y - 1).ifPresent(adjacents::add);    // top
        getCell(x + 1,y).ifPresent(adjacents::add);     // right
        getCell(x,y + 1).ifPresent(adjacents::add);     // bottom
        getCell(x - 1,y).ifPresent(adjacents::add);    // left
        return adjacents;
    }

    @Override
    public CellGraph<T> toCellGraph() {
        var graph = new CellGraph<T>();
        for (Cell<T> cell: getCells()){
            graph.getNodes().add(cell);
            for (Cell<T> adjacent: findAdjacentCells(cell.getCoord())) {
                if (cell == adjacent) continue;
                graph.getEdges().add(new CellGraphEdge<>(cell, adjacent));
            }
        }
        return graph;
    }
}
