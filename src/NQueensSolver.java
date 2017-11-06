import java.util.ArrayList;
import java.util.Arrays;
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
         //empty set
//            ga.generatePopulation();
//            ga.sortByFitness();
//
//            ChessBoard x = ga.randomSelection();
//            ChessBoard y = ga.randomSelection();
//
//
//        System.out.println("X: " + x.calculateFitness());
//        System.out.println("Y: "+ y.calculateFitness());


        int generationCount = 100;
        List<ChessBoard> newPopulation = new ArrayList<>();
        ga.generatePopulation();
        ga.sortByFitness();


        for(int i = 0; i < generationCount; i++){
            ChessBoard x = ga.randomSelection();
            ChessBoard y = ga.randomSelection();

            if(Arrays.equals(x.getChessBoard(), y.getChessBoard())){
                // avoids asexual
                x = ga.randomSelection();
            }
            ChessBoard child = ga.reproduce(x, y);
            System.out.println("Child(Fitness) -> " + child.calculateFitness());


            ga.mutate(child);

            newPopulation.add(child);
            if(child.calculateHeuristic() == 210){
                //goal heuristic
                System.out.println("Board Goal Has Been Found !!!");
                return;
            }
        }
        ga.setPopulationGroup(newPopulation);
        ga.sortByFitness();

        System.out.println("Best Found Population Fitness: " + ga.getPopulationGroup().get(0).calculateFitness());
//


    }

    public void solveBoard(){
        StateNode neighbor;
        while (!currentState.getStateBoard().isGoal()){
            // while current state is not equal to 0
            neighbor = currentState.getStateBoard().getLowestSuccessor();
            int neighborValue = neighbor.getStateBoard().calculateHeuristic();
            int currentValue = currentState.getStateBoard().calculateHeuristic();

            if(neighborValue == currentValue){
                // you have hit a plateaux
                System.out.println("You are stuck at a plateaux");
                System.out.println("Final heuristic : " + currentValue);
                return;
            }

            if(neighborValue < currentValue){
                // neighbor is more efficient than currentstate
                currentState = neighbor;
            }

        }
        // exits loop if goal is found and doesn't get stuck at plateau
        System.out.println("Heuristic Goal of 0 Has Been Found ! ");
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
