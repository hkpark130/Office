import { DataService } from '@/config/dataService/dataService';

const catrgoryList = await DataService.get('/api/catrgory-list');

export { catrgoryList };
