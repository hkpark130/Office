import mutations from './mutations';
import { availableDeviceList } from './load-data';

const response = availableDeviceList.data;

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async deviceFilter({ commit }, { column, value }) {
    try {
      commit('filterDeviceBegin');
      const data = response.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterDeviceSuccess', data);
    } catch (err) {
      commit('filterDeviceErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
