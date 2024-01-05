import { DataService } from '@/config/dataService/dataService';

const response = await DataService.get('/api/devices');

export { response };
