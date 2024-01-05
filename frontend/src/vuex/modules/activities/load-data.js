import { DataService } from '@/config/dataService/dataService';

const getActivities = await DataService.get('/api/activities');

export { getActivities };
