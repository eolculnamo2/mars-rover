import styled from 'styled-components';
import { ROVER_TEAL } from '../../shared/styled-variables';

export const ViewerWrap = styled.div`
  margin: 1em auto;
  width: 500px;
  overflow: hidden;
  background-color: white;
  padding: 1em;
  box-sizing: border-box;
  border-radius: 4px;
`;

export const ButtonWrap = styled.div`
  display: flex;
  justify-content: space-between;
  text-align: center;
  margin: 1em 0;
`;

export const NavigateBtn = styled.div`
  width: 100px;
  border: 1px solid ${ROVER_TEAL};
  cursor: pointer;
  border-radius: 4px;
  color: black;
  text-align: center;
  transition: all 0.15s ease-in;

  &:hover {
    background-color: ${ROVER_TEAL};
    color: white;
  }
`;

export const Photo = styled.img`
  width: 100%;
  border-radius: 4px;
`;
