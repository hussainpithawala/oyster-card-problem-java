package org.transport.london.rule;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Hussain Pithawala
 */
@Data
public class RuleContext {
    private Double maxFare;

    private Set<Rule> rules = new HashSet<>();

    public void loadRule(Rule rule){
        rules.add(rule);
    }

}
