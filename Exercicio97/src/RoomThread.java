public class RoomThread extends Thread {
    int myRoom;
    boolean aux;
    private Rooms.Room room;
    private Rooms rs;
    ExitHandler handler;

    public RoomThread(int n, Rooms rooms, ExitHandler h){
        this.myRoom=(int) (Math.random()*(n));
        this.room=rooms.mrooms(myRoom);
        this.rs=rooms;
        this.handler=h;
    }

    @Override
    public void run(){
        System.out.println(this.getName() +" quer entrar na sala "+ myRoom + ".");

        try {
            rs.enter(myRoom);
            Thread.sleep(500);
            aux=rs.exit();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
