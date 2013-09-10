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
	AstahFileToHash(String projectFilename) throws Exception, IOException,
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
