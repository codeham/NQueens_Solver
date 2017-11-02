import java.util.HashMap;
import java.util.Map;

public class StateNode {
    private ChessBoard stateBoard;

    StateNode(ChessBoard stateBoard){
        this.stateBoard = stateBoard;
    }

    public ChessBoard getStateBoard(){
        return stateBoard;
    }

    public void generateSuccessors(){
        // to generate successors,
        // create copy of the board and move Q within the col it lies in
        // make a stateNode instance of that queen and calcul

    }

//    public int calculateHeuristic(){
//        //calculating the heuristic
//        int[] currentBoard = this.stateBoard.getChessBoard();
//        int boardSize = this.stateBoard.getSize();
//        int heuristicCost = 0;
//
//        for(int i = 0; i < boardSize; i++){
//            for (int j = i + 1; j < boardSize; j++){
//                if(currentBoard[i] == currentBoard[j]) {
//                    heuristicCost++;
//                }
//                int offset = j-i;
//                if(currentBoard[i] == currentBoard[j]-offset || currentBoard[i] == currentBoard[j]+offset){
//                    heuristicCost++;
//                }
//            }
//        }
//        return heuristicCost;
//    }



}
