package org.plugin.editorconfigeclipse.core;

public enum ConfigProperty {

	INDENT_STYLE("indent_style", "spacesForTabs", EclipsePreferenceNode.UI_EDITOR) {
		@Override
		public String parse(String editorConfigProperyValue) {
			return String.valueOf(("space").equalsIgnoreCase(editorConfigProperyValue));
		}
	}, 
	INDENT_SIZE("indent_size", "org.eclipse.jdt.core.formatter.tabulation.size", EclipsePreferenceNode.JDT_CORE) {
		@Override
		public String parse(String editorConfigProperyValue) {
			return editorConfigProperyValue;
		}
	}, 
	TAB_WIDTH("tab_width", "tabWidth", EclipsePreferenceNode.UI_EDITOR) {
		@Override
		public String parse(String editorConfigProperyValue) {
			return editorConfigProperyValue;
		}
	},
	END_OF_LINE("end_of_line", "line.separator", EclipsePreferenceNode.CORE_RUNTIME) {
		@Override
		public String parse(String editorConfigProperyValue) {
			if ("cr".equalsIgnoreCase(editorConfigProperyValue)) return "\r";
			if ("lf".equalsIgnoreCase(editorConfigProperyValue)) return "\n";
			if ("crlf".equalsIgnoreCase(editorConfigProperyValue)) return "\r\n";
			return "\r\n";
		}
	},
	CHARSET("charset", "encoding", EclipsePreferenceNode.CORE_RESOURCES) {
		@Override
		public String parse(String editorConfigProperyValue) {
			if (editorConfigProperyValue != null)
				editorConfigProperyValue = editorConfigProperyValue.toUpperCase();
			return editorConfigProperyValue;
		}
	};
	
	
	
	ConfigProperty(String editorconfig, String eclipse, EclipsePreferenceNode eclipsePreferenceNode) {
		this.editorconfig = editorconfig;
		this.eclipse = eclipse;
		this.eclipsePreferenceNode = eclipsePreferenceNode;
	}

	private final String editorconfig;
	private final String eclipse;
	private final EclipsePreferenceNode eclipsePreferenceNode;

	public String getEditorconfig() {
		return editorconfig;
	}
	public String getEclipse() {
		return eclipse;
	}
    public EclipsePreferenceNode getEclipsePreferenceNode() {
        return eclipsePreferenceNode;
    }
    
    public abstract String parse(String editorConfigProperyValue);
}
