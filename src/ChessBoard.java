import java.util.*;

public class ChessBoard{
    private int[] chessBoard;
    private int size;
    private List<Integer> costList;
    private int heuristicCost;
    private StateNode lowestCostSuccessor;
    private double survivalRate;
    public ChessBoard(int size){
        this.size = size;
        this.chessBoard = randomizeBoard();
        //chessBoard = new int[]{4,5,6,3,4,5,6,5};
        this.costList = new ArrayList<>();
        this.heuristicCost = 0;
    }

    public int[] randomizeBoard(){
        Random rand = new Random();
        List<Integer> randomNumbers = new ArrayList<>();
        while(randomNumbers.size() < size){
            int random = rand.nextInt(size);
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

    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setChessBoard(int[] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public StateNode getLowestSuccessor(){
        List<StateNode> successors = generateSuccessors();
        int minCost = Collections.min(costList);
        for(StateNode x: successors){
            if(x.getStateBoard().calculateHeuristic() == minCost){
                //System.out.println("Lowest Heuristic Cost: " + minCost);
                return x;
            }
        }
        return null;
    }

    public boolean isGoal(){
        if(calculateHeuristic() == 0){
            return true;
        }
        return false;
    }

    public ChessBoard changeQueen(int colPosition, int rowPosition){
        int[] copyArray = Arrays.copyOf(this.chessBoard, size);
        // change the row position of the column
        copyArray[colPosition] = rowPosition;
        ChessBoard succ = new ChessBoard(size);
        succ.setChessBoard(copyArray);

        return succ;
    }

    public List<StateNode> generateSuccessors(){
        // grab the col position
        // scan down the column aisle, ignoring the original poisiton
        //List<ChessBoard> successors = new ArrayList<>();
        List<StateNode> successors = new ArrayList<>();

        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(chessBoard[i] == j){
                    continue;
                }else{
                    // generate successor with that new moved coordinate
                    // add heuristic for cost to be sorted by lowest (later)
                    costList.add(changeQueen(i, j).calculateHeuristic());
                    StateNode node = new StateNode(changeQueen(i,j));
                    successors.add(node);
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
//        this.heuristicCost = heuristicCost;
        return heuristicCost;
    }

    public int calculateNonAttackingPairs(){
        // total number of non attacking pairs
        int nonAttackingPairs = 0;
        for(int i = 0; i < size; i++){
            for(int j = i+1; j < size; j++){
                nonAttackingPairs++;
            }
        }

        return nonAttackingPairs;
    }

    public int calculateFitness(){
        // clashes = number of clashing queens
        // fitness = 120 - clashes
        return 210 - calculateHeuristic();
    }

    public int calculateFitnessProbability(int sumTotal){
        System.out.println("Fitness: " + calculateFitness());
        System.out.println("Sum Total: " + sumTotal);
        int total = (calculateFitness()/sumTotal);
        System.out.println(total);
        return total;
    }

    public void setSurvivalRate(double survivalRate) {
        this.survivalRate = survivalRate;
    }

    public double getSurvivalRate() {
        return survivalRate;
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
