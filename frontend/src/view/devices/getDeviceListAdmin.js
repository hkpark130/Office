import { DataService } from '@/config/dataService/dataService';

const deviceListAdmin = await DataService.get('/api/devicelist-admin');

export { deviceListAdmin };
