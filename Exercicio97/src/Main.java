public class Main {
    private static final int numThreads = 10; //nº de threads que vao tentar entrar numa sala
    private static final int numRooms = 2; // nº de salas em Rooms

    public static void main(String[] args) throws InterruptedException{
        ExitHandler handler=new ExitHandler();
        Rooms rooms = new Rooms(numRooms, handler);
        RoomThread[] threads=new RoomThread[numThreads];

        for (int i = 0; i < numThreads; i++){
            threads[i]=new RoomThread(numRooms, rooms, handler);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++){
            threads[i].join();
        }
    }
}