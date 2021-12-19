package com.truecaller.piecetour.utils;

import com.truecaller.piecetour.models.Cell;
import com.truecaller.piecetour.models.ChessBoard;
import com.truecaller.piecetour.models.Position;

import java.util.List;
import java.util.Objects;

/**
 * @author puneet created on 11/12/21
 */
public class TourUtils {

    /**
     * Returns whether a target position can be reached from initial position given the set of legal moves.
     *
     * @param moves
     *            allowed moves
     * @param cell
     *            initial position
     * @param neighbourCell
     *            target position
     *
     * @return if target position is reachable from initial position
     */
    public static boolean areNeighbours(List<Position> moves, Cell cell, Cell neighbourCell) {
        return moves.stream().anyMatch(
                move -> ((cell.getPosition().getRow() + move.getRow()) == neighbourCell.getPosition().getRow())
                        && ((cell.getPosition().getCol() + move.getCol()) == neighbourCell.getPosition().getCol()))
                || (Objects.equals(cell.getPosition().getRow(), neighbourCell.getPosition().getRow())
                        && Objects.equals(cell.getPosition().getCol(), neighbourCell.getPosition().getCol()));
    }

    /**
     * Returns whether a position lies within board boundary.
     *
     * @param position
     *            coordinates of position under consideration
     * @param rowCount
     *            board dimension
     * @param colCount
     *            board dimension
     *
     * @return if position is valid with respect to constraints.
     */
    public static boolean isValidPosition(Position position, Integer rowCount, Integer colCount) {
        return position.getRow() >= 0 && position.getRow() < rowCount && position.getCol() >= 0
                && position.getCol() < colCount;
    }

    /**
     * Returns whether the coordinates are valid and if valid whether cell at given coordinates is unvisited.
     *
     * @param chessBoard
     *            board with partial tour
     * @param coordinates
     *            coordinates of cell under consideration
     *
     * @return if cell at given coordinate is unvisited
     */
    public static boolean isUnvisited(ChessBoard chessBoard, Position coordinates) {
        return coordinates.getRow() >= 0 && coordinates.getRow() < chessBoard.getRowCount() && coordinates.getCol() >= 0
                && coordinates.getCol() < chessBoard.getColCount()
                && Objects.equals(chessBoard.getCell(coordinates).getTourPosition(), Cell.DEFAULT_TOUR_POSITION);
    }

    /**
     * Returns coordinates of the neighbour when a move is applied to the current cell
     *
     * @param currCell
     *            current cell
     * @param move
     *            move which is to be applied
     *
     * @return coordinates of the neighbour
     */
    public static Position getCoordinatesOfNeighbour(Cell currCell, Position move) {
        return new Position(currCell.getPosition().getRow() + move.getRow(),
                currCell.getPosition().getCol() + move.getCol());
    }
}
