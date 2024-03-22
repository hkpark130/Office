import { DataService } from '@/config/dataService/dataService';

const getUser = await DataService.get('/api/user');
const getMyList = await DataService.get(`/api/my-approval-list/${getUser.data.name}`);

export { getMyList };