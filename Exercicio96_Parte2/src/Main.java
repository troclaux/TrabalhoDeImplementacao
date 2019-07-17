public class Main { //Main == Banheiro
    private static final int numMales = 10; //nº de homens a usar o banheiro
    private static final int numFemales = 10; //nº de mulheres a usar o banheiro

    BathroomSync bathroom; //cria banheiro

    public static void main(String[] args) throws InterruptedException {
        BathroomSync bathroom = new BathroomSync();
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
  Grupo: Filipe Ramalho; Gabriel Villares; Miguel Santos; Jansen Pires;

 Segunda Parte da questão 96 - Explicação na Parte 1
 */