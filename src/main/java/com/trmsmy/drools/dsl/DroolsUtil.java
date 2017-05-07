package com.trmsmy.drools.dsl;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsUtil {
	public static KieSession getKieSession(String sessionName) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession(sessionName);
		return kSession;
	}
}
