package org.transport.london.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @Author Hussain Pithawala
 */
@Data
@AllArgsConstructor
public class Station {
    private String name;
    private Set<Zone> zones;
}
