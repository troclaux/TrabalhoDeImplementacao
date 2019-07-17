import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Task 1 - implement the class using lock and conditions
public class SavingAccount  {
    private Lock locker;
    private Condition cond;
    private double balance;
    private int preferred=0;
    SavingAccount(double balance){
        locker = new ReentrantLock();
        cond = locker.newCondition();
        this.balance=balance;
    }
    public void deposit(double k){

        locker.lock();
        try {
            this.balance += (int)(k*100)/100;
        }
        finally {
            System.out.println("Deu sinal");
            cond.signalAll();
            locker.unlock();
        }
    }

    public void withdraw(double k){
        k=Math.floor((k*100))/100;
        locker.lock();
        try {
            while(this.balance<k){//wait until it becomes equal or greater than k;
                System.out.println(Thread.currentThread().getName() + " está esperando");
                 cond.await();
            }
            this.balance -= k;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    //Task 2 - Creating methods ordinary and preferred withdraw;
    public void ordinaryWithdraw(double k){
        locker.lock();
        try {
            //wait until it becomes equal or greater than k or there is any preferred waiting to execute.
            while(this.balance<k || this.preferred>0){

                cond.await();
            }
            this.balance -= k;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void preferredWithdraw(double k){
        locker.lock();
        try {
            this.preferred++;
            while(this.balance<k){//wait until it becomes equal or greater than k;
                cond.await();
            }
            this.balance -= k;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.preferred--;
            locker.unlock();
        }
    }
    //End Task2;

    //Task 3 - The transfer method

    public void transfer(int k,SavingAccount reserve) {//Esse método é bugado por causa do lock dentro dele. se retirar este lock, o programa funciona.
            locker.lock();
            try{
                reserve.withdraw(k);
                deposit(k);
            }finally
            {locker.unlock();}
    }
}
//End task 1;