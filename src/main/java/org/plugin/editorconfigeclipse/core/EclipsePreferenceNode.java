package org.plugin.editorconfigeclipse.core;

public enum EclipsePreferenceNode {

	UI_EDITOR ("org.eclipse.ui.editors"), 
	JDT_CORE ("org.eclipse.jdt.core"),
	CORE_RESOURCES ("org.eclipse.core.resources"),
	CORE_RUNTIME ("org.eclipse.core.runtime");
    
    private EclipsePreferenceNode(String preference) {
        this.preference = preference;
    }

    private final String preference;

    public String getPreference() {
         return preference;
    }
}
