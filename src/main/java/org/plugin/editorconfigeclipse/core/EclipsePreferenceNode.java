package org.plugin.editorconfigeclipse.core;

public enum EclipsePreferenceNode {

	UI_EDITOR ("org.eclipse.ui.editors"), 
	JDT_CORE ("org.eclipse.jdt.core");
    
    private EclipsePreferenceNode(String preference) {
        this.preference = preference;
    }

    private final String preference;

    public String getPreference() {
         return preference;
    }
}
