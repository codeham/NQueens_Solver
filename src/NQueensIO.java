import java.util.Scanner;


public class NQueensIO {
    public static void main(String[] args){
        NQueensSolver thread = new NQueensSolver();
        //thread.testBoard();
        //thread.solveBoard();
        thread.solveGeneticAlgorithm();
    }

    public static int prompt(){
        Scanner k = new Scanner(System.in);
        System.out.println("**N Queens Solver**");
        System.out.println("Choose algorithm to solve the chess board");
        System.out.println("1. Ascent Hill climb algorithm");
        System.out.println("2. Genetic algorithm");
        int choice = k.nextInt();
        return choice;
    }

    public static void testClimbingAlgo(){

    }

    public static void testGeneticAlgo(){

    }
}
