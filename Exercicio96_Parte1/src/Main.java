package com.filipe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Main { //Main == Banheiro
    private static final int numMales = 10; //nº de homens a usar o banheiro
    private static final int numFemales = 10; //nº de mulheres a usar o banheiro

    BathroomLockCond bathroom; //cria banheiro

    public static void main(String[] args) throws InterruptedException {
        BathroomLockCond bathroom = new BathroomLockCond();
        Male[] males = new Male[numMales]; //vetor de threads Male
        Female[] females = new Female[numFemales]; //vetor de threads Female

        for (int i = 0; i < numMales; i++){ //cria threads Male
            males[i] = new Male(bathroom);
            males[i].start();
        }
        for (int i = 0; i < numFemales; i++){ //cria threads Female
            females[i] = new Female(bathroom);
            females[i].start();
        }
        for (int i = 0; i < numMales; i++){
            males[i].join();
        }
        for (int i = 0; i < numFemales; i++){
            females[i].join();
        }
    }

}

/***********************************************************************************************
  Grupo: Arthur Albuquerque, Lawrence Silva, Cesar Silva, François Boechat

 Starvation-Freedom:
    O grande problema de acesso aos banheiros é que quando um homem quiser entrar, se já houver mulheres lá dentro, ele
 pode nunca conseguir entrar se houver sempre mais mulheres entrando (e vice-versa).
    Com a variável "turn" esse problema é impedido, pois quando um homem quiser entrar, ele muda o valor de turn impedindo
 que novas mulheres entrem (e vice-versa).

 Exclusão Mutua:
    Para garantir que não haja homens e mulheres dentro do banheiro ao mesmo tempo, o homem verifica se ha mulheres lá
 dentro(análogo para mulheres) e se houver, ele espera. Se não houver mulher nenhuma e for a vez dele, ele entra.
 */