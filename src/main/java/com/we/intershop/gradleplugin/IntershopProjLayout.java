package com.we.intershop.gradleplugin;

import java.io.IOException;

import org.gradle.api.Project;

import com.we.intershop.gradleplugin.internal.IntershopProjLayoutImpl;

/**
 * An Intershop project object. Understand the structure of the project and
 * write artifacts to the project
 * 
 * @author willem evertse
 * @since
 */
public interface IntershopProjLayout {

	/**
	 * @param name
	 * @param javaInterface
	 */
	public void writeInterface(String name, String javaInterface);

	/**
	 * @param name
	 * @param implementation
	 */
	public void writeImplementation(String name, String implementation);

	/**
	 * @param name
	 * @param factory
	 */
	public void writeFactory(String name, String factory);

	/**
	 * @param name
	 * @param component
	 * @throws IOException
	 */
	public void writeComponent(String name, String component) throws IOException;

	/**
	 * Get capi package name
	 * 
	 * @return capi package name
	 */
	public String getCapiPKName();

	/**
	 * Get internal package name
	 * 
	 * @return internal package name
	 */
	public String getInternalPKName();

	/**
	 * Creates an instance of IntershopProjLayout
	 * 
	 * @param proj
	 *            current gradle project
	 * @return IntershopProjLayout instance for this project
	 */
	public static IntershopProjLayout createProjInstance(Project proj) {
		return new IntershopProjLayoutImpl(proj);
	}
}
