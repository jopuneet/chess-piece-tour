package com.truecaller.piecetour.services;

import com.truecaller.piecetour.models.Cell;
import com.truecaller.piecetour.models.ChessBoard;
import com.truecaller.piecetour.models.Position;
import com.truecaller.piecetour.utils.TourUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Next cell service.
 *
 * @author puneet created on 11/12/21
 */
public class NextCellService {

    private static NextCellService instance = new NextCellService();

    private NextCellService() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static NextCellService getInstance() {
        return instance;
    }

    /**
     * Try all N adjacent of (*x, *y) starting from a random adjacent. Find the adjacent with minimum degree.
     *
     * @param chessBoard
     *            current board
     * @param moves
     *            List of possible moves.
     * @param currCell
     *            cell under consideration
     * 
     * @return cell next position on tour
     */
    public Cell findNextCell(ChessBoard chessBoard, List<Position> moves, Cell currCell) {
        int minDegreeIndex = getMinDegreeMoveIndex(chessBoard, moves, currCell);

        if (minDegreeIndex < 0) {
            return null;
        }

        Position neighbourCoordinates = TourUtils.getCoordinatesOfNeighbour(currCell, moves.get(minDegreeIndex));
        Cell nextCell = chessBoard.getCell(neighbourCoordinates);
        nextCell.setTourPosition(currCell.getTourPosition() + 1);
        return nextCell;
    }

    /**
     * Finds the index of move which would result in neighbour with minimum degree.
     *
     * @param chessBoard
     *            board with current partial tour
     * @param moves
     *            allowed moves for the piece
     * @param currCell
     *            current position on tour
     * 
     * @return index of the move which results in neighbour with minimum degree.
     */
    private int getMinDegreeMoveIndex(ChessBoard chessBoard, List<Position> moves, Cell currCell) {

        int start = ThreadLocalRandom.current().nextInt(1000) % moves.size();
        int currNeighbourDegree, minDegree = Integer.MAX_VALUE, minDegreeIndex = -1;
        for (int count = 0; count < moves.size(); ++count) {
            int i = (start + count) % moves.size();
            if (TourUtils.isUnvisited(chessBoard, TourUtils.getCoordinatesOfNeighbour(currCell, moves.get(i)))) {
                currNeighbourDegree = getDegree(chessBoard, moves,
                        chessBoard.getCell(TourUtils.getCoordinatesOfNeighbour(currCell, moves.get(i))));
                if (currNeighbourDegree < minDegree) {
                    minDegreeIndex = i;
                    minDegree = currNeighbourDegree;
                }
            }
        }
        return minDegreeIndex;
    }

    /**
     * Returns degree of a cell.
     *
     * @param chessBoard
     *            board with partial tour
     * @param moves
     *            allowed moves
     * @param cell
     *            cell for which degree is to be found.
     * 
     * @return degree of cell.
     */
    private int getDegree(ChessBoard chessBoard, List<Position> moves, Cell cell) {
        return (int) moves.stream()
                .filter(move -> TourUtils.isUnvisited(chessBoard, TourUtils.getCoordinatesOfNeighbour(cell, move)))
                .count();
    }
}
