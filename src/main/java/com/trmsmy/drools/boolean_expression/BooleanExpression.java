package com.trmsmy.drools.boolean_expression;

import static com.trmsmy.drools.dsl.DroolsUtil.getKieSession;

import java.util.Arrays;

import org.drools.core.common.InternalAgenda;
import org.kie.api.runtime.KieSession;

public class BooleanExpression {
	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KieSession kSession = getKieSession("boolean_expression");
            activateFlowGroup(kSession, "rule1");

	       // ActivateRuleFlowCommand stepOneCmd = new ActivateRuleFlowCommand("step1");

			
			BoolFact bf = new BoolFact();
			bf.setA("a1");
			bf.setB("b1");
			bf.setC("c1");
			bf.setL(Arrays.asList("l1", "l2"));
			
			kSession.insert(bf);
			kSession.fireAllRules();
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static void activateFlowGroup(KieSession kSession, String groupName) {
		((InternalAgenda) kSession.getAgenda()).activateRuleFlowGroup(groupName);
	}
}
