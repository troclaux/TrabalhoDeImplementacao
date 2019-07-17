import java.util.Scanner;

public class Main {
    private static int NUMACCOUNTS=3;
    public static void main(String[] args) throws InterruptedException {
        SavingAccount[] accounts = new SavingAccount[NUMACCOUNTS];
        ClientThread[] clients = new ClientThread[NUMACCOUNTS];

        for(int i=0; i< accounts.length;i++){
            accounts[i] =   new SavingAccount(Math.random()*100);
            clients[i]  =   new ClientThread(accounts,i);
        }


        for(int i=0; i< accounts.length;i++){
            clients[i].start();
        }
        System.out.println("Iniciou");
        Thread.sleep(2000);
        System.out.println("passou 2s");
        for(int i=0; i< accounts.length;i++){
            accounts[i].deposit(1000);
        }
        System.out.println("Adicionou 1000 a cada");
        for(int i=0; i< accounts.length;i++){
            clients[i].join();
        }
        System.out.println("todas retornaram");
    }
}


/*
* Task 3 - Answer to the question - Não! Sejam duas threads (x e y), duas contas (cx e cy) pertencentes a essas threads respectivamente,
* e seja um valor k de transferencia. Segue a sequencia de operações:
*
* x entra em cx.transfere e pega o lock de cx.
* y entra em cy, transfer e pega o lock de cy.
* A primeira açao de x é tentar retirar k de cy, então x entra em cy.withdraw e tenta pegar o lock de cy.
* Mas y já tem o lock de cy, então x espera y desbloquear cy.
* O mesmo acontece quando y entra em cx.withdraw, ocorrendo assim um deadlock.
*
* E pior, quando a thread Boss tenta acessar os métodos cb.deposit ou ca.deposit, ela também fica travada nesse deadlock.
*
* A única forma de fazer com que tais operações funcionem é remover as chamadas aos locks de transfer(), assim o processo todo ocorreria sem problemas.
*
* */