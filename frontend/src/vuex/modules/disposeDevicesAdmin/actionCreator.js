import mutations from './mutations';
import { disposeDeviceListAdmin } from './load-data';

const response = disposeDeviceListAdmin.data;

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async disposeDeviceFilter({ commit }, { column, value }) {
    try {
      commit('filterDisposeDeviceBegin');
      const data = response.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterDisposeDeviceSuccess', data);
    } catch (err) {
      commit('filterDisposeDeviceErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
