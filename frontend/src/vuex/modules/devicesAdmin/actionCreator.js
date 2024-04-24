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
          if (item[column] === null){
            return false;
          }
          return String(item[column]).includes(value);
        }
        return item;
      });
      commit('filterDeviceAdminSuccess', data);
    } catch (err) {
      commit('filterDeviceAdminErr', err);
    }
  },

  async downloadAvailableDeviceList({ commit }) {
    const API_ENDPOINT = process.env.VUE_APP_API_ENDPOINT;
    try {
      commit('downloadAvailableDeviceListBegin');
      window.open(API_ENDPOINT+'/api/download-available-devicelist', '_blank');
      commit('downloadAvailableDeviceListSuccess');
      return;
    } catch (err) {
      commit('downloadAvailableDeviceListErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
