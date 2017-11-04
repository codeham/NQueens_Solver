import java.util.*;

public class GeneticAlgorithm{
    private int size;
    private List<ChessBoard> populationGroup;
    private List<ChessBoard> pickedPopulation;
    Comparator<ChessBoard> comparator = new Comparator<ChessBoard>() {
        @Override
        public int compare(ChessBoard o1, ChessBoard o2) {
            return o1.calculateHeuristic() - o2.calculateHeuristic();
        }
    };

    GeneticAlgorithm(int size){
        this.populationGroup = new ArrayList<>();
        this.pickedPopulation = new ArrayList<>();
        this.size = size;
    }


    public List<ChessBoard> generatePopulation(){
        // creates a brand new population of randomized board instances
        int populationSize = size*2;
        List<ChessBoard> populationGroup = new ArrayList<>();
        for(int i = 0; i < populationSize; i++){
            populationGroup.add(new ChessBoard(21));
        }

//        for(ChessBoard x: populationGroup){
//            System.out.println(x.calculateHeuristic());
//        }
        this.populationGroup = populationGroup;
        return populationGroup;
    }

    public void sortByFitness(){
        // sorts all objects in ascending order
        Collections.sort(populationGroup, comparator);
        System.out.println("Fitness Sort : ");
        for(ChessBoard x: populationGroup){
            System.out.println("Heuristic " + x.calculateHeuristic());
        }
    }

    public void randomSelection(){
        System.out.println(populationGroup.size());
        Random rand = new Random();

        List<ChessBoard> pickedPopulation = new ArrayList<ChessBoard>(this.populationGroup.size());
        //picks top 10% of the top ranking population based on heuristic
        int lastIndex = (int) (0.1 * this.populationGroup.size());
        pickedPopulation.addAll(populationGroup.subList(0, lastIndex));

        // randomize selection pool
        Collections.shuffle(pickedPopulation);
        for(ChessBoard x: pickedPopulation){
            System.out.println(x.calculateHeuristic());
        }
    }
}
