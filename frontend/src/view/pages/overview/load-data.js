import { DataService } from '@/config/dataService/dataService';
import { ref } from 'vue';

const response = ref();
await DataService.get('/api/devices').then(
    (v) => {
      response.value = v.data;
    }
);

export { response };
