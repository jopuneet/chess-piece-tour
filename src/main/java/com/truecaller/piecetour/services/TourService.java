package com.truecaller.piecetour.services;

import com.truecaller.piecetour.exceptions.TourNotFoundException;
import com.truecaller.piecetour.models.Cell;
import com.truecaller.piecetour.models.ChessBoard;
import com.truecaller.piecetour.models.Piece;

import java.text.DecimalFormat;

/**
 * The type Tour service.
 *
 * @author puneet created on 11/12/21
 */
public class TourService {

    private static TourService instance = new TourService();

    private static OutputService outputService = OutputService.getInstance();

    private static TourSearchService tourFinderService = TourSearchService.getInstance();

    private static Integer MAX_RETRIES = 1000;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TourService getInstance() {
        return instance;
    }

    private TourService() {
    }

    /**
     * Performs a tour for a chess piece on the given board and outputs the tour.
     *
     * @param chessPiece
     *            chess piece for which tour is to be performed.
     * @param boardRowCount
     *            board's vertical dimension
     * @param boardColumnCount
     *            board's horizontal dimension
     */
    public void run(Piece chessPiece, int boardRowCount, int boardColumnCount) {

        int retries = 0;
        do {
            try {
                retries++;
                ChessBoard tour = tourFinderService.performTour(chessPiece,
                        new ChessBoard(boardRowCount, boardColumnCount));
                printTour(tour);
                return;
            } catch (TourNotFoundException e) {
                outputService.print("Retrying: " + retries);
            } catch (Exception e) {
                outputService.print("Exception occurred while trying to find a tour." + e);
                return;
            }
        } while (retries < MAX_RETRIES);

        outputService.print("Unable to find tour with given initial position.");
    }

    /**
     * Prints the chessboard with all the legal piece's moves
     *
     * @param board
     *            board with spaces marked with their tour position.
     */
    private void printTour(ChessBoard board) {

        DecimalFormat twoDigits = new DecimalFormat("00");
        outputService.print("\nTour : ");
        for (Cell[] cells : board.getCells()) {
            StringBuilder sb = new StringBuilder();
            for (Cell cell : cells) {
                sb.append("   ").append(twoDigits.format(cell.getTourPosition()));
            }
            outputService.print(sb.toString());
        }
    }
}
