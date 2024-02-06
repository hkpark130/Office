import mutations from './mutations';

const state = () => ({
  data: null,
  loading: false,
  error: null,
});

const actions = {
  async disposeDeviceFilter({ commit }, { column, value, response }) {
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
