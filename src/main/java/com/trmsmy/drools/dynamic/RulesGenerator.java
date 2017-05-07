package com.trmsmy.drools.dynamic;

import java.io.IOException;
import java.io.InputStream;

import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.api.PackageDescrBuilder;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.google.common.io.Resources;

public class RulesGenerator {

	public static void main(String[] args) {
		KieContainer container = new RulesGenerator().build(KieServices.Factory.get());
		KieSession session = container.newKieSession();
		session.insert(new Person(17));
		session.insert(new Action());
		session.fireAllRules();
	}

	public KieContainer build(KieServices kieServices) {
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		ReleaseId rid = kieServices.newReleaseId("com.trmsmy.drools", "model-test", "1.0-SNAPSHOT");
		kieFileSystem.generateAndWritePomXML(rid);

		kieFileSystem.write("src/main/resources/rules.drl", getResource(kieServices, "rules.drl"));

		addRule(kieFileSystem);

		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();
		if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
		}
		
		return kieServices.newKieContainer(rid);
	}

	private void addRule(KieFileSystem kieFileSystem) {
		PackageDescrBuilder packageDescrBuilder = DescrFactory.newPackage().name("com.trmsmy.drools.dynamic");
		packageDescrBuilder.newImport().target("com.trmsmy.drools.dynamic.*").end();
		packageDescrBuilder.newRule().name("Is of valid age")
			.lhs()
				.pattern("Person")
					.constraint("age < 18").end()
				.pattern()
					.id("$a", false).type("Action").end()
			.end()
			.rhs("$a.showBanner( false ); System.out.println(\" Valid Age \");")
			.end();

		String ruleStr = new DrlDumper().dump(packageDescrBuilder.getDescr());
		
		printGeneratedRule(ruleStr);
		
		kieFileSystem.write("src/main/resources/rule-1.drl", ruleStr);
	}

	private void printGeneratedRule(String rules) {
		System.out.println("===================================== genereated rules start =================================");
		System.out.println(rules);
		System.out.println("===================================== genereated rules end =================================");
	}

	private org.kie.api.io.Resource getResource(KieServices kieServices, String resourcePath) {
		try {
			InputStream is = Resources.getResource(resourcePath).openStream(); // guava
			return kieServices.getResources().newInputStreamResource(is).setResourceType(ResourceType.DRL);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load drools resource file.", e);
		}
	}

}
