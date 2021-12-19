# Assignment: Chess Piece Tour

# Problem statement

The assignment is to write a program that finds at least one tour for a chess piece on a 10-by-10 chessboard. A tour is a path for a piece to visit all tiles on the board, following a set of rules for movement. Assume the piece can start in any tile.

The four rules of movement for the piece are:

a) The piece can move 3 spaces either North, East, South, or West.

b) The piece can move 2 spaces diagonally:Northeast, Southeast, Southwest, or Northwest.

c) Each space can only be visited once.

## Solution:

There are several ways to find a piece's tour on a given chess board. 

In this application **Warnsdorff's rule** has been chosen.

Since Warnsdorff's rule is a heuristic,
it might not be able to find a tour in the first iteration itself and hence have added retry logic with an upper limit of 100 retries.

For details of Warnsdorff's Rule: https://en.wikipedia.org/wiki/Knight%27s_tour .

Since there are lots of possible sequences for a knight's tour, heuristics are used to guide our search. 
Warnsdoff's rule is also one such heuristic. The heuristic is to visit a square which has less number of non-visited neighbors. 
This is quite intuitive because if we have to visit all the squares in a tour, we better visit those squares which are remote and are difficult to be reached.
As with any heuristic, there is no mathematical proof for Warnsdoff's rule that it will always produce a valid tour.

## Prerequisites:

For test and run this project you just need to have java 8 and `mvn` command in your path.

To use and install maven follow [this link](https://maven.apache.org/install.html).

## Assumptions:

Project builds upon maven and java and assumes maven is pre-installed. 
External libraries have been used in the project for build and packaging.

`Board dimensions to be 10 X 10, Valid values are 0 to 9`

`Maximum retries for finding the heuristics is set to be 100`


## Run Tests:

There are some integration and unit tests for project, you can run them by this command:

``` mvn test```

## Run project:

In order to run this project you need to run this command:

Build:

```mvn clean install```

Run:

```mvn exec:java -Dexec.mainClass="com.truecaller.piecetour.PieceTourApplication"```

Then program will ask for entering the initial coordinates which must be entered in a space separated way.
Enter the coordinates and press enter and program will display the tour for the piece in the board starting from the initial entered location.

Enter the coordinates value between 0-9 for row and column.