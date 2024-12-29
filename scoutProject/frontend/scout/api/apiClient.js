import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://192.168.15.153:8080/scout',
    timeout: 5000,
})

export default apiClient;