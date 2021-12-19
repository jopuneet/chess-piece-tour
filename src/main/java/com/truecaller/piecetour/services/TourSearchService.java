package com.truecaller.piecetour.services;

import com.truecaller.piecetour.exceptions.InvalidLocationException;
import com.truecaller.piecetour.exceptions.TourNotFoundException;
import com.truecaller.piecetour.models.Cell;
import com.truecaller.piecetour.models.ChessBoard;
import com.truecaller.piecetour.models.Piece;
import com.truecaller.piecetour.utils.TourUtils;

/**
 * The type Tour finder service.
 *
 * @author puneet created on 11/12/21
 */
public class TourSearchService {

    private static NextCellService nextCellService = NextCellService.getInstance();

    private static TourSearchService instance = new TourSearchService();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TourSearchService getInstance() {
        return instance;
    }

    private TourSearchService() {
    }

    /**
     * Generates the legal moves using Warnsdorff's heuristics. Throws exception if not possible.
     *
     * @param piece
     *            Chess piece
     * @param chessBoard
     *            board on which tour will be performed
     * 
     * @return Board with each space marked with its visit position in tour.
     */
    public ChessBoard performTour(Piece piece, ChessBoard chessBoard) {

        if (!TourUtils.isValidPosition(piece.getPosition(), chessBoard.getRowCount(), chessBoard.getColCount())) {
            throw new InvalidLocationException();
        }

        markInitialPositionCell(piece, chessBoard);
        return search(piece, chessBoard);
    }

    /**
     * Returns a tour on the board for the chess piece.
     *
     * @param piece
     *            chess piece for which tour is to be performed
     * @param chessBoard
     *            board on which tour will be performed.
     * 
     * @return complete tour on chess board cells.
     */
    private ChessBoard search(Piece piece, ChessBoard chessBoard) {

        Cell nextCell = null, currCell = getInitialCell(piece, chessBoard);

        for (int i = 0; i < chessBoard.getRowCount() * chessBoard.getColCount() - 1; ++i) {
            nextCell = nextCellService.findNextCell(chessBoard, piece.getMoves(), currCell);
            if (nextCell == null) {
                throw new TourNotFoundException();
            }
            currCell = nextCell;
        }

        if (!TourUtils.areNeighbours(piece.getMoves(), nextCell, currCell)) {
            throw new TourNotFoundException();
        }
        return chessBoard;
    }

    /**
     * Marks the initial position of the piece on board as visited and assigns it tour position.
     *
     * @param piece
     *            chess piece for which tour is to be performed
     * @param chessBoard
     *            board on which tour will be performed.
     */
    private void markInitialPositionCell(Piece piece, ChessBoard chessBoard) {
        Cell initialCell = getInitialCell(piece, chessBoard);
        initialCell.setTourPosition(1);
    }

    /**
     * Returns the cell from where tour starts on the chessboard for the piece.
     *
     * @param piece
     *            chess piece for which tour is to be found.
     * @param chessBoard
     *            board on which tour will be marked.
     * 
     * @return cell initial position of piece on chessboard.
     */
    private Cell getInitialCell(Piece piece, ChessBoard chessBoard) {
        return chessBoard.getCells()[piece.getPosition().getRow()][piece.getPosition().getCol()];
    }
}
