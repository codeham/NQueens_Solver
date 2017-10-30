public class NQueensSolver {
private ChessBoard board;

private StateNode initialState;

private StateNode currentState;

    NQueensSolver(){
        board = new ChessBoard(8);
        initialState = new StateNode(board);
        this.currentState = initialState;
    }

    public void solveBoard(){
        currentState.getStateBoard().printBoard();
    }
}
