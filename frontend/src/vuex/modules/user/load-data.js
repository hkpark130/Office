import { DataService } from '@/config/dataService/dataService';
import Cookies from 'js-cookie';

async function checkLoginStatus() {
    const loggedIn = Cookies.get('loggedIn');
    const API_ENDPOINT = process.env.VUE_APP_API_ENDPOINT;
    if (loggedIn) {
        try {
            const user = await DataService.get('/api/user');
            return user;
        } catch (error) {
            console.error('Failed to fetch user information:', error);
            throw error;
        }
    } else {
        return window.location.href = API_ENDPOINT+'/login/back-office-api';
    }
}

const getUser = await checkLoginStatus();

export { getUser };
