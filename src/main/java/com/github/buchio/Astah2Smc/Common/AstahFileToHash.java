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

import java.io.IOException;
import java.util.HashMap;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.LicenseNotFoundException;
import com.change_vision.jude.api.inf.exception.NonCompatibleException;
import com.change_vision.jude.api.inf.exception.ProjectLockedException;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.project.ProjectAccessor;

/**
 * AstahファイルからSMCファイルに変換するためのHashデータを作成する。
 */
public class AstahFileToHash {
	private IModel mProject;

	/**
	 * @param projectFilename
	 *            プロジェクトファイル名
	 * @throws Exception
	 *             Astah
	 *             pluginが発行する例外は一旦catchし、内容をStringに格納してExceptionとしてthrowする
	 * @throws IOException
	 *             内部で発生してもcatchしない
	 * @throws ClassNotFoundException
	 *             内部で発生してもcatchしない
	 * 
	 */
	public AstahFileToHash(String projectFilename) throws Exception, IOException,
			ClassNotFoundException {
		try {
			ProjectAccessor projectAccessor = AstahAPI.getAstahAPI()
					.getProjectAccessor();
			projectAccessor.open(projectFilename, true, false, true);
			mProject = projectAccessor.getProject();
		} catch (LicenseNotFoundException e) {
			throw new Exception("Astah license not found.");
		} catch (ProjectNotFoundException e) {
			throw new Exception("Astah Project not found.");
		} catch (ProjectLockedException e) {
			throw new Exception("Astah Project locked.");
		} catch (NonCompatibleException e) {
			throw new Exception("Astah Project is not compatible.");
		}
	}

	@SuppressWarnings("rawtypes")
	public HashMap<String, HashMap> getHash() throws Exception {
		return (new AstahProjectToHash(mProject)).getHash();
	}

}
