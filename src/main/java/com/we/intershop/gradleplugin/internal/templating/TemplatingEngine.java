package com.we.intershop.gradleplugin.internal.templating;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

/**
 * Template engine. Generates ICM artifacts like component and java files.
 * 
 * @author willem evertse
 *
 */
public class TemplatingEngine {
	// Interface
	private static final JtwigTemplate TEMPL_PRODUCTBO_INTER = JtwigTemplate
			.classpathTemplate("templates/ProductBOExtTemplate.twig");
	// Component
	private static final JtwigTemplate TEMPL_Component = JtwigTemplate
			.classpathTemplate("templates/ComponentTemplate.twig");
	// Factory
	private static final JtwigTemplate TEMPL_Factory = JtwigTemplate
			.classpathTemplate("templates/BOExtFactoryTemplate.twig");
	// Implementation
	private static final JtwigTemplate TEMPL_Impl = JtwigTemplate
				.classpathTemplate("templates/BOExtImplementationTemplate.twig");

	public static String createInterface(String interfaceName, String packageName,String businessobject,String bopackage) {
		JtwigModel extModel = JtwigModel.newModel()
				.with("package", packageName)
				.with("extName", interfaceName)
				.with("bopackage", bopackage)
				.with("bo", businessobject);
		return TEMPL_PRODUCTBO_INTER.render(extModel);
	}

	public static String createComponent(String factoryName, String factoryPackage) {
		JtwigModel compModel = JtwigModel.newModel()
				.with("factoryClass", factoryName)
				.with("factoryPackages",factoryPackage);
		return TEMPL_Component.render(compModel);
	}

	public static String createFactory(String factoryName, String factoryPackage, String implName, String businessObject,String businessObjectPackage,String boExtInterface,String boExtInterfacePackage) {
		JtwigModel compModel = JtwigModel.newModel()
				.with("boextfactory", factoryName)
				.with("package", factoryPackage)
				.with("boextimpl", implName)
				.with("bo", businessObject)
				.with("boextinterface", boExtInterface)
				.with("bopackage", businessObjectPackage)
				.with("boextinterfacepackage", boExtInterfacePackage);
		return TEMPL_Factory.render(compModel);
	}

	public static String createExtImplementation(String implPackage,String businessObjectPackage,String businessObject,String boExtInterfacePackage,String boExtInterface,String implName) {
		JtwigModel compModel = JtwigModel.newModel()
				.with("package", implPackage)
				.with("bopackage", businessObjectPackage)
				.with("boextinterfacepackage", boExtInterfacePackage)
				.with("boextinterface", boExtInterface)
				.with("boextimpl", implName)
				.with("bo", businessObject);
		return TEMPL_Impl.render(compModel);
	}

}
