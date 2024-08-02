// local vue api axios instance
import axios from 'axios';

function $axios() {
  const instance = axios.create({
    baseURL: 'http://192.168.1.13:8090/api/v1',
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
    },
   
  });
  return instance;
}

export { $axios };