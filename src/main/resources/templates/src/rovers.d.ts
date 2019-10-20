export interface IGlobalState {
  sol: string;
}

export interface IAction {
  payload: any;
  type: string;
}

export interface IActionType {
  [key: string]: string;
}

export type TGlobalAction = {
  [key: string]: (state: IGlobalState, action: IAction) => IGlobalState;
};
