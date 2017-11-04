public class RunTest {
    public static void main(String[] args) {
        double averageTime = 0;
        for(int i = 0; i < 100; i++){
            long startTime = System.currentTimeMillis();
            NQueensIO.main(args);
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime);
//            double seconds = duration / 1000.0;
            averageTime += duration;
            System.out.println("Time Cost: " + duration + " Milliseconds.");
            System.out.println();
        }

        averageTime = averageTime/100;
        System.out.println("Average Time for 100 boards " + averageTime);
    }
}
