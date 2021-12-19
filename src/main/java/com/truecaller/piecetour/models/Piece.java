package com.truecaller.piecetour.models;

import com.truecaller.piecetour.exceptions.InvalidMoveException;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * The type Piece.
 *
 * @author puneet created on 11/12/21
 */
@Data
public class Piece {

    private Position position;
    // List of legal moves
    private final List<Position> moves;

    /**
     * Instantiates a new Piece.
     *
     * @param rowMoves
     *            the row moves
     * @param colMoves
     *            the col moves
     * @param rowPos
     *            the row pos
     * @param colPos
     *            the col pos
     */
    public Piece(int[] rowMoves, int[] colMoves, int rowPos, int colPos) {
        this.position = new Position(rowPos, colPos);
        this.moves = initMoves(rowMoves, colMoves);
    }

    /**
     * Instantiates a new Piece.
     *
     * @param position
     *            the position
     * @param moves
     *            the moves
     */
    public Piece(Position position, List<Position> moves) {
        this.position = position;
        this.moves = moves;
    }

    /**
     * Instantiates a new Piece.
     *
     * @param rowPos
     *            the row pos
     * @param colPos
     *            the col pos
     * @param moves
     *            the moves
     */
    public Piece(int rowPos, int colPos, List<Position> moves) {

        this.position = new Position(rowPos, colPos);
        this.moves = moves;
    }

    private List<Position> initMoves(int[] rowMoves, int[] colMoves) {

        if (rowMoves == null || colMoves == null || rowMoves.length != colMoves.length) {
            throw new InvalidMoveException();
        }

        List<Position> allowedMoves = new ArrayList<>();
        for (int i = 0; i < rowMoves.length; i++) {
            allowedMoves.add(new Position(rowMoves[i], colMoves[i]));
        }
        return allowedMoves;
    }
}
