import axios from "axios";

// 기본 axios 초기화 함수
function createAxiosInstance() {
    return axios.create({
        withCredentials: true
        , baseURL : 'http://127.0.0.1:8080/bittlebittle'
    })
}

function restApiPost(url, data) {
    return createAxiosInstance().post(url, data);
}

function restApiGets(url) {
    return createAxiosInstance().get(url);
}

function restApiPut(url, data) {
    return createAxiosInstance().post(url, data);
}

function restApiDelete(url) {
    return createAxiosInstance().get(url);
}

export { restApiPost, restApiGets, restApiPut, restApiDelete };