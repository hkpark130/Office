import { DataService } from '@/config/dataService/dataService';

const getUser = await DataService.get('/api/user');
const getMyDevice = await DataService.get(`/api/mydevice/${getUser.data.name}`);

export { getMyDevice };