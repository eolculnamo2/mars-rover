import { TGlobalAction, IActionType, IGlobalState, IAction } from '../../rovers';
import { GlobalState } from '.';

export function reducer(state: IGlobalState, action: IAction): IGlobalState {
  return GlobalActions[action.type](state, action);
}

export const ACTION_TYPES: IActionType = {
  SET_SOL: 'SET_SOL',
  SET_CAM: 'SET_CAM',
  SET_ROVER: 'SET_ROVER',
};

export const GlobalActions: TGlobalAction = {
  [ACTION_TYPES.SET_SOL]: (state, action) => ({ ...state, sol: action.payload }),
  [ACTION_TYPES.SET_CAM]: (state, action) => ({ ...state, cam: action.payload }),
  // changing rovers resets cam to ensure invalid lingering values do not affect api call
  [ACTION_TYPES.SET_ROVER]: (state, action) => ({
    ...state,
    rover: action.payload,
    cam: GlobalState.cam,
  }),
};
