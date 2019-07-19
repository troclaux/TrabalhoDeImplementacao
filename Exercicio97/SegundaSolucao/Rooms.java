public class Rooms {
	public int residents;
	public boolean occupied;
	
	public interface Handler {
		void onEmpty();
	}
	public Rooms(int m) {
		Rooms[] mrooms = new Rooms[m]
		for (int i = 0; i < m; i++) {
			mrooms[i] = new Rooms;
			mroos[i].residents = 0;
	};
	void synchronized enter(int i) {
		//sugestao de mudança (sem tempo pra fazer agora):
		//dentro do for if mrooms[j].residents > 0 seta uma variavel int que diz qual a sala que ta ocupada
		//aí na hora de checar quem ta ocupado checa so a sala de posição mrooms[var]
		for (int j = 0; j < mrooms.length; j++){
			while (mrooms[j].residents == 0 || occupied = true){
				try{
					this.wait();
				}
				catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
		}
		mrooms[i].residents++;
		System.out.pintln("Entrou uma thread na sala "+i+". Total dentro: "+mrooms[i].residents);
	}
	
	boolean synchronized exit() {
		for (int i = 0; i < m; i++) {
			if (mrooms[i].residents > 0){
				try{
					mrooms[i].residents--;
					//se a sala ficar vazia tem que chamar o handler e notificar todo mundo
					//ao chamar o handler occupied = false e notifica todo mundo
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	public void synchronized setExitHandler(int i, Rooms.Handler h) {
	
	}
	
}
