import { DataService } from '@/config/dataService/dataService';

const availableDeviceList = await DataService.get('/api/available-devicelist');

export { availableDeviceList };
