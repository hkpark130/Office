import { DataService } from '@/config/dataService/dataService';

const getNotis = await DataService.get('/api/user');

export { getNotis };
