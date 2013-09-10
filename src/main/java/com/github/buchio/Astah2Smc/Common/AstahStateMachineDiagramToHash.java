package com.github.buchio.Astah2Smc.Common;

import java.util.HashMap;

import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IFinalState;
import com.change_vision.jude.api.inf.model.IPseudostate;
import com.change_vision.jude.api.inf.model.IState;
import com.change_vision.jude.api.inf.model.IStateMachine;
import com.change_vision.jude.api.inf.model.IStateMachineDiagram;
import com.change_vision.jude.api.inf.model.ITransition;
import com.change_vision.jude.api.inf.model.IVertex;

public class AstahStateMachineDiagramToHash {
	private IDiagram mDiagram;

	AstahStateMachineDiagramToHash(IDiagram diagram) {
		mDiagram = diagram;
	}

	@SuppressWarnings("rawtypes")
	public HashMap<String, HashMap> getHash() throws Exception {
		HashMap<String, HashMap> hash = new HashMap<String, HashMap>();
		if (mDiagram instanceof IStateMachineDiagram) {
			System.out.println("Getting Information from "
					+ mDiagram.getFullName("::"));
			hash.put(mDiagram.getFullName("::"),
					getStateMachineDiagramInfo((IStateMachineDiagram) mDiagram));
		}
		return hash;
	}

	@SuppressWarnings("rawtypes")
	private HashMap<?, ?> getStateMachineDiagramInfo(
			IStateMachineDiagram iStateMachineDiagram) throws Exception {
		HashMap<String, HashMap> stateMachine = new HashMap<String, HashMap>();
		String[] splitedFullName = iStateMachineDiagram.getFullName(":").split(
				":");
		String mapName = iStateMachineDiagram.getName();
		String className = "";
		if (splitedFullName.length >= 2) {
			className = splitedFullName[splitedFullName.length - 2];
		}

		IStateMachine iStateMachine = iStateMachineDiagram.getStateMachine();
		IVertex[] iVertexes = iStateMachine.getVertexes();
		IState initialState = null;

		HashMap<String, HashMap> states = new HashMap<String, HashMap>();

		for (IVertex iVertex : iVertexes) {
			if (iVertex instanceof IPseudostate) {
				IPseudostate iPseudostate = (IPseudostate) iVertex;
				if (iPseudostate.isInitialPseudostate()) {
					initialState = (IState) (iVertex.getOutgoings()[0]
							.getTarget());
				} else {
					// throw new Exception( "開始疑似状態以外は未サポート" );
				}
			}
		}

		for (IVertex iVertex : iVertexes) {
			if (iVertex instanceof IState) {
				if (!((IState) iVertex).isSubmachineState()) {
					states.put(iVertex.getName(),
							getStateInfo((IState) iVertex));
				} else if (iVertex instanceof IFinalState) {
					// IFinalState iFinalState = (IFinalState)iVertex;
				} else if (iVertex instanceof IPseudostate) {
					IPseudostate iPseudostate = (IPseudostate) iVertex;
					if (iPseudostate.isInitialPseudostate()) {
					} else if (iPseudostate.isShallowHistoryPseudostate()) {
					} else if (iPseudostate.isDeepHistoryPseudostate()) {
					} else if (iPseudostate.isJunctionPseudostate()) {
					} else if (iPseudostate.isChoicePseudostate()) {
					} else if (iPseudostate.isForkPseudostate()) {
					} else if (iPseudostate.isJoinPseudostate()) {
					} else if (iPseudostate.isStubState()) {
					}
				}
			}
		}
		HashMap<String, String> stateMachineInfo = new HashMap<String, String>();
		stateMachineInfo
				.put("definition", iStateMachineDiagram.getDefinition());
		stateMachineInfo.put("class", className);
		stateMachineInfo.put("map", mapName);
		stateMachine.put("info", stateMachineInfo);
		stateMachine.put("states", states);
		if (initialState != null) {
			stateMachineInfo.put("start", initialState.getName());
		}
		return stateMachine;
	}

	@SuppressWarnings("rawtypes")
	private HashMap<?, ?> getStateInfo(IState iState) throws Exception {
		HashMap<String, HashMap> transitions = new HashMap<String, HashMap>();
		for (ITransition iTransition : iState.getInternalTransitions()) {
			transitions.put(iTransition.getId(),
					getInternalTransitionInfo(iTransition));
		}
		for (ITransition iTransition : iState.getOutgoings()) {
			// getOutgoingsに内部遷移も含まれてしまうので、既に取得済の遷移は対象外とする。
			if (!transitions.containsKey(iTransition.getId())) {
				transitions.put(iTransition.getId(),
						getTransitionInfo(iTransition));
			}
		}
		HashMap<String, Object> state = new HashMap<String, Object>();
		state.put("transitions", transitions);
		state.put("entry", iState.getEntry());
		state.put("exit", iState.getExit());
		return state;
	}

	private HashMap<?, ?> getInternalTransitionInfo(ITransition iTransition)
			throws Exception {
		// 内部遷移を追加
		HashMap<String, String> transition = new HashMap<String, String>();
		transition.put("Event", iTransition.getEvent());
		transition.put("Target", "nil");
		if (iTransition.getGuard().length() > 0) {
			transition.put("Guard", iTransition.getGuard());
		}
		if (iTransition.getAction().length() > 0) {
			transition.put("Action", iTransition.getAction());
		}
		return transition;
	}

	private HashMap<?, ?> getTransitionInfo(ITransition iTransition)
			throws Exception {
		HashMap<String, String> transition = new HashMap<String, String>();
		String event = iTransition.getEvent();

		IVertex target = iTransition.getTarget();
		if (target instanceof IState) {
			transition.put("Target", target.getName());
		} else if (target instanceof IPseudostate) {
			IPseudostate iPseudostate = (IPseudostate) target;
			if (iPseudostate.isInitialPseudostate()) {
			} else if (iPseudostate.isShallowHistoryPseudostate()) {
				throw new Exception(
						"ShallowHistoryPseudostate does not supported.");
			} else if (iPseudostate.isDeepHistoryPseudostate()) {
				// 深い履歴状態を用いてpopを表現する
				// Eventを「イベント|pop(復帰先に渡すイベント)」の形で記述する。
				String[] es = iTransition.getEvent().split("\\|");
				event = es[0];
				transition.put("Target", es[1]);
			} else if (iPseudostate.isJunctionPseudostate()) {
				throw new Exception("JunctionPseudostate does not supported.");
			} else if (iPseudostate.isChoicePseudostate()) {
				throw new Exception("ChoicePseudostate does not supported.");
			} else if (iPseudostate.isForkPseudostate()) {
				throw new Exception("ForkPseudostate does not supported.");
			} else if (iPseudostate.isJoinPseudostate()) {
				throw new Exception("JoinPseudostate does not supported.");
			} else if (iPseudostate.isStubState()) {
				String[] es = iTransition.getEvent().split("\\|");

				if (es.length > 1) {
					if (es[1].equals("Push")) {
						transition.put("Target",
								"push(" + iPseudostate.getContainer() + "::"
										+ target.getName() + ")");
						event = es[0];
					} else {
						throw new Exception("Unknown target option [" + es[1]
								+ "]");
					}

				} else {
					transition.put("Target",
							"jump(" + iPseudostate.getContainer() + "::"
									+ target.getName() + ")");
				}
			}

		}
		transition.put("Event", event);
		if (iTransition.getGuard().length() > 0) {
			transition.put("Guard", iTransition.getGuard());
		}
		if (iTransition.getAction().length() > 0) {
			transition.put("Action", iTransition.getAction());
		}
		return transition;
	}

}
