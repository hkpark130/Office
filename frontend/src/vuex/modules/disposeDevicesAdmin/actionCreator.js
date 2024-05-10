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
          if (item[column] === null){
            return false;
          }
          return String(item[column]).includes(value);
        }
        return item;
      });
      commit('filterDisposeDeviceSuccess', data);
    } catch (err) {
      commit('filterDisposeDeviceErr', err);
    }
  },

  async downloadDisposeDeviceList({ commit }) {
    const API_ENDPOINT = process.env.VUE_APP_API_ENDPOINT;
    try {
      commit('downloadDisposeDeviceListBegin');
      window.open(API_ENDPOINT+'/api/download-dispose-devicelist', '_blank');
      commit('downloadDisposeDeviceListSuccess');
      return;
    } catch (err) {
      commit('downloadDisposeDeviceListErr', err);
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
