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
            throw new Error(err);
        }
    },

    async getApprovalByDeviceId({ commit }, deviceId) {
      try {
          commit('getApprovalBeginByDeviceId');
          const query = await DataService.get(`/api/approval-device/device/${deviceId}`);
          commit('getApprovalSuccessByDeviceId', query.data);
          return query.data;
      } catch (err) {
          commit('getApprovalErrByDeviceId', err);
          throw new Error(err);
      }
    },

    async approvalDeviceFinishPost({ commit }, data) {
        try {
          commit('approvalDeviceFinishBegin');
          await DataService.post('/api/approval-device-finish', data);
          commit('approvalDeviceFinishSuccess', data);
        } catch (err) {
          commit('approvalDeviceFinishErr', err);
          throw new Error(err);
        }
    },

    async EditApprovalPut({ commit }, data) {
        try {
          commit('editApprovalPutBegin');
          await DataService.put('/api/approval-device-edit', data);
          commit('editApprovalPutSuccess');
        } catch (err) {
          commit('editApprovalPutErr', err);
          throw new Error(err);
        }
    },

    async approvalDeviceReturnPost({ commit }, data) {
        try {
          commit('approvalDeviceReturnBegin');
          await DataService.post('/api/approval-device-return', data);
          commit('approvalDeviceReturnSuccess', data);
        } catch (err) {
          commit('approvalDeviceReturnErr', err);
          throw new Error(err);
        }
    },

};

export default { 
    namespaced: false,
    actions,
    mutations,
    state,
};
