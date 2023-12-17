import { DataService } from '@/config/dataService/dataService';

const projectList = await DataService.get('/api/project-list');

export { projectList };
