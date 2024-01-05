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

  async fetchAvailableDeviceList({ commit }) {
    try {
      commit('fetchAvailableDeviceListBegin');
      const availableDeviceList = await DataService.get('/api/available-devicelist');
      commit('fetchAvailableDeviceListSuccess', availableDeviceList.data);
      return availableDeviceList.data;
    } catch (err) {
      commit('fetchAvailableDeviceListErr', err);
    }
  },

};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
