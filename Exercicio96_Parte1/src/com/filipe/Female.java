package com.filipe;


public class Female extends Thread {
    private BathroomLockCond bathroom;
    Female(BathroomLockCond bathroom){
        this.bathroom = bathroom;
    }
    @Override
    public void run() {
        long id = this.getId();
        System.out.println("***************F: Thread "+id+" quer entrar no Banheiro******************");
        this.bathroom.enterFemale();
        System.out.println("F: Thread "+id+" ta Penteando o cabelo\n");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.bathroom.leaveFemale();
        System.out.println("F: Thread "+id+" largou o banheiro todo cheio de cabelo\n");
    }
}