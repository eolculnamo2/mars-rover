import { createContext } from 'react';
import GlobalState from './globalState';

export { GlobalActions, reducer } from './globalActions';
export { default as GlobalState } from './globalState';

const GlobalContext: any = createContext(GlobalState);
export default GlobalContext;
