package com.truecaller.piecetour;

import com.truecaller.piecetour.exceptions.InvalidLocationException;
import com.truecaller.piecetour.exceptions.TourNotFoundException;
import com.truecaller.piecetour.models.ChessBoard;
import com.truecaller.piecetour.models.Piece;
import com.truecaller.piecetour.models.Position;
import com.truecaller.piecetour.models.Cell;
import com.truecaller.piecetour.services.TourSearchService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class TourSearchServiceTest {

    private static ChessBoard chessBoard;

    private static Piece piece;

    @Before
    public void init() {

        chessBoard = new ChessBoard(10, 10);

        List<Position> moves = Arrays.asList(new Position(3, 0), new Position(0, -3), new Position(-3, 0),
                new Position(0, 3), new Position(2, 2), new Position(2, -2), new Position(-2, -2), new Position(-2, 2));

        piece = new Piece(4, 5, moves);

    }

    /**
     * Asserts that the tour has all the cells available on the board.
     *
     */
    @Test
    public void successPerformTour() {

        ChessBoard tour = null;

        while (tour == null) {

            try {

                tour = TourSearchService.getInstance().performTour(piece, chessBoard);

            } catch (TourNotFoundException ignored) {
            }
        }

        int countOfUnvisitedCell = 0;

        for (int i = 0; i < chessBoard.getRowCount(); i++) {
            for (int j = 0; j < chessBoard.getColCount(); j++) {
                if (Objects.equals((tour.getCells()[i][j]).getTourPosition(), Cell.DEFAULT_TOUR_POSITION)) {
                    countOfUnvisitedCell++;
                }
            }
        }
        assertEquals(0, countOfUnvisitedCell);

    }

    /**
     * Asserts that exception is raised when invalid initial chess piece position is provided.
     *
     */
    @Test(expected = InvalidLocationException.class)
    public void failedPerformTourDueToIncorrectInitialPosition() {

        piece.setPosition(new Position(10, 10));

        TourSearchService.getInstance().performTour(piece, chessBoard);

    }

    /**
     * Asserts that exception is raised when tour is not complete due to unavailability of unvisited neighbour cells on
     * the chess board.
     *
     */
    @Test(expected = TourNotFoundException.class)
    public void failedPerformTourDueToNoUnvisitedNeighbourFound() {

        Cell currCell = chessBoard.getCell(piece.getPosition());

        currCell.setTourPosition(1);

        int tourPos = currCell.getTourPosition();

        for (Position move : piece.getMoves()) {

            applyMove(chessBoard, currCell, move).setTourPosition(++tourPos);

        }

        TourSearchService.getInstance().performTour(piece, chessBoard);

    }

    private Cell applyMove(ChessBoard chessBoard, Cell currCell, Position move) {

        return chessBoard.getCell(new Position(currCell.getPosition().getRow() + move.getRow(),
                currCell.getPosition().getCol() + move.getCol()));

    }

}