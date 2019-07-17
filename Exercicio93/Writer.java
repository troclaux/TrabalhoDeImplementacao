public class Writer extends Thread { // Thread de Escritor
    private int vezes;
    private SimpleReadWriteLock.WriteLock lock;

    Writer (int vezes, SimpleReadWriteLock lock){
        this.lock=lock.writeLock();
        this.vezes=vezes;
    }

    public void run(){
        try {
            for (int i=0; i < this.vezes; i++) {
                lock.lock();
                System.out.println("Thread nº " +Thread.currentThread().getId()+ " Vai escrever!");
                Thread.sleep(500);
                System.out.println("Thread nº " +Thread.currentThread().getId()+ " escreveu!");
                lock.unlock();
            }
        }
        catch (InterruptedException e){
            System.out.println("ERRO na execução da Thread nº "+Thread.currentThread().getId());
        }
        finally {
            System.out.println("SAIU Writer nº " +Thread.currentThread().getId());
        }

    }
}
