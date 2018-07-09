package com.we.intershop.gradleplugin.internal.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.we.intershop.gradleplugin.IntershopProjLayout;

/**
 * Directory Util Class Collection of utils for working with dir paths in the
 * context of a icm project
 * 
 * @see IntershopProjLayout
 * @author willem
 */
public final class DirUtil {

	private final static String[] IGNORE_DIRS = new String[] { "bin", "build", ".gradle", "gradle", ".settings" };

	private DirUtil() {
	}

	/**
	 * Get the first directory by given name by traversing the root path
	 * 
	 * @param root
	 *            path to search for dir name
	 * @param dirName
	 *            name of the directory to find
	 * @return if found a path otherwise empty
	 */
	public static Optional<Path> getDirectoryByName(Path root, String dirName) {
		try {
			Optional<Path> rs = getDirectories(root).filter(p -> p.endsWith(dirName)).findFirst();
			return rs;
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
		}
		return Optional.empty();
	}

	/**
	 * Walk through all directory structure, filter out non-dir and ignore dir
	 * in the ignore list
	 * 
	 * @param path
	 *            path to traverse
	 * @return stream of Paths that remain after filter
	 * @throws IOException
	 */
	private static Stream<Path> getDirectories(Path path) throws IOException {
		return Files.walk(path).filter(p -> !hasSubName(p, IGNORE_DIRS)).filter(p -> Files.isDirectory(p));
	}

	/**
	 * 
	 * 
	 * @param path
	 * @param names
	 * @return
	 */
	public static boolean hasSubName(Path path, String... names) {
		if (path == null || names == null) {
			throw new IllegalArgumentException("path or names can't be null");
		}

		for (int i = 0; i < path.getNameCount(); i++) {
			List<String> nameslist = Arrays.asList(names);
			if (nameslist.contains(path.getName(i).toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return package name from the root to the target Path
	 * 
	 * @param packageRoot
	 *            root where it starts
	 * @param packageFolder
	 *            until the target
	 * @return package name as String
	 */
	public static String getJavaPackageName(Path packageRoot, Path packageFolder) {
		String diff = StringUtils.difference(packageRoot.toString(), packageFolder.toString());

		// remove seperators at the start
		if (diff.startsWith(java.io.File.separator)) {
			diff = diff.substring(1, diff.length());
		}

		return diff.replaceAll(java.io.File.separator, ".");
	}

	/**
	 * Create dir
	 * 
	 * @param path
	 *            path to create
	 * @return created Path
	 */
	public static Path createDir(Path path) {
		try {
			return Files.createDirectory(path);
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			throw new IllegalStateException("cant create path " + path, e);
		}
	}
}
