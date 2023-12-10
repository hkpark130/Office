import mutations from './mutations';
import { deviceListAdmin } from './load-data';

const response = deviceListAdmin.data;

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async deviceAdminFilter({ commit }, { column, value }) {
    try {
      commit('filterDeviceAdminBegin');
      const data = response.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterDeviceAdminSuccess', data);
    } catch (err) {
      commit('filterDeviceAdminErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
