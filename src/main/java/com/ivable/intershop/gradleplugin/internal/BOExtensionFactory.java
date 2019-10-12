package com.ivable.intershop.gradleplugin.internal;

import com.ivable.intershop.gradleplugin.internal.templating.TemplatingEngine;

public final class BOExtensionFactory {
	
	private static final String ORDERBO_CLASS = "OrderBO";
	private static final String ICM_ORDER_CAPI = "com.intershop.component.order.capi";
	private static final String ICM_BASKET_CAPI = "com.intershop.component.basket.capi";
	private static final String CATEGORYBO_CLASS = "CatalogCategoryBO";
	private static final String ICM_CATALOG_CAPI = "com.intershop.component.catalog.capi";
	private static final String PRODUCTBO_CLASS = "ProductBO";
	private static final String ICM_PROD_CAPI = "com.intershop.component.product.capi";

	private BOExtensionFactory() {
		throw new IllegalStateException("Utility class");
	}

	private static final String POSTFIX_FACTORY = "Factory";
	private static final String POSTFIX_IMPL = "Impl";

	public static IntershopArtifacts createSources(BOExtensionConstants.ExtTypes type, String name, String capiPK,
			String internalPK) {

		switch (type) {
		case PRODUCTBO:
			return createSourcesProductBO(name, capiPK, internalPK);
		case CATALOGCATEGORYBO:
			return createSourcesCatalogCategoryBO(name, capiPK, internalPK);
		case BASKETBO:
			return createBasketBO(name, capiPK, internalPK);
		case ORDERBO:
			return createOrderBO(name, capiPK, internalPK);
		default:
			throw new IllegalArgumentException("Unknown extension type " + type);
		}
	}
	
	private static String getFactoryName(String extName){
		return extName.concat(POSTFIX_FACTORY);
	}
	private static String getImplementationName(String extName){
		return extName.concat(POSTFIX_IMPL);
	}

	private static IntershopArtifacts createSourcesProductBO(String extName, String capiPK, String internalPK) {
		// postfix
		String factoryName = getFactoryName(extName);
		String implName = getImplementationName(extName);

		// component
		String comp = TemplatingEngine.createComponent(factoryName, internalPK);

		// interface
		String inter = TemplatingEngine.createInterface(extName, capiPK, PRODUCTBO_CLASS,
				ICM_PROD_CAPI);

		// factory
		String factory = TemplatingEngine.createFactory(factoryName, internalPK, implName, PRODUCTBO_CLASS,
				ICM_PROD_CAPI, extName, capiPK);

		// Implementation
		String impl = TemplatingEngine.createExtImplementation(internalPK,
				ICM_PROD_CAPI, PRODUCTBO_CLASS, capiPK, extName, implName);

		// return artifacts
		return new IntershopArtifacts(inter, impl, factory, comp, extName, implName, factoryName, extName);
	}
	
	private static IntershopArtifacts createSourcesCatalogCategoryBO(String extName, String capiPK, String internalPK) {
		// postfix
		String factoryName = getFactoryName(extName);
		String implName = getImplementationName(extName);

		// component
		String comp = TemplatingEngine.createComponent(factoryName, internalPK);

		// interface
		String inter = TemplatingEngine.createInterface(extName, capiPK, CATEGORYBO_CLASS,ICM_CATALOG_CAPI);

		// factory
		String factory = TemplatingEngine.createFactory(factoryName, internalPK, implName, CATEGORYBO_CLASS,ICM_CATALOG_CAPI, extName, capiPK);

		// Implementation
		String impl = TemplatingEngine.createExtImplementation(internalPK,ICM_CATALOG_CAPI, CATEGORYBO_CLASS, capiPK, extName, implName);

		// return artifacts
		return new IntershopArtifacts(inter, impl, factory, comp, extName, implName, factoryName, extName);
	}
	
	private static IntershopArtifacts createBasketBO(String extName, String capiPK, String internalPK) {
		// postfix
		String factoryName = getFactoryName(extName);
		String implName = getImplementationName(extName);
		String bo = "BasketBO";
		String bopackage = ICM_BASKET_CAPI;

		// component
		String comp = TemplatingEngine.createComponent(factoryName, internalPK);

		// interface
		String inter = TemplatingEngine.createInterface(extName, capiPK, bo,bopackage);

		// factory
		String factory = TemplatingEngine.createFactory(factoryName, internalPK, implName, bo,bopackage, extName, capiPK);

		// Implementation
		String impl = TemplatingEngine.createExtImplementation(internalPK,bopackage, bo, capiPK, extName, implName);

		// return artifacts
		return new IntershopArtifacts(inter, impl, factory, comp, extName, implName, factoryName, extName);
	}
	
	private static IntershopArtifacts createOrderBO(String extName, String capiPK, String internalPK) {
		// postfix
		String factoryName = getFactoryName(extName);
		String implName = getImplementationName(extName);
		String bo = ORDERBO_CLASS;
		String bopackage = ICM_ORDER_CAPI;

		// component
		String comp = TemplatingEngine.createComponent(factoryName, internalPK);

		// interface
		String inter = TemplatingEngine.createInterface(extName, capiPK, bo,bopackage);

		// factory
		String factory = TemplatingEngine.createFactory(factoryName, internalPK, implName, bo,bopackage, extName, capiPK);

		// Implementation
		String impl = TemplatingEngine.createExtImplementation(internalPK,bopackage, bo, capiPK, extName, implName);

		// return artifacts
		return new IntershopArtifacts(inter, impl, factory, comp, extName, implName, factoryName, extName);
	}
}
