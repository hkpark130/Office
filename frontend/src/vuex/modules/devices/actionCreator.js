import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';
import { availableDeviceList } from './load-data';

const originData = availableDeviceList.data;
const response = availableDeviceList.data;

const state = () => ({
  data: response,
  originData: originData,
  loading: false,
  postDeviceApplicationLoading: false,
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

  async submitDeviceApplicationPost({ commit }, data) {
    try {
      commit('postDeviceApplicationBegin');
      await DataService.post('/api/device-application', data);
      commit('postDeviceApplicationSuccess', data);
    } catch (err) {
      commit('postDeviceApplicationErr', err);
    }
  },

  async submitDeviceReturnPost({ commit }, data) {
    try {
      commit('postDeviceReturnBegin');
      await DataService.post('/api/device-return', data);
      commit('postDeviceReturnSuccess', data);
    } catch (err) {
      commit('postDeviceReturnErr', err);
    }
  },

};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
