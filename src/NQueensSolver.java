import java.util.List;

public class NQueensSolver {
private ChessBoard board;

private StateNode initialState;

private StateNode currentState;

    NQueensSolver(){
        this.board = new ChessBoard(21);
        this.initialState = new StateNode(board);
        this.currentState = initialState;
    }

    public void solveGeneticAlgorithm(){
        GeneticAlgorithm ga = new GeneticAlgorithm(21);
        ga.generatePopulation();
        ga.sortByFitness();
        ga.randomSelection();
    }

    public void solveBoard(){
        StateNode neighbor;
//        System.out.println("******* Initial Board *******");
//        currentState.getStateBoard().printBoard();
//        System.out.println("Heuristic Cost: " + currentState.getStateBoard().calculateHeuristic());
//        System.out.println("*************************");

        while (!currentState.getStateBoard().isGoal()){
            // while current state is not equal to 0
            neighbor = currentState.getStateBoard().getLowestSuccessor();
            int neighborValue = neighbor.getStateBoard().calculateHeuristic();
            int currentValue = currentState.getStateBoard().calculateHeuristic();

            //testing print
//            currentState.getStateBoard().printBoard();
//            System.out.println("Heuristic Cost: " + currentValue);
//            System.out.println("*************************");

            if(neighborValue == currentValue){
                // you have hit a plateaux
                System.out.println("You are stuck at a plateaux");
                System.out.println("Final heuristic : " + currentValue);
//                System.out.println();
                return;
            }

            if(neighborValue < currentValue){
                // neighbor is more efficient than currentstate
                currentState = neighbor;
            }

        }
        // exits loop if goal is found and doesn't get stuck at plateau
        System.out.println("Heuristic Goal of 0 Has Been Found ! ");
//        currentState.getStateBoard().printBoard();

    }

    public void testBoard(){
        currentState.getStateBoard().printBoard();
        System.out.println();
        System.out.println("Heuristic Cost: " + currentState.getStateBoard().calculateHeuristic());
//        for(int x: currentState.getStateBoard().getChessBoard()){ System.out.print(x + ", ");}
        System.out.println();
        List<StateNode> test = currentState.getStateBoard().generateSuccessors();
        System.out.println("**************************************");
        for (StateNode x: test){
            x.getStateBoard().printBoard();
            System.out.println("Heuristic Cost : " + x.getStateBoard().calculateHeuristic());
            System.out.println("**************************************");
            System.out.println();
        }

        System.out.println("Lowest Heuristic Cost: " + currentState.getStateBoard().getLowestSuccessor());

    }
}
