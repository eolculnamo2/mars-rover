import React, { FC, useContext } from 'react';
import { Button, SubmitButton, ButtonBox, Input, OptionsWrapper, FieldWrap, Label } from './style';
import GlobalContext from '../../contexts/global';
import { ACTION_TYPES } from '../../contexts/global/globalActions';
import { Title } from '..';

const OptionsBlock: FC = (): JSX.Element => {
  const { state, dispatch } = useContext(GlobalContext);

  return (
    <OptionsWrapper>
      <Title />
      <FieldWrap>
        <Label>Select Sol</Label>
        <Input type="number" />
      </FieldWrap>
      <ButtonBox>
        <Button
          onClick={() => dispatch({ type: ACTION_TYPES.SET_CAM, payload: 'curiosity' })}
          selected={state.cam === 'curiosity'}
          type="button"
        >
          Curiosity
        </Button>
        <Button
          onClick={() => dispatch({ type: ACTION_TYPES.SET_CAM, payload: 'opportunity' })}
          selected={state.cam === 'opportunity'}
          type="button"
        >
          Opportunity
        </Button>
        <Button
          onClick={() => dispatch({ type: ACTION_TYPES.SET_CAM, payload: 'spirit' })}
          selected={state.cam === 'spirit'}
          type="button"
        >
          Spirit
        </Button>
      </ButtonBox>
      <SubmitButton>Download and View</SubmitButton>
    </OptionsWrapper>
  );
};

export default OptionsBlock;
