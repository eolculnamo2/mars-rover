import { TGlobalAction, IActionType, IGlobalState, IAction } from '../../rovers';
import { GlobalState } from '.';

export function reducer(state: IGlobalState, action: IAction): IGlobalState {
  return GlobalActions[action.type](state, action);
}

export const ACTION_TYPES: IActionType = {
  SET_SOL: 'SET_SOL',
  SET_CAM: 'SET_CAM',
  SET_ROVER: 'SET_ROVER',
  SET_PHOTO: 'SET_PHOTO',
  SET_INITIAL_PHOTO: 'SET_INITIAL_PHOTO',
  SET_INDEX: 'SET_INDEX',
};

export const GlobalActions: TGlobalAction = {
  [ACTION_TYPES.SET_SOL]: (state, action) => ({ ...state, sol: action.payload }),
  [ACTION_TYPES.SET_CAM]: (state, action) => ({ ...state, cam: action.payload }),
  [ACTION_TYPES.SET_PHOTO]: (state, action) => ({ ...state, photoSrc: action.payload }),
  [ACTION_TYPES.SET_INDEX]: (state, action) => ({ ...state, photoIndex: action.payload }),
  // changing rovers resets cam to ensure invalid lingering values do not affect api call
  [ACTION_TYPES.SET_ROVER]: (state, action) => ({
    ...state,
    rover: action.payload,
    cam: GlobalState.cam,
  }),
  [ACTION_TYPES.SET_INITIAL_PHOTO]: (state, action) => ({
    ...state,
    photoSrc: action.payload,
    photoIndex: 0,
  }),
};
