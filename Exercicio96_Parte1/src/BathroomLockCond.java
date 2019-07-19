package com.filipe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class BathroomLockCond{
    int maleUsing=0, femaleUsing=0; //nº de homens OU mulheres que ESTAO USANDO simultaneamente o banheiro
    Lock bLock;
    Lock locker;
    Condition cond;
    boolean turn=false;

    public BathroomLockCond(){
        maleUsing = femaleUsing = 0;

        locker = new ReentrantLock();
        cond = locker.newCondition();
    }
    public void enterMale() {
        locker.lock();
        try {
            while (!this.turn || (femaleUsing > 0)) {
                turn=true;
                if(femaleUsing>0){
                    cond.await();
                }
            }
            maleUsing++;
            System.out.println("Homens:"+this.maleUsing+" - Mulheres"+this.femaleUsing);
        } catch (InterruptedException e) {
            System.err.println("Erro\n");
        }
        finally {
            locker.unlock();
        }
    }
    public void leaveMale() {
        locker.lock();
        try {
            maleUsing--;
            if (maleUsing == 0)
                cond.signalAll();
        } finally {
            locker.unlock();
        }
    }
    public void enterFemale() {
        locker.lock();
        try {
            while (this.turn || (maleUsing > 0)) {
                turn=false;
                if(maleUsing>0){
                    cond.await();
                }
            }
            femaleUsing++;
            System.out.println("Homens:"+this.maleUsing+" - Mulheres"+this.femaleUsing);
        } catch (InterruptedException e) {
            System.err.println("Erro\n");
        }
        finally {
            locker.unlock();
        }
    }
    public void leaveFemale() {
        locker.lock();
        try {
            femaleUsing--;
            if (femaleUsing == 0)
                cond.signalAll();
        } finally {
            locker.unlock();
        }
    }

}