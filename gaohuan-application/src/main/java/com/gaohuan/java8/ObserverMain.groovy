/**
 * java8自带observer练习
 */
class Watcher implements Observer {

    @Override
    void update(Observable o, Object arg) {
        println "update() called ,count is " + ((Integer) arg).intValue();
    }
}

class BeingWatched extends Observable {

    void counter(int period) {
        for (; period >= 0; period--) {
            setChanged();
            notifyObservers(period)
            Thread.sleep(100)
        }
    }

}


BeingWatched observed = new BeingWatched();
Watcher watcher = new Watcher();
observed.addObserver(watcher);
observed.counter(10);


