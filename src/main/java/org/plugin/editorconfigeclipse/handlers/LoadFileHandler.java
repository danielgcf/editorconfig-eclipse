package org.plugin.editorconfigeclipse.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.editorconfig.core.EditorConfig;
import org.editorconfig.core.EditorConfig.OutPair;
import org.editorconfig.core.EditorConfigException;
import org.osgi.service.prefs.BackingStoreException;
import org.plugin.editorconfigeclipse.core.ConfigProperty;
import org.plugin.editorconfigeclipse.core.EclipsePreferenceNode;

public class LoadFileHandler extends AbstractHandler {

	private final static String WINDOW_TITLE = "editorconfig-eclipse";

	public LoadFileHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		String message = null;
		Map<String, String> editorConfigProperties = null;
		try {
			editorConfigProperties = getEditorConfigProperties();
			setEclipseProperties(editorConfigProperties);
			flushAll();
			message = "Done!";
		} catch (EditorConfigException e) {
			message = "Error during loading: " + e.getCause();
			if (e.getCause() instanceof FileNotFoundException) {
				message = "File not found!";
			}
		} catch (Exception e) {
			message = "Error during loading: ";
			if (e.getMessage() != null)
				message += (e.getMessage() + " ");
			if (e.getCause() != null)
				message += (e.getCause() + " ");
		}

		MessageDialog.openInformation(window.getShell(), WINDOW_TITLE,
				message);
		return null;
	}

	private Map<String, String> getEditorConfigProperties()
			throws EditorConfigException {

		Map<String, String> configsMap = new HashMap<>();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		File workspaceDirectory = workspace.getRoot().getLocation().toFile();
		EditorConfig editorConfigReader = new EditorConfig();
		List<OutPair> pairs = editorConfigReader
				.getProperties(workspaceDirectory.getAbsolutePath());

		for (OutPair outPair : pairs) {
			configsMap.put(outPair.getKey(), outPair.getVal());
		}

		return configsMap;
	}

	private void setEclipseProperties(
			Map<String, String> editorConfigProperties)
			throws EditorConfigException {
		String editorConfigProperyValue = null;
		for (ConfigProperty configProperty : ConfigProperty.values()) {
			editorConfigProperyValue = editorConfigProperties
					.get(configProperty.getEditorconfig());
			editorConfigProperyValue = configProperty
					.parse(editorConfigProperyValue);
			InstanceScope.INSTANCE.getNode(
					configProperty.getEclipsePreferenceNode()
							.getPreference()).put(
					configProperty.getEclipse(), editorConfigProperyValue);
		}
	}

	private void flushAll() throws Exception {
		boolean hasError = false;
		for (EclipsePreferenceNode eclipsePreferenceNode : EclipsePreferenceNode
				.values()) {
			try {
				InstanceScope.INSTANCE.getNode(
						eclipsePreferenceNode.getPreference()).flush();
			} catch (BackingStoreException e) {
				hasError = true;
			}
		}

		if (hasError) {
			throw new Exception("Not all properties was set!");
		}
	}

}
