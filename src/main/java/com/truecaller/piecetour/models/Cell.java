package com.truecaller.piecetour.models;

import lombok.Data;

/**
 * The type Cell.
 *
 * @author puneet created on 11/12/21
 */
@Data
public class Cell {
    private Position position;
    private Integer tourPosition;

    /**
     * The constant DEFAULT_TOUR_POSITION.
     */
    public static Integer DEFAULT_TOUR_POSITION = -1;

    /**
     * Instantiates a new Cell.
     *
     * @param row
     *            the row
     * @param column
     *            the column
     */
    public Cell(Integer row, Integer column) {
        this.position = new Position(row, column);
        this.tourPosition = DEFAULT_TOUR_POSITION;
    }
}
