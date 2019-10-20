import styled from 'styled-components';
import { DARK1 } from './shared/styled-variables';

export const GlobalStyles = styled.div`
  padding: 1em;
  font-family: roboto;
  background-color: ${DARK1};
  min-height: 100vh;
  min-width: 100vw;
  color: white;
  box-sizing: border-box;
  text-rendering: geometricPrecision;
`;
