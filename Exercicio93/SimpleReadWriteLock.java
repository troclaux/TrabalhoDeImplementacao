/*
* O objetivo era substituir o objeto da classe condition e seus métodos bem com o objeto da classe Lock e seus metodos por wait, notify, notifyAll e synchronized 
* Encontramos dois erros no código do autor do livro.
*/

public class SimpleReadWriteLock {
    private final SimpleReadWriteLock outer; // Gambiarra para fazer com que as Inner Classes sejam sinronizadas entre si.
    int readers;
    boolean writer;
    ReadLock readLock; // Objeto da Inner class ReadLock
    WriteLock writeLock; // Objeto da Inner class WriteLock

    public SimpleReadWriteLock() {
        outer = this;
        writer = false;
        readers = 0;
        readLock = new ReadLock();
        writeLock = new WriteLock();
    }

    public ReadLock readLock() {
        return  readLock;
    }
    
    public WriteLock writeLock() {
        return  writeLock;
    }

    class ReadLock { // Inner class
        public void lock() throws InterruptedException {
            synchronized (outer){ // Synchronized substitui lock e unlock
                while(writer) {
                    outer.wait(); // Substitui condition.await()
                }

                readers++;
            }
        }
        
        public void  unlock() {
            synchronized (outer){ // Synchronized substitui lock e unlock
                readers--;

                if(readers == 0)
                    outer.notifyAll(); // Substitui condition.signalAll()
            }
 
        }
    }

    protected class WriteLock{ // Inner class
        public void lock() throws  InterruptedException{
            synchronized (outer){
                while(readers > 0 || writer) {
                    outer.wait(); // Substitui condition.await()
                }

                writer = true;
            }
           
        }
         
        public void unlock() {
            synchronized (outer){ // Synchronized substitui lock e unlock
                writer = false;
                outer.notifyAll(); // Substitui condition.signalAll()
            }
        }

    }
}