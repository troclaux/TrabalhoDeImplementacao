public class Reader extends Thread{ // Thread de Leitor
    private int vezes;
    private SimpleReadWriteLock.ReadLock lock;

    Reader(int vezes, SimpleReadWriteLock lock){
        this.vezes=vezes;
        this.lock=lock.readLock();
    }

    public void run() {
        try {
            for (int i = 0; i < this.vezes; i++){
                lock.lock();
                System.out.println("Thread nº " +Thread.currentThread().getId()+ " vai ler!");
                Thread.sleep(500);
                System.out.println("Thread nº " +Thread.currentThread().getId()+ " leu!");
                lock.unlock();
            }
        }
        catch (InterruptedException e){
            System.err.println("ERRO na execução da Thread nº "+Thread.currentThread().getId());
        }
        finally {
            System.out.println("SAIU Reader nº " +Thread.currentThread().getId());
        }
    }
}
