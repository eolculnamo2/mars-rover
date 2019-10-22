import React from 'react';
import {
  render
} from '@testing-library/react';
import GlobalContext from '../../contexts/global';
import { PhotoViewer } from '..';


const setup = (photoSrc = '') => {
  const container = render(
    <GlobalContext.Provider value={{ state: { photoSrc } , dispatch: () =>  null}}>
      <PhotoViewer/>
    </GlobalContext.Provider>
  );

  const mainWrap = container.queryByTestId('photoviewer-wrap');

  return {
    ...container,
    mainWrap
  };
};

describe('PhotoViewer block', () => {
  it('Should not display when photoSrc is blank', () => {
    const { mainWrap } = setup();
    expect(mainWrap).not.toBeInTheDOM();
  });
  it('Should  display when photoSrc has data', () => {
    const { mainWrap } = setup('test');
    expect(mainWrap).toBeInTheDOM();
  });
});
