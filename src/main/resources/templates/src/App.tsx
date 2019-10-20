import React, { useReducer, Reducer } from 'react';
import GlobalContext, { reducer, GlobalState } from './contexts/global';
import { IGlobalState, IAction } from './rovers';
import { PhotoViewer, OptionsBlock } from './components';
import { GlobalStyles } from './global-styles';

function App(): JSX.Element {
  const [state, dispatch] = useReducer<Reducer<IGlobalState, IAction>>(reducer, GlobalState);

  return (
    <GlobalContext.Provider value={{ state, dispatch }}>
      <GlobalStyles>
        <OptionsBlock />
        <PhotoViewer />
      </GlobalStyles>
    </GlobalContext.Provider>
  );
}

export default App;
