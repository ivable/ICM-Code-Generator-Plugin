package com.ivable.intershop.gradleplugin;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.ivable.intershop.gradleplugin.internal.BOExtensionFactory;
import com.ivable.intershop.gradleplugin.internal.IntershopArtifacts;
import com.ivable.intershop.gradleplugin.internal.BOExtensionConstants.ExtTypes;

/**
 * Gradle task to generate business object extensions
 * 
 * @author willem evertse
 *
 */
public class BOExtensionTask extends DefaultTask {
	private static final String DEFAULT_EXT_TYPE = "1";

	@TaskAction
	public void createBOExtension() throws IOException {
		// get extension name
		String extName = (String) this.getProject().getProperties().get("extname");

		if (StringUtils.isBlank(extName)) {
			String helpmsg = Resources.toString(Resources.getResource("HelpMsg.txt"), Charsets.UTF_8);
			System.out.println(helpmsg);
			return;
		}

		// get type
		ExtTypes type = getType(this.getProject().getProperties());

		// icm project
		IntershopProjLayout icmProj = IntershopProjLayout.createProjInstance(this.getProject());

		// get sources
		IntershopArtifacts sources = BOExtensionFactory.createSources(type, extName, icmProj.getCapiPKName(),
				icmProj.getInternalPKName());

		// write artifacts to project
		icmProj.writeInterface(sources.getInterfaceFilename(), sources.getInterfaceData());
		icmProj.writeImplementation(sources.getImplFilename(), sources.getImplementationData());
		icmProj.writeFactory(sources.getFactFilename(), sources.getFactoryData());
		icmProj.writeComponent(sources.getCompFilename(), sources.getComponentData());

		// done
		System.out.println(extName + " successfully created !!");
	}

	/**
	 * Get the type of BO to generate
	 * 
	 * @param prop
	 * @return
	 */
	private static ExtTypes getType(Map<String, ?> prop) {
		// get the type of extension
		String type = (String) prop.get("type");
		if (StringUtils.isBlank(type)) {
			type = DEFAULT_EXT_TYPE; // default to 1 (ProductBO)
		}

		switch (type) {
		case "1":
			return ExtTypes.PRODUCTBO;
		case "2":
			return ExtTypes.CATALOGCATEGORYBO;
		case "3":
			return ExtTypes.BASKETBO;
		case "4":
			return ExtTypes.ORDERBO;
		default:
			throw new IllegalArgumentException("Unknown extension type " + type);
		}
	}
}
