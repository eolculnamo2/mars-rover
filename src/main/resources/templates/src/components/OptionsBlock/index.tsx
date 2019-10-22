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
  const [loading, setLoading] = useState<boolean>(false);

  async function downloadAndView() {
    setLoading(true);
    await downloadAndSaveImages(cam, sol, rover);
    const imgSrc: string = 'data:image/jpg;base64, ' + (await getImage(cam, sol, rover, 1));
    dispatch({ type: ACTION_TYPES.SET_INITIAL_PHOTO, payload: imgSrc });
    setLoading(false);
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
          data-testid="opportunity-rover-btn"
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
      <SubmitButton
        data-testid="submit-btn"
        disabled={!rover || loading}
        enabled={!!rover}
        onClick={downloadAndView}
      >
        {loading ? 'Downloading Images...' : 'Download and View'}
      </SubmitButton>
    </OptionsWrapper>
  );
};

export default OptionsBlock;
