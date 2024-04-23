import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
  data: "",
  loading: false,
  error: null,
});

const actions = {
  async myListFilter({ commit }, { column, value, name }) {
    try {
      commit('filterMyListBegin');
      const query = await DataService.get(`/api/my-approval-list/${name}`);
      const data = query.data.filter((item) => {
        if (value !== '') {
          if (item[column] === null){
            return false;
          }
          return String(item[column]).includes(value);
        }
        return item;
      });
      commit('filterMyListSuccess', data);
    } catch (err) {
      console.error(err);
      commit('filterMyListErr', err);
    }
  },

  async getMyApproval({ commit }, name) {
    try {
      commit('getMyApprovalBegin');
      const query = await DataService.get(`/api/my-approval-list/${name}`);
      commit('getMyApprovalSuccess', query.data);
      return query.data;
    } catch (err) {
      console.log(err);
      commit('getMyApprovalErr', err);
    }
  },

  async approvalDeviceCancel({ commit }, approvalId) {
    try {
      commit('approvalDeviceCancelBegin');
      await DataService.delete(`/api/approval-device-cancel/${approvalId}`);
      commit('approvalDeviceCancelSuccess');
    } catch (err) {
      commit('approvalDeviceCancelErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
