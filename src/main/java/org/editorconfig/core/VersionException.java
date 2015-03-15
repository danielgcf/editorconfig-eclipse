package org.editorconfig.core;

/**
 * Exception which is thrown by {@link EditorConfig#getProperties(String)} if an
 * invalid version number is specified
 *
 * @author Dennis.Ushakov
 */
public class VersionException extends EditorConfigException {

	private static final long serialVersionUID = 1491108103877583947L;

	public VersionException(String s) {
		super(s, null);
	}
}
