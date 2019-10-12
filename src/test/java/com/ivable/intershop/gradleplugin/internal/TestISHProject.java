package com.ivable.intershop.gradleplugin.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Test;

import com.ivable.intershop.gradleplugin.internal.util.DirUtil;

public class TestISHProject {

	@Test
	public void testHasSubDir() throws IOException {
		Path p = Paths.get("/something/dir/bla/file.txt");
		assertTrue("dir should exist", DirUtil.hasSubName(p, new String[] { "dir" }));
	}

	@Test
	public void testHasSubDoesntExist() throws IOException {
		Path p = Paths.get("/something/dir/bla/file.txt");
		assertFalse("doesntexist shouldnt exist", DirUtil.hasSubName(p, new String[] { "doesntexist" }));
	}

	@Test
	public void testSearchingDirGradlePlugin() throws IOException {
		Path root = Paths.get(System.getProperty("user.dir"));
		Optional<Path> dir = DirUtil.getDirectoryByName(root, "gradleplugin");
		assertTrue(dir.isPresent());
	}

	@Test
	public void testSearchingDirDoesntExist() throws IOException {
		Path root = Paths.get(System.getProperty("user.dir"));
		Optional<Path> dir = DirUtil.getDirectoryByName(root, "somethingThatdoesntExist");
		assertFalse(dir.isPresent());
	}

	@Test
	public void testGetPackageName() throws IOException {
		Path root = Paths.get(System.getProperty("user.dir")).resolve("src/main/java");
		Optional<Path> dir = DirUtil.getDirectoryByName(root, "gradleplugin");

		String javaPackageName = "dir not found";
		if (dir.isPresent()) {
			javaPackageName = DirUtil.getJavaPackageName(root, dir.get());
		}
		
		assertEquals("com.ivable.intershop.gradleplugin", javaPackageName);
	}

	@Test
	public void testGetPackageNameExtraSeperator() throws IOException {
		Path root = Paths.get("/proj/src/");
		Path javadir = Paths.get("/proj/src/com/something/bla/");

		String javaPackageName = DirUtil.getJavaPackageName(root, javadir);

		assertEquals("com.something.bla", javaPackageName);
	}
	
	@Test
	public void testGetPackageNameWindowsSeperator() throws IOException {
		Path root = Paths.get("c:\\proj\\src\\");
		Path javadir = Paths.get("c:\\proj\\src\\com\\something\\bla\\");
		
		String javaPackageName = DirUtil.getJavaPackageName(root, javadir,"\\");

		assertEquals("com.something.bla", javaPackageName);
	}

	@Test
	public void testArtifacts() {
		IntershopArtifacts a = new IntershopArtifacts("interfacedata", "impldata", "factdata", "compdata",
				"InterfaceName", "ImplName", "FactName", "CompName");

		assertTrue(a.getCompFilename().equals("CompName.component"));
		assertTrue(a.getFactFilename().equals("FactName.java"));
		assertTrue(a.getImplFilename().equals("ImplName.java"));
		assertTrue(a.getInterfaceFilename().equals("InterfaceName.java"));
	}
}
