package com.we.intershop.gradleplugin.internal;

import com.we.intershop.gradleplugin.internal.templating.TemplatingEngine;

public final class BOExtensionFactory {

	private static final String POSTFIX_FACTORY = "Factory";
	private static final String POSTFIX_IMPL = "Impl";

	public static IntershopArtifacts createSources(BOExtensionConstants.ExtTypes type, String name, String capiPK,
			String internalPK) {

		switch (type) {
		case PRODUCTBO:
			return createSourcesProductBO(name, capiPK, internalPK);
		case CATALOGCATEGORYBO:
			return createSourcesCatalogCategoryBO(name, capiPK, internalPK);
		default:
			throw new IllegalArgumentException("Unknown extension type " + type);
		}
	}

	private static IntershopArtifacts createSourcesProductBO(String extName, String capiPK, String internalPK) {
		// postfix
		String factoryName = extName.concat(POSTFIX_FACTORY);
		String implName = extName.concat(POSTFIX_IMPL);

		// component
		String comp = TemplatingEngine.createComponent(factoryName, internalPK);

		// interface
		String inter = TemplatingEngine.createInterface(extName, capiPK, "ProductBO",
				"com.intershop.component.product.capi");

		// factory
		String factory = TemplatingEngine.createFactory(factoryName, internalPK, implName, "ProductBO",
				"com.intershop.component.product.capi", extName, capiPK);

		// Implementation
		String impl = TemplatingEngine.createExtImplementation(internalPK,
				"com.intershop.component.product.capi", "ProductBO", capiPK, extName, implName);

		// return artifacts
		return new IntershopArtifacts(inter, impl, factory, comp, extName, implName, factoryName, extName);
	}
	
	private static IntershopArtifacts createSourcesCatalogCategoryBO(String extName, String capiPK, String internalPK) {
		// postfix
		String factoryName = extName.concat(POSTFIX_FACTORY);
		String implName = extName.concat(POSTFIX_IMPL);

		// component
		String comp = TemplatingEngine.createComponent(factoryName, internalPK);

		// interface
		String inter = TemplatingEngine.createInterface(extName, capiPK, "CatalogCategoryBO","com.intershop.component.catalog.capi");

		// factory
		String factory = TemplatingEngine.createFactory(factoryName, internalPK, implName, "CatalogCategoryBO","com.intershop.component.catalog.capi", extName, capiPK);

		// Implementation
		String impl = TemplatingEngine.createExtImplementation(internalPK,"com.intershop.component.catalog.capi", "CatalogCategoryBO", capiPK, extName, implName);

		// return artifacts
		return new IntershopArtifacts(inter, impl, factory, comp, extName, implName, factoryName, extName);
	}
}
