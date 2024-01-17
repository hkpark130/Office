import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
  loading: false,
  error: null,
});

const actions = {
  async submitEditDevicePut({ commit }, data) {
    try {
      commit('putEditDeviceBegin');
      await DataService.put('/api/edit-device', data);
      commit('putEditDeviceSuccess', data);
    } catch (err) {
      commit('putEditDeviceErr', err);
    }
  },
};

export default { 
  namespaced: false,
  actions,
  mutations,
  state,
};
