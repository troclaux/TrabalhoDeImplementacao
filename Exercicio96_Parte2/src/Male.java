public class Male extends Thread {
    private BathroomSync bathroom;
    Male(BathroomSync bathroom){
        this.bathroom = bathroom;
    }
    @Override
    public void run() {
        long id = this.getId();
        System.out.println("***************M: Thread "+id+" ta apertado*********************");
        bathroom.enterMale();
        System.out.println("M: Thread "+id+" ta cagando\n");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bathroom.leaveMale();
        System.out.println("M: Thread "+id+" deixou o banheiro todo sujo\n");
    }
}