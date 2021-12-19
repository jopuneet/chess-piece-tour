package com.truecaller.piecetour;

import com.truecaller.piecetour.models.ChessBoard;
import com.truecaller.piecetour.models.Position;
import com.truecaller.piecetour.models.Cell;
import com.truecaller.piecetour.services.NextCellService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class NextCellServiceTest {

    private static ChessBoard chessBoard;
    private static Cell currCell;
    private static List<Position> moves;

    @Before
    public void init() {

        chessBoard = new ChessBoard(10, 10);

        currCell = chessBoard.getCell(new Position(4, 5));

        currCell.setTourPosition(1);

        moves = Arrays.asList(new Position(3, 0), new Position(0, -3), new Position(-3, 0), new Position(0, 3),
                new Position(2, 2), new Position(2, -2), new Position(-2, -2), new Position(-2, 2));

    }

    /**
     * Asserts that returned cell is a neighbour of current cell and its tour position is next to current cell's tour
     * position.
     *
     */
    @Test
    public void successFindNextCell() {

        Cell nextCell = NextCellService.getInstance().findNextCell(chessBoard, moves, currCell);

        assertTrue(moves.stream().anyMatch(move -> Objects.deepEquals(nextCell, applyMove(chessBoard, currCell, move)))
                && Objects.equals(currCell.getTourPosition() + 1, nextCell.getTourPosition()));

    }

    private Cell applyMove(ChessBoard chessBoard, Cell currCell, Position move) {

        return chessBoard.getCell(new Position(currCell.getPosition().getRow() + move.getRow(),
                currCell.getPosition().getCol() + move.getCol()));

    }

    /**
     * Asserts that next cell is not found if all neighbours have already been visited.
     *
     */
    @Test
    public void failedFindNextCell() {

        int tourPos = currCell.getTourPosition();

        for (Position move : moves) {

            applyMove(chessBoard, currCell, move).setTourPosition(++tourPos);

        }

        Cell nextCell = NextCellService.getInstance().findNextCell(chessBoard, moves, currCell);

        assertNull(nextCell);

    }

}