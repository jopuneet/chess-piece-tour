package com.truecaller.piecetour;

import com.truecaller.piecetour.exceptions.InvalidLocationException;
import com.truecaller.piecetour.models.Piece;
import com.truecaller.piecetour.services.TourService;
import com.truecaller.piecetour.services.OutputService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PieceTourApplication {

    private static OutputService outputService = OutputService.getInstance();
    private static TourService tourService = TourService.getInstance();

    /**
     * The entry point of application.
     *
     * @param args
     *            the input arguments
     */
    public static void main(String[] args) {

        printInfo();
        outputService.print("\nEnter initial position of the piece (in the following format->  x y ) :");

        Scanner sc = new Scanner(System.in);
        int initialRow = 0, initialCol = 0;

        do {
            try {
                initialRow = sc.nextInt();
                initialCol = sc.nextInt();
                if (initialCol < 0 || initialRow < 0 || initialRow > 9 || initialCol > 9) {
                    throw new InvalidLocationException();
                }
            } catch (InvalidLocationException e) {
                outputService.print("The location entered are outside the board. Please enter valid positions.");
            } catch (InputMismatchException e) {
                outputService.print("Invalid INPUT received!! Exiting!!");
                break;
            }

            PieceTourApplication app = new PieceTourApplication();
            app.startTourFromProvidedPosition(initialRow, initialCol);
            outputService.print("\nEnter initial position of the piece (in the following format:- x y) :");
        } while (sc.hasNext());
        sc.close();
    }

    /**
     * Start tour from provided position.
     *
     * @param initialRow
     *            the initial row
     * @param initialCol
     *            the initial col
     */
    private void startTourFromProvidedPosition(int initialRow, int initialCol) {
        Piece piece = createChessPiece(initialRow, initialCol);
        // Passing 10X10 row-column as provided in the problem
        tourService.run(piece, 10, 10);
    }

    /**
     * Creates chess piece.
     *
     * @param initialRow
     *            the initial row
     * @param initialCol
     *            the initial col
     * 
     * @return the chess piece
     */
    private Piece createChessPiece(int initialRow, int initialCol) {

        int[] rowMoves = { 3, 2, 0, -2, -3, -2, 0, 2 };
        int[] colMoves = { 0, -2, -3, -2, 0, 2, 3, 2 };
        return new Piece(rowMoves, colMoves, initialRow, initialCol);
    }

    /**
     * Print info.
     */
    private static void printInfo() {

        outputService.print("###############################################################");
        outputService.print("#  This application produces a chess piece's tour, starting   #");
        outputService.print("#  from initial position provided by user. The cell indexing  #");
        outputService.print("#  is zero based. If non-integer/negative inputs are provided,#");
        outputService.print("#  the program exits.                                         #");
        outputService.print("###############################################################");
    }
}
