import mutations from './mutations';
import { deviceListAdmin } from './load-data';

const response = deviceListAdmin.data;

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async orderFilter({ commit }, { column, value }) {
    try {
      commit('filterOrderBegin');
      const data = response.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterOrderSuccess', data);
    } catch (err) {
      commit('filterOrderErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
