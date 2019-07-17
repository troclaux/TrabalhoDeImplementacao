public class Main {
    private static final int NUMTHREADS = 10; // Numero total de threads
    private static final int VEZES = 2; // Quantas vezes cada thread é executada

    public static void main(String[] args) throws InterruptedException{
        SimpleReadWriteLock lock = new SimpleReadWriteLock();
        int numReaders = 1 + (int)(Math.random() * (NUMTHREADS-1));
        int numWrites = NUMTHREADS-numReaders;
        Reader[] readers = new Reader[numReaders];
        Writer[] writers = new Writer[numWrites];

        System.out.println(numReaders+" Leitores e "+ numWrites +" Escritores");

        for(int i=0; i<readers.length; i++){
            readers[i] = new Reader(VEZES, lock);
            readers[i].start();
        }
        
        for(int i=0; i<writers.length; i++){
            writers[i] = new Writer(VEZES, lock);
            writers[i].start();
        }

        for(int i=0; i<readers.length; i++){
            readers[i].join();
        }

        for(int i=0; i<writers.length; i++){
            writers[i].join();
        }
    }
}
