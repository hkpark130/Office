import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
    loading: false,
    error: null,
  });

const actions = {
    async submitDevicePurchasePost({ commit }, data) {
        try {
          commit('postDevicePurchaseBegin');
          await DataService.post('/api/device-purchase', data);
          commit('postDevicePurchaseSuccess', data);
        } catch (err) {
          commit('postDevicePurchaseErr', err);
        }
    },
};

export default { 
    namespaced: false,
    actions,
    mutations,
    state,
};
