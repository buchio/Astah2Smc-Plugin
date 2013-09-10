package com.github.buchio.Astah2Smc.Common;

import java.util.HashMap;

import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;

public class AstahProjectToHash {
	private IModel mProject;

	public AstahProjectToHash(IModel project) {
		mProject = project;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap<String, HashMap> getHash() throws Exception {
		HashMap<String, HashMap> hash = new HashMap<String, HashMap>();
		HashMap<String, HashMap> stateMachines = new HashMap<String, HashMap>();
		try {
			stateMachines = getPackageInfo(mProject);
		} catch (InvalidUsingException e) {
			throw new Exception("Invalid use of project.");
		}

		hash.put("statemachines", stateMachines);

		HashMap<String, HashMap> classes = new HashMap<String, HashMap>();
		for (String stateMachineName : stateMachines.keySet()) {
			String classname = stateMachineName.replaceAll("::[^:]*$", "");
			if (!classes.containsKey(classname)) {
				HashMap<String, HashMap> map = new HashMap<String, HashMap>();
				map.put(stateMachineName, stateMachines.get(stateMachineName));
				classes.put(classname, map);
			} else {
				classes.get(classname).put(stateMachineName,
						stateMachines.get(stateMachineName));
			}
		}
		hash.put("classes", classes);

		return hash;
	}

	@SuppressWarnings("rawtypes")
	private HashMap<String, HashMap> getPackageInfo(IPackage iPackage)
			throws Exception, InvalidUsingException {
		HashMap<String, HashMap> stateMachines = new HashMap<String, HashMap>();
		for (IDiagram iDiagram : iPackage.getDiagrams()) {
			stateMachines.putAll(getDiagramInfo(iDiagram));
		}
		for (INamedElement iNamedElement : iPackage.getOwnedElements()) {
			if (iNamedElement instanceof IPackage) {
				IPackage iChildPackage = (IPackage) iNamedElement;
				stateMachines.putAll(getPackageInfo(iChildPackage));
			} else {
				for (IDiagram iDiagram : iNamedElement.getDiagrams()) {
					stateMachines.putAll(getDiagramInfo(iDiagram));
				}
			}
		}
		return stateMachines;
	}

	@SuppressWarnings("rawtypes")
	private HashMap<String, HashMap> getDiagramInfo(IDiagram iDiagram)
			throws Exception {
		return (new AstahStateMachineDiagramToHash(iDiagram)).getHash();
	}

}
