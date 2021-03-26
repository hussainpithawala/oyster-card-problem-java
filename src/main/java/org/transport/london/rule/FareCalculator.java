package org.transport.london.rule;

import lombok.Data;
import org.transport.london.model.Journey;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * @Author Hussain Pithawala
 */
@Data
public class FareCalculator {
	private RuleContext ruleContext;

	// Rule Comparator to pick up min fare-amount rule amongst any two rule(s)
	private Comparator<Rule> ruleComparator = (Rule rule1, Rule rule2) -> {
		if (rule1.getAmount() < rule2.getAmount()) {
			return -1;
		} else if (rule1.getAmount() > rule2.getAmount()) {
			return 1;
		} else {
			return 0;
		}
	};

	public Double calculate(Journey journey) {

		Predicate<Rule> rulePredicate = (rule) -> rule.check(journey);

		// Fetch all the rules and pick up all applicable one's. Then pick up the one with lowest rate
		Rule applicable = ruleContext.getRules().stream().filter(rulePredicate).min(ruleComparator).get();

		return applicable.getAmount();
	}
}
