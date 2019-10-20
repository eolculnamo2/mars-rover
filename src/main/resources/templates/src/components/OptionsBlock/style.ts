import styled from 'styled-components';
import { IButton } from '../../rover-styles';
import { ROVER_TEAL, GRAY1, ROVER_PURPLE } from '../../shared/styled-variables';

export const OptionsWrapper = styled.div`
  max-width: 800px;
  margin: auto;
  background-color: white;
  padding: 1em;
  color: black;
  border-radius: 4px;
`;

export const FieldWrap = styled.div`
  margin: auto;
  max-width: 200px;
`;

export const Label = styled.label`
  font-size: 1em;
`;

export const Input = styled.input`
  padding: 0.5em;
  width: 200px;
  outline: none;
  display: block;
  margin: 1em auto;
  margin-top: 0.3em;
  border-radius: 4px;
  border: 1px solid ${GRAY1};

  &:focus {
    border: 1px solid ${ROVER_TEAL};
  }
`;

export const ButtonBox = styled.div`
  max-width: 800px;
  margin: auto;
  display: flex;
  justify-content: center;
`;

export const Button = styled.button<IButton>`
  background-color: ${({ selected }) => (selected ? ROVER_TEAL : GRAY1)};
  outline: none;
  padding: 1em 0.5em;
  margin-right: 1em;
  min-width: 200px;
  cursor: pointer;
  border: 0;
  border-radius: 4px;
`;

export const SubmitButton = styled.button`
  background-color: ${ROVER_PURPLE};
  outline: none;
  padding: 1em 0.5em;
  margin-right: 1em;
  width: 500px;
  cursor: pointer;
  display: block;
  margin: 1em auto;
  color: white;
  font-weight: 600;
  font-size: 1em;
  border-radius: 4px;
  border: 0;
`;
