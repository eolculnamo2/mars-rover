import React from 'react';
import {
  render
} from '@testing-library/react';
import OptionsBlock from '.';
import GlobalContext from '../../contexts/global';


const setup = (rover = '') => {
  const container = render(
    <GlobalContext.Provider value={{ state: { rover } , dispatch: () =>  null}}>
      <OptionsBlock/>
    </GlobalContext.Provider>
  );

  const submitBtn = container.getByTestId('submit-btn');
  const opportunityBtn = container.getByTestId('opportunity-rover-btn');

  return {
    ...container,
    submitBtn,
    opportunityBtn
  };
};

describe('Options Block', () => {
  it('Display Download and View on load', () => {
    const { submitBtn } = setup();
    expect(submitBtn.innerHTML).toBe("Download and View");
  });
  it('Rover buttons change color when selected', () => {
    const { opportunityBtn } = setup('opportunity');
    expect(getComputedStyle(opportunityBtn).getPropertyValue('background-color')).toBe('rgb(49, 191, 185)');
  });
  it('Rover buttons should not change color when not selected', () => {
    const { opportunityBtn } = setup('spirit');
    expect(getComputedStyle(opportunityBtn).getPropertyValue('background-color')).toBe('rgb(218, 218, 218)');
  })
});
