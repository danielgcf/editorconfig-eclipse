package org.editorconfig.core;

import java.io.IOException;

/**
 * Exception which is thrown by {@link EditorConfig#getProperties(String)} if an
 * EditorConfig file could not be parsed
 *
 * @author Dennis.Ushakov
 * {@link https://github.com/editorconfig/editorconfig-core-java}
 */
public class ParsingException extends EditorConfigException {

	private static final long serialVersionUID = -7931221808937178887L;

	public ParsingException(String s, IOException e) {
		super(s, e);
	}
}
