import { DataService } from '@/config/dataService/dataService';

const getUser = await DataService.get('/api/user');
const getActivities = await DataService.get(`/api/activities/${getUser.data.name}`);

export { getActivities };
