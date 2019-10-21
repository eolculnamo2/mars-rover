import axios, { AxiosRequestConfig } from 'axios';

async function getRequest(url: string) {
  try {
    const config: AxiosRequestConfig = {
      responseType: 'blob',
    };
    const response = await axios.get(url);
    return response;
  } catch (e) {
    console.log(e);
    alert('An error has occurred :(');
  }
}

async function postRequest(url: string, payload: string) {
  try {
    const config: AxiosRequestConfig = {
      headers: { 'Content-Type': 'application/json' },
    };
    const response = await axios.post(url, payload, config);
  } catch (e) {
    console.log(e);
    alert('An error has occurred :(');
  }
}

export async function downloadAndSaveImages(cam: string, sol: number, rover: string) {
  const payload = { cam, sol, rover };
  return await postRequest('/rovers/save-rovers', JSON.stringify(payload));
}

export async function getImage(cam: string, sol: number, rover: string, index: number) {
  const response = await getRequest(
    `/rovers/get-rover?cam=${cam}&sol=${sol}&rover=${rover}&index=${index}`
  );
  return response ? response.data : null;
}
