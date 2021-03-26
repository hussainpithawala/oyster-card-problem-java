package org.transport.london.model;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * @Author Hussain Pithawala
 */

@Data
@Builder
public class Journey {
    private Station start;
    private Station end;
    private Mode mode;
    private boolean isComplete;
}
