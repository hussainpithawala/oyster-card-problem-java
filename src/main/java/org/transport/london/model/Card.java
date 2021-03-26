package org.transport.london.model;

import java.util.Observable;

/**
 * @Author Hussain Pithawala
 */
public class Card extends Observable {
    private Double balance = 0.0;

    public synchronized void credit(Double amount) {
        balance += amount;
    }

    public synchronized void debit(Double amount) {
        balance -= amount;
    }

    public Double getBalance() {
        return balance;
    }

    private Journey journeyInProgress;

    public Card() {}

    public void swipe(Station station, Mode mode) {
        if (null != journeyInProgress) {            // in case the journey is in progress
            journeyInProgress.setEnd(station);      // set the end station
            journeyInProgress.setComplete(true);    // mark the journey as complete
            this.setChanged();                      // without this statement observers aren't notified
            notifyObservers(journeyInProgress);     // notify the observer
            journeyInProgress = null;               // once a journey is complete set journeyInProgress to null
        } else {
            // prepare a journey and set the starting station and mode of transport
            Journey journey  = Journey.builder().start(station).mode(mode).isComplete(false).build();
            journeyInProgress = journey;            // set the journeyInProgress with the instance
            this.setChanged();                      // without this statement observers aren't notified
            notifyObservers(journeyInProgress);     // notify the observer
        }
    }
}
