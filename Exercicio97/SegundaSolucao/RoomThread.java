public class RoomThread extends Thread {
	@Override
	public void run(){ //run da thread
		long id = this.getId();
		int randRoom = (int) (Math.random()*(numThreads+1)); //usa um nº aleatorio para escolher em qual sala a thread quer entrar
		System.out.prinln("Thread "+id+"quer entrar na sala "+randRoom+".\n");
		rooms.enter(randRoom); //entra na sala escolhida aleatoriamente
		System.out.pintln("Thread "+id+" entrou na sala "+randRoom+". Total dentro: "+mrooms[randRoom].residents); //confirma que entrou
		rooms.exit();
	}	
}
