import { DataService } from '@/config/dataService/dataService';

const getUser = await DataService.get('/api/user');

export { getUser };
