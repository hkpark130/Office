import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
    loading: false,
    error: null,
    duplicationCheck: false,
  });

const actions = {
    async submitAddDevicePost({ commit }, data) {
      try {
        commit('postAddDeviceBegin');
        await DataService.post('/api/add-device', data);
        commit('postAddDeviceSuccess', data);
      } catch (err) {
        commit('postAddDeviceErr', err);
      }
    },
    async checkDuplication({ commit }, deviceId) {
      try {
        commit('checkDuplicationBegin');
        const query = await DataService.get(`/api/check-device-id/${deviceId}`);
        commit('checkDuplicationSuccess', query.data);
        return query.data;
      } catch (err) {
        console.log(err);
        commit('checkDuplicationErr', err);
      }
    },
};

export default { 
    namespaced: false,
    actions,
    mutations,
    state,
};
