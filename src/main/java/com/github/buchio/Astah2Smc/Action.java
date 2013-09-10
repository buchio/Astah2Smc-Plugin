package com.github.buchio.Astah2Smc;

import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;
import com.github.buchio.Astah2Smc.Common.AstahProjectToHash;

public class Action implements IPluginActionDelegate {

	public Object run(IWindow window) throws UnExpectedException {
		try {
			AstahAPI api = AstahAPI.getAstahAPI();
			ProjectAccessor projectAccessor = api.getProjectAccessor();
			HashMap<?, ?> a = (new AstahProjectToHash(projectAccessor.getProject()))
					.getHash().get("classes");
			JFileChooser filechooser = new JFileChooser();
			filechooser.setDialogTitle("Select Output Directory.");
			filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if( filechooser.showSaveDialog(window.getParent()) == JFileChooser.APPROVE_OPTION ) {
				System.out.print( "JFileChooser : " + filechooser.getSelectedFile() );
			}
		} catch (ProjectNotFoundException e) {
			String message = "Project is not opened.Please open the project or create new project.";
			JOptionPane.showMessageDialog(window.getParent(), message,
					"Warning", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(window.getParent(),
					"Unexpected error has occurred.", "Alert",
					JOptionPane.ERROR_MESSAGE);
			throw new UnExpectedException();
		}
		return null;
	}

}
