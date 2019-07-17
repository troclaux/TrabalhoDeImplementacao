public class ExitHandler implements Rooms.Handler {
    @Override
    public synchronized void onEmpty(int r) {
        System.out.println("Ultima thread saiu da sala " + r);
    }
}
