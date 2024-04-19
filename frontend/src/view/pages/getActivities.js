import { DataService } from '@/config/dataService/dataService';

const getUserD = await DataService.get('/api/user');
const getActivities = await DataService.get(`/api/activities/${getUserD.data.name}`);

export { 
    getActivities, 
    getUserD,
};
