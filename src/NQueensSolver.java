import java.util.List;

public class NQueensSolver {
private ChessBoard board;

private StateNode initialState;

private StateNode currentState;

    NQueensSolver(){
        this.board = new ChessBoard(4);
        this.initialState = new StateNode(board);
        this.currentState = initialState;
    }

    public void solveBoard(){
        currentState.getStateBoard().printBoard();
        System.out.println();
        System.out.println("Heuristic Cost: " + currentState.getStateBoard().calculateHeuristic());
//        for(int x: currentState.getStateBoard().getChessBoard()){ System.out.print(x + ", ");}
        System.out.println();
        List<ChessBoard> test = currentState.getStateBoard().generateSuccessors();
        System.out.println("**************************************");
        for (ChessBoard x: test){
            x.printBoard();
            System.out.println("**************************************");
            System.out.println();
        }

    }
}
