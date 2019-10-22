import React, { FC, useContext } from 'react';
import GlobalContext from '../../contexts/global';
import { ViewerWrap, NavigateBtn, Photo, ButtonWrap } from './style';
import { ACTION_TYPES } from '../../contexts/global/globalActions';
import { getImage } from '../../shared/api';

const PhotoViewer: FC = (): JSX.Element => {
  const { state, dispatch } = useContext(GlobalContext);
  const { cam, sol, rover, photoSrc, photoIndex } = state;

  async function changePhoto(change: number) {
    const newIndex = photoIndex + change;
    const imgData = await getImage(cam, sol, rover, newIndex);
    if (imgData) {
      const imgSrc: string = 'data:image/jpg;base64, ' + imgData;
      dispatch({ type: ACTION_TYPES.SET_INDEX, payload: newIndex });
      dispatch({ type: ACTION_TYPES.SET_PHOTO, payload: imgSrc });
    }
  }

  if (!photoSrc.length) return <></>;
  return (
    <ViewerWrap>
      <ButtonWrap>
        <NavigateBtn onClick={() => changePhoto(-1)}>Back</NavigateBtn>
        <NavigateBtn onClick={() => changePhoto(1)}>Next</NavigateBtn>
      </ButtonWrap>
      <Photo src={photoSrc} />
    </ViewerWrap>
  );
};

export default PhotoViewer;
