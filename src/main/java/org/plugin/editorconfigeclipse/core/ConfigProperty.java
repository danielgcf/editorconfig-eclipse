package org.plugin.editorconfigeclipse.core;

import org.eclipse.core.runtime.preferences.InstanceScope;


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

	TRIM_TRAILING_WHITESPACE("trim_trailing_whitespace", "sp_cleanup.remove_trailing_whitespaces", EclipsePreferenceNode.JDT_UI) {
		@Override
		public String parse(String editorConfigProperyValue) {
			if ("true".equalsIgnoreCase(editorConfigProperyValue)) {
				InstanceScope.INSTANCE.getNode(TRIM_TRAILING_WHITESPACE.getEclipsePreferenceNode().getPreference()).put("editor_save_participant_org.eclipse.jdt.ui.postsavelistener.cleanup", "true");
				InstanceScope.INSTANCE.getNode(TRIM_TRAILING_WHITESPACE.getEclipsePreferenceNode().getPreference()).put("sp_cleanup.on_save_use_additional_actions", "true");
				return "true";
			} else {
				return "false";
			}
		}
	},

	INSERT_FINAL_NEWLINE("insert_final_newline", "org.eclipse.jdt.core.formatter.insert_new_line_at_end_of_file_if_missing", EclipsePreferenceNode.JDT_CORE) {
		@Override
		public String parse(String editorConfigProperyValue) {
			if ("true".equalsIgnoreCase(editorConfigProperyValue)) {
				return "insert";
			} else {
				return  "do not insert";
			}
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
			return (editorConfigProperyValue != null ? editorConfigProperyValue.toUpperCase() : "") ;
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
