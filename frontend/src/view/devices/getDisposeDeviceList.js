import { DataService } from '@/config/dataService/dataService';

const disposeDeviceListAdmin = await DataService.get('/api/dispose-devicelist-admin');

export { disposeDeviceListAdmin };
