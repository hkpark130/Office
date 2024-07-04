import { DataService } from '@/config/dataService/dataService';

const ldapUserList = await DataService.get('/api/ldap-users');

export { ldapUserList };
