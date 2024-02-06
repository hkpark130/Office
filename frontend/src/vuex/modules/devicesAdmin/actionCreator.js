import mutations from './mutations';

const state = () => ({
  data: null,
  loading: false,
  error: null,
});

const actions = {
  async deviceAdminFilter({ commit }, { column, value, response }) {
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
