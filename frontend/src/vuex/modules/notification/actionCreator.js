import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
  data: "",
  loading: false,
  error: null,
});

const actions = {
  async getNotifications({ commit }, name) {
    try {
      commit('getNotificationsBegin');
      const query = await DataService.get(`/get-notifications/${name}`);
      commit('getNotificationsSuccess', query.data);
    } catch (err) {
      commit('getNotificationsErr', err);
      throw new Error(err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
