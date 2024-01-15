import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
    data: [],
    loading: false,
    error: null,
  });

const actions = {
    async getApprovalById({ commit }, ApprovalId) {
        try {
            commit('getApprovalBegin');
            const query = await DataService.get(`/api/approval-device/${ApprovalId}`);
            commit('getApprovalSuccess', query.data);
        } catch (err) {
            commit('getApprovalErr', err);
        }
    },

    async approvalDeviceFinishPost({ commit }, data) {
        try {
          commit('approvalDeviceFinishBegin');
          await DataService.post('/api/approval-device-finish', data);
          commit('approvalDeviceFinishSuccess', data);
        } catch (err) {
          commit('approvalDeviceFinishErr', err);
        }
    },

    async approvalDeviceReturnPost({ commit }, data) {
        try {
          commit('approvalDeviceReturnBegin');
          await DataService.post('/api/approval-device-return', data);
          commit('approvalDeviceReturnSuccess', data);
        } catch (err) {
          commit('approvalDeviceReturnErr', err);
        }
    },

};

export default { 
    namespaced: false,
    actions,
    mutations,
    state,
};
