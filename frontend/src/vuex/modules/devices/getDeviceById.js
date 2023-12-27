import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
    getDeviceData: "",
    loading: false,
    error: null,
  });

const actions = {
    async getDeviceById({ commit }, deviceId) {
        try {
            commit('getDeviceBegin');
            const query = await DataService.get(`/api/device/${deviceId}`);
            commit('getDeviceSuccess', query.data);
        } catch (err) {
            commit('getDeviceErr', err);
        }
    },
};

export default { 
    namespaced: false,
    actions,
    mutations,
    state,
};
