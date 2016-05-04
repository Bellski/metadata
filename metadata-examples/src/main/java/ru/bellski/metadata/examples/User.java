package ru.bellski.metadata.examples;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by oem on 4/27/16.
 */
public class User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws MalformedURLException {
		Path classes = Paths.get("E:\\Programing\\git\\metadata\\metadata-examples\\target\\classes");
		URLClassLoader cl = new URLClassLoader(new URL[]{classes.toUri().toURL()});


	}
}
