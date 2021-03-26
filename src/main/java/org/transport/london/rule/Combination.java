package org.transport.london.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.transport.london.model.Journey;
import org.transport.london.model.Zone;

/**
 * @Author Hussain Pithawala
 */
@Data
@AllArgsConstructor
public class Combination {
    private Zone startZone;
    private Zone endZone;

    public boolean check(Journey journey) {
        return journey.getStart().getZones().contains(startZone) && journey.getEnd().getZones().contains(endZone);
    }
}
