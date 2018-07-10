package com.we.intershop.gradleplugin.internal.templating;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.we.intershop.gradleplugin.internal.BOExtensionConstants.ExtTypes;
import com.we.intershop.gradleplugin.internal.BOExtensionFactory;
import com.we.intershop.gradleplugin.internal.IntershopArtifacts;
import com.we.intershop.gradleplugin.internal.templating.TemplatingEngine;

public class TestTemplatingEngine {

	/**
	 * Test that ProductBO extension interface is rendered
	 */
	@Test
	public void testProductBOInterface() {
		String javaInterface = TemplatingEngine.createInterface("TestProductBOEx", "com.ctc.test","ProductBO","com.intershop.component.product.capi");

		// interface
		assertThat(javaInterface,
				containsString("public interface TestProductBOEx extends BusinessObjectExtension<ProductBO>"));
		// package
		assertThat(javaInterface, containsString("package com.ctc.test"));
		
		// bo package
		assertThat(javaInterface, containsString("import com.intershop.component.product.capi.ProductBO;"));
		
		//EXTENSION_ID
		assertThat(javaInterface, containsString("public static final String EXTENSION_ID = \"TestProductBOEx\";"));
		
	}

	@Test
	public void testComponetFile() {
		String comp = TemplatingEngine.createComponent("TestFactory", "com.ctc.test2");

		// xml file
		assertThat(comp, containsString("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"));
		// ish namespace
		assertThat(comp, containsString("<components xmlns=\"http://www.intershop.de/component/2010\">"));

		// component name, class & instance
		assertThat(comp, containsString("name=\"com.ctc.test2.TestFactory\""));
		assertThat(comp, containsString("class=\"com.ctc.test2.TestFactory\""));
		assertThat(comp, containsString("<instance with=\"com.ctc.test2.TestFactory\"/>"));
	}

	@Test
	public void testFactoryFile() {
		String fac = TemplatingEngine.createFactory("TestProductFactory", "com.test.internal", "TestProductImpl",
				"ProductBO", "com.intershop.component.product.capi", "TestProduct","com.test.capi");

		assertThat(fac, containsString(
				"public class TestProductFactory extends AbstractBusinessObjectExtensionFactory<ProductBO>"));
		
		assertThat(fac, containsString(
				"return new TestProductImpl(TestProduct.EXTENSION_ID, object);"));
		
		assertThat(fac, containsString(
				"import com.test.capi.TestProduct;"));
		
		assertThat(fac, containsString(
				"public Class<ProductBO> getExtendedType()"));
		
		assertThat(fac, containsString("import com.intershop.component.product.capi.ProductBO;"));
	}
	
	@Test
	public void testImplementationFile() {
		String impl = TemplatingEngine.createExtImplementation("com.test.internal","com.intershop.component.product.capi","ProductBO","com.ctc.test","TestProductBOEx","TestProductBOExImpl");
		
		assertThat(impl, containsString("import com.intershop.component.product.capi.ProductBO;"));
		
		assertThat(impl, containsString("public class TestProductBOExImpl extends AbstractBusinessObjectExtension<ProductBO> implements TestProductBOEx"));
		
		assertThat(impl, containsString("package com.test.internal;"));
	}
	
	@Test
	public void testBasketBOExt(){
		IntershopArtifacts a = BOExtensionFactory.createSources(ExtTypes.BASKETBO, "BlaBasketExt", "com.intershop.capi", "com.intershop.internal");

		assertThat(a.getInterfaceData(), containsString("import com.intershop.component.basket.capi.BasketBO;"));
		
		assertThat(a.getImplementationData(), containsString("import com.intershop.component.basket.capi.BasketBO;"));
		
		assertThat(a.getImplementationData(), containsString("public class BlaBasketExtImpl extends AbstractBusinessObjectExtension<BasketBO> implements BlaBasketExt"));
		
		assertThat(a.getFactoryData(), containsString("public class BlaBasketExtFactory extends AbstractBusinessObjectExtensionFactory<BasketBO>"));
		
		assertThat(a.getComponentData(), containsString("<instance with=\"com.intershop.internal.BlaBasketExtFactory\"/>"));
	}
	
	@Test
	public void testOrderBOExt(){
		IntershopArtifacts a = BOExtensionFactory.createSources(ExtTypes.ORDERBO, "BlaOrderExt", "com.intershop.capi", "com.intershop.internal");

		assertThat(a.getInterfaceData(), containsString("import com.intershop.component.order.capi.OrderBO;"));
		
		assertThat(a.getImplementationData(), containsString("import com.intershop.component.order.capi.OrderBO;"));
		
		assertThat(a.getImplementationData(), containsString("public class BlaOrderExtImpl extends AbstractBusinessObjectExtension<OrderBO> implements BlaOrderExt"));
		
		assertThat(a.getFactoryData(), containsString("public class BlaOrderExtFactory extends AbstractBusinessObjectExtensionFactory<OrderBO>"));
		
		assertThat(a.getComponentData(), containsString("<instance with=\"com.intershop.internal.BlaOrderExtFactory\"/>"));
	}
}
