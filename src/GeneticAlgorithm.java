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
        int populationSize = 100;
        List<ChessBoard> populationGroup = new ArrayList<>();
        for(int i = 0; i < populationSize; i++){
            populationGroup.add(new ChessBoard(size));
        }

        System.out.println("General Population Heuristic: ");
        for(ChessBoard x: populationGroup){
            System.out.print(x.calculateHeuristic() + ", ");
        }
        System.out.println();
        this.populationGroup = populationGroup;
        return populationGroup;
    }

    public void sortByFitness(){
        // sorts all objects in ascending order
        Collections.sort(populationGroup, comparator);
        System.out.println("Fitness Sort : ");
        for(ChessBoard x: populationGroup){
            System.out.print(x.calculateHeuristic() + ", ");
        }
        System.out.println();
    }

    public ChessBoard randomSelection(){
        System.out.println("initial population size: " + populationGroup.size());
        System.out.println("10% of strongest chromosomes: ");
        Random rand = new Random();

        List<ChessBoard> pickedPopulation = new ArrayList<ChessBoard>(this.populationGroup.size());
        //picks top 10% of the top ranking population based on heuristic
        int lastIndex = (int) (0.1 * this.populationGroup.size());
        pickedPopulation.addAll(populationGroup.subList(0, lastIndex));

        // randomize selection pool
        Collections.shuffle(pickedPopulation);
        for(ChessBoard x: pickedPopulation){
            System.out.print(x.calculateHeuristic() + ", ");
        }
        System.out.println();
        return pickedPopulation.get(0);
    }

    public ChessBoard reproduce(ChessBoard candidateX, ChessBoard candidateY){
        ChessBoard child = new ChessBoard(size);
        // randomly split both arrays
        System.out.println("Candidate X - " + Arrays.toString(candidateX.getChessBoard()));
        System.out.println("Candidate Y - " + Arrays.toString(candidateY.getChessBoard()));

        Random rand = new Random();
        int firstSplit = 1 + rand.nextInt(size);
        int secondSplit = size - firstSplit;
        System.out.println("First Split : " + firstSplit);
        System.out.println("Second Split : " + secondSplit);

        int[] childCrossover = crossover(candidateX, firstSplit, candidateY, secondSplit);
        child = new ChessBoard(size);
        child.setChessBoard(childCrossover);

        return child;
    }

    public int[] crossover(ChessBoard candidateX, int firstSplit, ChessBoard candidateY, int secondSplit){
        int[] firstSelection = new int[firstSplit];
        int[] secondSelection = new int[secondSplit];
        //System.arraycopy(src, srcPos, dest, destPos, length)
        System.arraycopy(candidateX.getChessBoard(), 0, firstSelection, 0, firstSelection.length);
        System.arraycopy(candidateY.getChessBoard(), firstSelection.length, secondSelection, 0, secondSelection.length);

        // print out arrays
        System.out.println();
        System.out.println("**After Split**");
        System.out.println("Candidate X - " + Arrays.toString(firstSelection));
        System.out.println("Candidate Y - " + Arrays.toString(secondSelection));

        int[] child = new int[firstSplit + secondSplit];
        System.arraycopy(firstSelection, 0, child, 0, firstSelection.length);
        System.arraycopy(secondSelection, 0, child, firstSelection.length, secondSelection.length);
        System.out.println();
        System.out.println("Child Array: " + Arrays.toString(child));
        return child;
    }

    public ChessBoard mutate(ChessBoard child){
        Random rand = new Random();
        int randomChromosome = rand.nextInt(child.getSize());
        int randomCol = rand.nextInt(size);
        child.getChessBoard()[randomChromosome] = randomCol;
        System.out.println();
        System.out.println("After Mutation @ Index " + randomChromosome);
        System.out.println(Arrays.toString(child.getChessBoard()));
        return  child;
    }
}

