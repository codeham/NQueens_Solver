public class StateNode {
    private ChessBoard stateBoard;

    StateNode(ChessBoard stateBoard){
        this.stateBoard = stateBoard;
    }

    public ChessBoard getStateBoard(){
        return stateBoard;
    }
}
