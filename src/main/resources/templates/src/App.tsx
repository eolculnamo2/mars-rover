import React, { useReducer, Reducer } from 'react';
import GlobalContext, { reducer, GlobalState } from './contexts/global';
import { IGlobalState, IAction } from './rovers';
import { PhotoViewer, OptionsBlock } from './components';

function App(): JSX.Element {
  const [state, dispatch] = useReducer<Reducer<IGlobalState, IAction>>(reducer, GlobalState);

  return (
    <GlobalContext.Provider value={{ state, dispatch }}>
      <OptionsBlock />
      <PhotoViewer />
    </GlobalContext.Provider>
  );
}

export default App;
