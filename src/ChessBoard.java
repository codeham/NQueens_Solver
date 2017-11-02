import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChessBoard {
    private int[] chessBoard;
    private int size;
    ChessBoard(int size){
        this.size = size;
        this.chessBoard = randomizeBoard();
        //chessBoard = new int[]{4,5,6,3,4,5,6,5};
    }

    public int[] randomizeBoard(){
        Random rand = new Random();
        List<Integer> randomNumbers = new ArrayList<>();
        while(randomNumbers.size() < size){
            int random = rand.nextInt(size);
//            if(!randomNumbers.contains(random)){
//                randomNumbers.add(random);
//            }
            randomNumbers.add(random);
        }

        int[] arrayNums = new int[size];
        for(int i = 0; i < arrayNums.length; i++){
            arrayNums[i] = randomNumbers.get(i);
        }

        return arrayNums;
    }

    public int getSize() {
        return size;
    }

    public int[] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(int[] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public ChessBoard changeQueen(int colPosition, int rowPosition){
        int[] copyArray = Arrays.copyOf(this.chessBoard, size);
        // change the row position of the column
        copyArray[colPosition] = rowPosition;
        ChessBoard succ = new ChessBoard(size);
        succ.setChessBoard(copyArray);

        return succ;
    }

    public List<ChessBoard> generateSuccessors(){
        // grab the col position
        // scan down the column aisle, ignoring the original poisiton
        List<ChessBoard> successors = new ArrayList<>();

        for(int i = 0; i < size; i++){
            System.out.println();
            for (int j = 0; j < size; j++){
                if(chessBoard[i] == j){
                    continue;
                }else{
                    // generate successor with that new moved coordinate
                    // CURRENT ISSUE, SKIPPING 0's !!!!!
                    successors.add(changeQueen(i, j));
                }

            }
        }

        return successors;
    }

    public int calculateHeuristic(){
        //calculating the heuristic
        int[] currentBoard = chessBoard;
        int boardSize = size;
        int heuristicCost = 0;

        for(int i = 0; i < boardSize; i++){
            for (int j = i + 1; j < boardSize; j++){
                if(currentBoard[i] == currentBoard[j]) {
                    heuristicCost++;
                }
                int offset = j-i;
                if(currentBoard[i] == currentBoard[j]-offset || currentBoard[i] == currentBoard[j]+offset){
                    heuristicCost++;
                }
            }
        }
        return heuristicCost;
    }


    public void printBoard(){
        final String[][] twoDimBoard = new String[size][size];
        for(int i = 0; i < size; i++) {
            Arrays.fill(twoDimBoard[i], "_");
        }

        for(int x = 0; x < size; x++){
            System.out.println("col: " + x + ", row: " + chessBoard[x]);
            twoDimBoard[chessBoard[x]][x] = "Q";
        }

        System.out.println();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(" | " + twoDimBoard[i][j]);
            }
            System.out.print(" |");
            System.out.println();
        }
    }
}
