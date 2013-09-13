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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class AstahHashToSmcFiles {
	String mBaseDir;

	public AstahHashToSmcFiles(String basedir) {
		mBaseDir = basedir;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void outputSmcFiles(HashMap<String, HashMap> classes)
			throws IOException {
		for (String className : classes.keySet()) {
			System.out.println("  Class " + className);
			outputSmcFile(mBaseDir + "/" + className.replaceAll("::", "/")
					+ ".sm", classes.get(className));
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void outputSmcFile(String fileName,
			HashMap<String, HashMap> classInfo) throws IOException {
		Object[] maps = classInfo.keySet().toArray();
		Object firstMapName = maps[0];
		HashMap<String, String> info;
		if (maps.length > 1) {
			for (Object mapName : maps) {
				HashMap<String, HashMap> map = classInfo.get(mapName);
				info = map.get("info");
				if (info != null && info.get("definition") != null) {
					if (info.get("start") != null) {
						firstMapName = mapName;
					}
				}
			}
		}

		HashMap<String, HashMap> firstMap = classInfo.get(firstMapName);
		info = firstMap.get("info");

		if (info != null) {
			System.out.println("Creating " + fileName);
			File file = new File(fileName);
			File dir = file.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					file)));
			if (info.get("start") != null) {
				pw.println("%start " + info.get("map") + "::"
						+ info.get("start"));
			}
			pw.println("%class " + info.get("class"));
			for (Object mapName : maps) {
				HashMap<String, HashMap> map = classInfo.get(mapName);
				info = map.get("info");
				if (info != null) {
					pw.println("%map " + info.get("map"));
					pw.println("%%");

					HashMap<String, HashMap> states = map.get("states");
					for (String stateName : states.keySet()) {
						pw.print(stateName + " {");
						if (!states.get(stateName).isEmpty()) {
							HashMap<?, ?> transitions = (HashMap<?, ?>) states
									.get(stateName).get("transitions");
							for (Object tid : transitions.keySet()) {
								HashMap<?, ?> transition = (HashMap<?, ?>) transitions
										.get(tid);
								pw.print("\t" + transition.get("Event"));
								if (transition.containsKey("Guard")) {
									pw.print("\t[" + transition.get("Guard")
											+ "]");
								}
								String target = (String) (transition
										.get("Target"));
								pw.print("\t" + target);

								if (transition.containsKey("Action")) {
									pw.print("\t{" + transition.get("Action")
											+ "}");
								} else {
									pw.print("\t{}");
								}
								pw.println();
							}
						}
						pw.println("}");
						pw.println();
					}
					pw.println("%%");
				}
			}
			pw.close();
		}
	}
}
