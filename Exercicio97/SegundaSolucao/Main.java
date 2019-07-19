public class Main {
	private static final int numThreads = 50; //nº de threads que vao tentar entrar numa sala
	
	Rooms rooms;
	
	public static void main(String[] args){
		Rooms rooms = new Rooms(numThreads); //cria nova Rooms, onde numTrheads sera o tamanho do vetor de salas
		
		for (int i = 0; i < numThreads; i++){ //inicia as threads
			rooms[i].start();
			rooms[i].join();
		}
	}
}
