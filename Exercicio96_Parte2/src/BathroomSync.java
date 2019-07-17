import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BathroomSync {
    int maleUsing=0, femaleUsing=0; //nº de homens OU mulheres que ESTAO USANDO simultaneamente o banheiro
    boolean turn=false;

    public synchronized void enterMale() {
        while (!this.turn || (femaleUsing > 0)) {
            turn=true;
            if(femaleUsing>0){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        maleUsing++;
        System.out.println("Homens:"+this.maleUsing+" - Mulheres"+this.femaleUsing);

    }
    public synchronized void leaveMale(){
        maleUsing--;
        if (maleUsing == 0)
            this.notifyAll();
    }
    public synchronized void enterFemale(){
        while (this.turn || (maleUsing > 0)) {
            turn=false;
            if(maleUsing>0){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        femaleUsing++;
        System.out.println("Homens:"+this.maleUsing+" - Mulheres"+this.femaleUsing);
    }
    public synchronized void leaveFemale() {
        femaleUsing--;
            if (femaleUsing == 0)
                this.notifyAll();
    }

}