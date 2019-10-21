import React, { FC, useContext, useState } from 'react';
import {
  Button,
  SubmitButton,
  ButtonBox,
  Input,
  OptionsWrapper,
  FieldWrap,
  Label,
  Select,
  FlexFields,
} from './style';
import GlobalContext from '../../contexts/global';
import { ACTION_TYPES } from '../../contexts/global/globalActions';
import { Title } from '..';
import { roverCams } from '../../shared/constants';
import { downloadAndSaveImages, getImage } from '../../shared/api';

const OptionsBlock: FC = (): JSX.Element => {
  const { state, dispatch } = useContext(GlobalContext);
  const { cam, rover, sol } = state;
  const [img, setImg] = useState();

  async function downloadAndView() {
    await downloadAndSaveImages(cam, sol, rover);
    // left off here.. set to global state and call it in photo viewer block
    setImg('data:image/jpg;base64, ' + (await getImage(cam, sol, rover, 1)));
  }

  return (
    <OptionsWrapper>
      <Title />
      <ButtonBox>
        <Button
          onClick={() => dispatch({ type: ACTION_TYPES.SET_ROVER, payload: 'curiosity' })}
          selected={rover === 'curiosity'}
          type="button"
        >
          Curiosity
        </Button>
        <Button
          onClick={() => dispatch({ type: ACTION_TYPES.SET_ROVER, payload: 'opportunity' })}
          selected={rover === 'opportunity'}
          type="button"
        >
          Opportunity
        </Button>
        <Button
          onClick={() => dispatch({ type: ACTION_TYPES.SET_ROVER, payload: 'spirit' })}
          selected={rover === 'spirit'}
          type="button"
        >
          Spirit
        </Button>
      </ButtonBox>
      <FlexFields>
        <FieldWrap>
          <Label>Select Sol</Label>
          <Input
            onChange={e => dispatch({ type: ACTION_TYPES.SET_SOL, payload: e.target.value })}
            type="number"
            value={sol}
          />
        </FieldWrap>
        <FieldWrap>
          <Label>Select Cam</Label>
          <Select
            onChange={e => dispatch({ type: ACTION_TYPES.SET_CAM, payload: e.target.value })}
            value={cam}
          >
            <option value="all">All</option>
            {roverCams[rover] &&
              roverCams[rover].map((cam: string) => (
                <option key={cam} value={cam}>
                  {cam}
                </option>
              ))}
          </Select>
        </FieldWrap>
      </FlexFields>
      <SubmitButton onClick={downloadAndView}>Download and View</SubmitButton>
      {img && <img src={img} />}
    </OptionsWrapper>
  );
};

export default OptionsBlock;
