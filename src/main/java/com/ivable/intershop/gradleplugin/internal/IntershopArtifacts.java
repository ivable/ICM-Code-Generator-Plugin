package com.ivable.intershop.gradleplugin.internal;

public class IntershopArtifacts {
	private String interfaceData;
	private String implementationData;
	private String factoryData;
	private String componentData;
	
	private String interName;
	private String implName;
	private String factName;
	private String compName;
	
	private String interfaceFilename;
	private String implFilename;
	private String factFilename;
	private String compFilename;
	

	public IntershopArtifacts(String interfaceData, String implementationData, String factoryData,
			String componentData,String interName,String implName,String factName,String compName) {
		this.interfaceData = interfaceData;
		this.implementationData = implementationData;
		this.factoryData = factoryData;
		this.componentData = componentData;
		
		this.interName=interName;
		this.implName=implName;
		this.factName=factName;
		this.compName=compName;
		
		this.setInterfaceFilename(interName.concat(".java"));
		this.setImplFilename(implName.concat(".java"));
		this.setFactFilename(factName.concat(".java"));
		this.setCompFilename(compName.concat(".component"));
	}
	
	public IntershopArtifacts() {
	}

	public String getInterfaceData() {
		return interfaceData;
	}

	public void setInterfaceData(String interfaceData) {
		this.interfaceData = interfaceData;
	}

	public String getImplementationData() {
		return implementationData;
	}

	public void setImplementationData(String implementationData) {
		this.implementationData = implementationData;
	}

	public String getFactoryData() {
		return factoryData;
	}

	public void setFactoryData(String factoryData) {
		this.factoryData = factoryData;
	}

	public String getComponentData() {
		return componentData;
	}

	public void setComponentData(String componentData) {
		this.componentData = componentData;
	}

	public String getInterfaceFilename() {
		return interfaceFilename;
	}

	public void setInterfaceFilename(String interfaceFilename) {
		this.interfaceFilename = interfaceFilename;
	}

	public String getImplFilename() {
		return implFilename;
	}

	public void setImplFilename(String implFilename) {
		this.implFilename = implFilename;
	}

	public String getFactFilename() {
		return factFilename;
	}

	public void setFactFilename(String factFilename) {
		this.factFilename = factFilename;
	}

	public String getCompFilename() {
		return compFilename;
	}

	public void setCompFilename(String compFilename) {
		this.compFilename = compFilename;
	}

	public String getInterName() {
		return interName;
	}

	public void setInterName(String interName) {
		this.interName = interName;
	}

	public String getImplName() {
		return implName;
	}

	public void setImplName(String implName) {
		this.implName = implName;
	}

	public String getFactName() {
		return factName;
	}

	public void setFactName(String factName) {
		this.factName = factName;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}
}
