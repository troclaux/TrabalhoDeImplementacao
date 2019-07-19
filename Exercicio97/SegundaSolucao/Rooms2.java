public class Rooms {
	public int residents, currRoom;
	public boolean occupied;
	
	public interface Handler {
		void onEmpty();
	}
	
	public Rooms(int m) {
		Rooms[] mrooms = new Rooms[m] //cria vetor de salas
		for (int i = 0; i < m; i++) {
			mrooms[i] = new Rooms; //cada elemento e uma nova sala
			mroos[i].residents = 0; //cada sala inicia-se vazia
		}		
	}
	
	void synchronized enter(int i) {
		for (int j = 0; j < mrooms.length; j++){
			if (mrooms[j].residents > 0){ //checa se a sala tem alguem dentro
				currRoom = j; //pega a posicao da sala atualmente nao vazia no vetor
				break; //se peguei qual sala esta ocupada, nao preciso olhar salas subsequentes
			}
		}
		while (occupied = true || i != currRoom){//tem sala ocupada e quero entrar numa outra -> boto a thread pra esperar
			try{
				this.wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
            }
		}
		rooms[i].residents++; //conseguiu entrar, portanto o nº de "residentes" da sala aumenta
		currRoom = i; //pego a sala atual, pra poder comparar
		System.out.pintln("Entrou uma thread na sala "+i+". Total dentro: "+mrooms[i].residents);
	}
	
	boolean synchronized exit() {		
		if (mrooms[currRoom].residents > 0){ //olha se a sala atual esta vazia ou nao
			try{
				mrooms[currRoom].residents--; //saiu da sala
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (mrooms[currRoom].residents == 0){
			setExitHandler(currRoom, hand); //se a sala estiver vazia, chama o handler
		}
	}
	
	public void synchronized setExitHandler(int i, Rooms.Handler h) {
		int roomsOccupied = 0; //inicia com nenhuma sala ocupada, visto que a sala atual precisa ser esvaziada para chamar o handler
		for (int j = 0; j < mrooms.length; j++) {
			if (mrooms[j].residents == 0) { //checa se as salas estao vazias
				System.out.prinln("Sala "+j+"vazia.\n");
			}
			else {
				roomsOccupied++; //se tem uma sala ocupada, fico sabendo
				System.out.prinln("Sala "+j+"nao esta vazia.\n");
		}
		if (roomsOccupied == 0){ //se nenhuma sala estiver ocupada, chamo onEmpty
			onEmpty();
		}
	}
	
	void onEmpty(){
		occupied = false; //seta occupied como false, indicando que nenhuma sala esta ocupada, e permitindo que outras salas possam entrar
		this.notifyAll(); //notifica as threads dormintes
	}
}
