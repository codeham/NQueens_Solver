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
    }

    public int[] randomizeBoard(){
        Random rand = new Random();
        List<Integer> randomNumbers = new ArrayList<>();
        while(randomNumbers.size() < size){
            int random = rand.nextInt(size);
            if(!randomNumbers.contains(random)){
                randomNumbers.add(random);
            }
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

    public void printBoard(){
        final String[][] twoDimBoard = new String[size][size];
        for(int i = 0; i < size; i++) {
            Arrays.fill(twoDimBoard[i], "_");
        }

        for(int i = 0; i < size; i++){
            int col = i;
            int row = chessBoard[i];
            twoDimBoard[col][row] = "Q";
        }

        System.out.println(Arrays.deepToString(twoDimBoard).replace("], ", "]\n"));
    }
}
