public class Rooms {
	public int occupied;
	Room[] mrooms; // Array of Inner class type
	ExitHandler handler;
	
	public interface Handler {
		void onEmpty(int r);
	}

	public Rooms(int m, ExitHandler h) {
		this.occupied=m+1;
		mrooms = new Room[m];
		this.handler=h;
		
		for (int i = 0; i < m; i++) {
			mrooms[i] = new Room(5);
		}
	}

	public Room mrooms(int i){
		return mrooms[i];
	}
	
	public synchronized void  setExitHandler(int i, ExitHandler h) {
			mrooms[i].exitHandler = h;
	}

	synchronized void enter(int i) throws InterruptedException{
			while (i != occupied) { // Checks if the room that is being used is the one it wants to enter
				if (occupied == mrooms.length + 1) { // No rooms were used yet
					occupied = i;
					break;
				}

				this.wait();
			}

			while (mrooms[i].getVisitors() == mrooms[i].getCapacity()) {
				this.wait();
			}

			mrooms[i].incrementVisitors();
			System.out.println(Thread.currentThread().getName() + " entrou na sala " + i + " Total dentro: " + mrooms[i].getVisitors() + "/" + mrooms[i].getCapacity());
	}

	synchronized boolean exit(){
			mrooms[occupied].decrementVisitors();

			if (mrooms[occupied].getVisitors() == 0) {
				System.out.println(Thread.currentThread().getName() + " saiu da sala " + occupied + " Total dentro: " + mrooms[occupied].getVisitors() + "/" + mrooms[occupied].getCapacity());
				this.setExitHandler(occupied, handler);
				mrooms[occupied].exitHandler.onEmpty(occupied);
				occupied=mrooms.length+1;
				this.notifyAll();
				return true;
			}

			System.out.println(Thread.currentThread().getName() + " saiu da sala " + occupied + " Total dentro: " + mrooms[occupied].getVisitors() + "/" + mrooms[occupied].getCapacity());
			this.notifyAll();
			return false;
	}

	public class Room{ // Inner class
		int capacity; // Total capacity of the room
		int visitors; // Number of visitors in the room
		public ExitHandler exitHandler; // Retains the received handler

		public Room(int capacity){
			this.capacity=capacity;
			this.visitors=0;
		}

		public int getCapacity() {
			return capacity;
		}

		public int getVisitors(){
			return visitors;
		}

		public int incrementVisitors(){
			return visitors++;
		}

		public int decrementVisitors(){
			return visitors--;
		}
	}
	
} 
