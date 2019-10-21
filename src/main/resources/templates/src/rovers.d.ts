export interface IGlobalState {
  sol: number;
  rover: string;
  cam: string;
}

export interface IAction {
  payload: any;
  type: string;
}

export interface IActionType {
  [key: string]: string;
}

export interface IRoverCams {
  [key: string]: Array<string>;
}

export type TGlobalAction = {
  [key: string]: (state: IGlobalState, action: IAction) => IGlobalState;
};
