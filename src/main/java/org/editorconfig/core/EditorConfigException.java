package org.editorconfig.core;

import java.io.IOException;

/**
 * The base class of all EditorConfig exceptions
 *
 * @author Dennis.Ushakov
 */
public class EditorConfigException extends Exception {
	private static final long serialVersionUID = -6989884482075940375L;

	public EditorConfigException(String s, IOException e) {
		super(s, e);
	}
}
