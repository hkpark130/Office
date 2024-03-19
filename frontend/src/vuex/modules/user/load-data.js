import { DataService } from '@/config/dataService/dataService';
import Cookies from 'js-cookie';

async function checkLoginStatus() {
    const loggedIn = Cookies.get('loggedIn');
    if (loggedIn) {
        try {
            const user = await DataService.get('/api/user');
            return user;
        } catch (error) {
            console.error('Failed to fetch user information:', error);
            throw error;
        }
    } else {
        return window.location.href = '/login';
    }
}

const getUser = await checkLoginStatus();

export { getUser };
