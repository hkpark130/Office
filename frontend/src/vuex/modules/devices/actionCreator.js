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

  async submitDeviceDisposePost({ commit }, data) {
    try {
      commit('postDeviceDisposeBegin');
      await DataService.post('/api/device-dispose', data);
      commit('postDeviceDisposeSuccess', data);
    } catch (err) {
      commit('postDeviceDisposeErr', err);
    }
  },

  //submitDeviceDispose

  async adminReturnDevice({ commit }, approvalId) {
    try {
      commit('adminReturnDeviceBegin');
      await DataService.get(`/api/admin-device-return/${approvalId}`);
      commit('adminReturnDeviceSuccess', approvalId);
    } catch (err) {
      commit('adminReturnDeviceErr', err);
    }
  },

  async adminDisposeDevice({ commit }, deviceId) {
    try {
      commit('adminDisposeDeviceBegin');
      await DataService.get(`/api/admin-device-dispose/${deviceId}`);
      commit('adminDisposeDeviceSuccess', deviceId);
    } catch (err) {
      commit('adminDisposeDeviceErr', err);
    }
  },

  async adminRecoveryDevice({ commit }, deviceId) {
    try {
      commit('adminRecoveryDeviceBegin');
      await DataService.get(`/api/admin-device-recovery/${deviceId}`);
      commit('adminRecoveryDeviceSuccess', deviceId);
    } catch (err) {
      commit('adminRecoveryDeviceErr', err);
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
