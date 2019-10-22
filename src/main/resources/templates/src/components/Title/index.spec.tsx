import React from 'react';
import {
  render
} from '@testing-library/react';
import { Title } from '..';


const setup = () => {
  const container = render(<Title/>);

  const titleText = container.getByTestId('title-text');

  return {
    ...container,
    titleText
  };
};

describe('Title Block', () => {
  it('Title should display', () => {
    const { titleText } = setup();
    expect(titleText).toBeInTheDOM();
  });
});
