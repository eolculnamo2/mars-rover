import React, { useReducer, Reducer } from 'react';
import GlobalContext, { reducer, GlobalState } from './contexts/global';
import { IGlobalState, IAction } from './rovers';

function App(): JSX.Element {
  const [state, dispatch] = useReducer<Reducer<IGlobalState, IAction>>(reducer, GlobalState);

  return (
    <GlobalContext.Provider value={{ state, dispatch }}>
      <div>Here</div>
    </GlobalContext.Provider>
  );
}

export default App;
