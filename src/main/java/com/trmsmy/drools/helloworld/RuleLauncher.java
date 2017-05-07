package com.trmsmy.drools.helloworld;

import static com.trmsmy.drools.dsl.DroolsUtil.getKieSession;

import org.kie.api.runtime.KieSession;

public class RuleLauncher {

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KieSession kSession = getKieSession("hello-world");

			Message message = new Message();
			message.setMessage("Hello World");
			message.setStatus(Message.HELLO);
			
			kSession.insert(message);
			kSession.fireAllRules();
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
