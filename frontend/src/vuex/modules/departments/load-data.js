import { DataService } from '@/config/dataService/dataService';

const departmentList = await DataService.get('/api/department-list');

export { departmentList };
