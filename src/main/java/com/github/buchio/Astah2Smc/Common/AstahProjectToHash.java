// Copyright (c) 2013 Yukio Obuchi
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

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
