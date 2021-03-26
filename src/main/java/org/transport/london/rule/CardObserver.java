package org.transport.london.rule;

import lombok.AllArgsConstructor;
import org.transport.london.model.Journey;
import org.transport.london.model.Card;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 * @Author Hussain Pithawala
 */
@AllArgsConstructor
public class CardObserver implements Observer {
    private FareCalculator fareCalculator;

    private synchronized void transact(Card card, Journey journey) {
        Double maxFare = fareCalculator.getRuleContext().getMaxFare();
        Double fareAmount = Optional.of(fareCalculator.calculate(journey)).orElse(maxFare);
        // Fare Amount is either the calculated fareAmount or the max Fare
        card.debit(fareAmount);
    }

    @Override
    public void update(Observable observable, Object argument) {
        Card card = (Card)observable;
        Journey journey = (Journey)argument;
        if(journey.isComplete()) {                                      // if journey is complete
            card.credit(fareCalculator.getRuleContext().getMaxFare());  // roll-back the max amount
            transact(card, journey);                                    // charge for the actual amount
        } else {
            card.debit(fareCalculator.getRuleContext().getMaxFare());   // when a journey begins charge for the max amount
        }
    }
}
