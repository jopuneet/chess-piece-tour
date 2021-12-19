package com.truecaller.piecetour.models;

import lombok.Data;

/**
 * The type Chess board.
 *
 * @author puneet created on 11/12/21
 */
@Data
public class ChessBoard {

    private Integer rowCount;
    private Integer colCount;
    private Cell[][] cells;

    /**
     * Instantiates a new Chess board.
     *
     * @param rowCount
     *            the row count
     * @param colCount
     *            the col count
     */
    public ChessBoard(Integer rowCount, Integer colCount) {

        this.rowCount = rowCount;
        this.colCount = colCount;
        initializeBoard();
    }

    private void initializeBoard() {

        this.cells = new Cell[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Gets Cell for the provided position.
     *
     * @param position
     *            the position
     * 
     * @return the cell
     */
    public Cell getCell(Position position) {
        return cells[position.getRow()][position.getCol()];
    }
}
