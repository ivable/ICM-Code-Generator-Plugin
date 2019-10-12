package com.ivable.intershop.gradleplugin.internal;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.internal.impldep.org.eclipse.jgit.errors.NotSupportedException;

import com.ivable.intershop.gradleplugin.IntershopProjLayout;
import com.ivable.intershop.gradleplugin.internal.util.DirUtil;

/**
 * Implemantation of IntershopProjLayout
 * 
 * @see IntershopProjLayout
 * 
 * @author willem evertse
 *
 */
public final class IntershopProjLayoutImpl implements IntershopProjLayout {
	private static final String SRC_MAIN = "main";
	private final Path rootPath;
	private final Path javaSrcPath;
	private final Path capi;
	private final Path internalPath;
	private final Path componentPath;

	private final Logger logger;

	public IntershopProjLayoutImpl(final Project project) {
		// gradle logger
		logger = project.getLogger();

		// get java plugin
		JavaPluginConvention javaplugin = getGradleJavaPlugin(project);

		// root of the cartridge project
		rootPath = FileSystems.getDefault().getPath(project.getProjectDir().getAbsolutePath());

		// set java path
		this.javaSrcPath = getJavaSrcPath(javaplugin);

		// set capi path
		this.capi = getPath(javaSrcPath, "capi");

		// set internal package
		this.internalPath = getPath(javaSrcPath, "internal");

		// component path
		this.componentPath = getComponetPath();
	}

	private Path getComponetPath() {
		Optional<Path> dir = DirUtil.getDirectoryByName(rootPath, "components");
		// if doesnt exist, create the component path
		if (!dir.isPresent()) {
			Path newcompPath = getPath(rootPath, "staticfiles").resolve("cartridge/components");
			return DirUtil.createDir(newcompPath);
		}
		return dir.get();
	}

	private Path getPath(Path root, String dirname) {
		Optional<Path> dir = DirUtil.getDirectoryByName(root, dirname);
		if (!dir.isPresent()) {
			throw new IllegalStateException("cant find " + dirname + " path");
		}
		return dir.get();
	}

	/**
	 * Get the java src directory
	 * 
	 * @param javaplugin
	 *            gradle java plugin
	 * @return src dir for java
	 */
	private Path getJavaSrcPath(JavaPluginConvention javaplugin) {
		// find the main source set
		SourceSet mainSrc = javaplugin.getSourceSets().getByName(SRC_MAIN);
		if (mainSrc == null) {
			logger.info("source sets : {}", javaplugin.getSourceSets());
			throw new IllegalStateException("cant find main source set");
		}

		// get the first (existing) src dir
		Optional<File> srcDir = mainSrc.getJava().getSrcDirs().stream().filter(File::exists).findFirst();
		if (!srcDir.isPresent()) {
			throw new IllegalStateException("cant find existing java src dir");
		}

		logger.info("java src dir : {}", srcDir.get());
		return srcDir.get().toPath();
	}

	/**
	 * Get java plugin
	 * 
	 * @param project
	 *            gradle project
	 * @return java plugin
	 */
	private JavaPluginConvention getGradleJavaPlugin(Project project) {
		JavaPluginConvention javaplugin = project.getConvention().getPlugin(JavaPluginConvention.class);

		// can't continue without plugin, need this to determine the sourceset
		if (javaplugin == null) {
			throw new IllegalArgumentException("Cant find java plugin. Task need to java plugin to determine src");
		}
		logger.info("java plugin found");
		return javaplugin;
	}

	@Override
	public String getCapiPKName() {
		return DirUtil.getJavaPackageName(this.javaSrcPath, this.capi);
	}

	@Override
	public String getInternalPKName() {
		return DirUtil.getJavaPackageName(this.javaSrcPath, this.internalPath);
	}

	public void writeInterface(String name, String data) {
		logger.debug("writing Interface data : {}", data);
		try {
			Files.write(this.capi.resolve(name), data.getBytes());
		} catch (IOException e) {
			logger.error("error writing Interface ", e);
		}
	}

	public void writeImplementation(String name, String data) {
		logger.debug("writing Implementatio data : {}", data);
		try {
			Files.write(this.internalPath.resolve(name), data.getBytes());
		} catch (IOException e) {
			logger.error("error writing Implementatio ", e);
		}
	}

	public void writeFactory(String name, String data) {
		logger.debug("writing Factory data : {}", data);
		try {
			Files.write(this.internalPath.resolve(name), data.getBytes());
		} catch (IOException e) {
			logger.error("error writing Factory ", e);
		}
	}

	@Override
	public void writeComponent(String name, String data) throws IOException {
		logger.debug("writing component data : {}", data);
		try {
			Files.write(this.componentPath.resolve(name), data.getBytes());
		} catch (IOException e) {
			logger.error("error writing component ", e);
		}
	}

}
