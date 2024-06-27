import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/CompileCourseApp/';

export const endpoints = {
    'courses': '/api/courses/', 
    'outlines': '/api/outlines/',
    'details': (outlineId) => `/api/outlines/${outlineId}/`,
    'outline_comment': (outlineId) => `/api/outlines/${outlineId}/comments/`,
    'outlineMethod': '/api/outlinemethods/',
    'register': '/api/users/',
    'login': '/api/login/',
    'current-user': '/api/current-user/',
    'create-comment': '/api/comments/'
}

export const authApi = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `${cookie.load('token')}`
        }
    });
}

export default axios.create({
    baseURL: BASE_URL
});