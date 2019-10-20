import { TGlobalAction, IActionType, IGlobalState, IAction } from '../../rovers';

export function reducer(state: IGlobalState, action: IAction): IGlobalState {
  return GlobalActions[action.type](state, action);
}

export const ACTION_TYPES: IActionType = {
  SET_SOL: 'SET_SOL',
};

export const GlobalActions: TGlobalAction = {
  [ACTION_TYPES.SET_SOL]: (state, action) => {
    return { ...state, sol: action.payload };
  },
};
