import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
  data: "",
  loading: false,
  error: null,
});

const actions = {
  async myDeviceFilter({ commit }, { column, value, name }) {
    try {
      commit('filterMyDeviceBegin');
      const query = await DataService.get(`/api/mydevice/${name}`);
      const data = query.data.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterMyDeviceSuccess', data);
    } catch (err) {
      commit('filterMyDeviceErr', err);
    }
  },

  async getMyDevices({ commit }, name) {
    try {
      commit('getMyDevicesBegin');
      const query = await DataService.get(`/api/mydevice/${name}`);
      commit('getMyDevicesSuccess', query.data);
      return query.data;
    } catch (err) {
      console.log(err);
      commit('getMyDevicesErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
