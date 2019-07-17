public class ClientThread extends Thread {
    SavingAccount[] accounts;
    int mineID;

    ClientThread(SavingAccount[] accounts,int account){
        this.accounts=accounts;
        this.mineID = account;
    }
    @Override
    public void run() {
        SavingAccount anotherAccount, mine;
        mine = this.accounts[mineID];
        int variableId = ((int)(Math.random()*accounts.length-1));
        while(variableId==this.mineID){
            variableId = ((int)(Math.random()*accounts.length-1));
        }
        anotherAccount=accounts[variableId];
        System.out.println(mineID+" < "+variableId);
        mine.transfer(100,anotherAccount);
    }
}
