import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
  data: null,
  loading: false,
  error: null,
});

const actions = {
  async departmentFilter({ commit, response }) {
    try {
      commit('filterDepartmentBegin');
      commit('filterDepartmentSuccess', response);
    } catch (err) {
      commit('filterDepartmentErr', err);
    }
  },

  async fetchDepartmentList({ commit }) {
    try {
      commit('fetchDepartmentListBegin');
      const departmentList = await DataService.get('/api/department-list');
      commit('fetchDepartmentListSuccess', departmentList.data);
      return departmentList.data;
    } catch (err) {
      commit('fetchDepartmentListErr', err);
      throw new Error(err);
    }
  },

  async submitEditDepartmentPut({ commit }, data) {
    try {
      commit('putEditDepartmentBegin');
      await DataService.put('/api/edit-department', data);
      commit('putEditDepartmentSuccess', data);
    } catch (err) {
      commit('putEditDepartmentErr', err);
      throw new Error(err);
    }
  },

  async submitAddDepartmentPost({ commit }, data) {
    try {
      commit('postAddDepartmentBegin');
      await DataService.post('/api/add-department', data);
      commit('postAddDepartmentSuccess', data);
    } catch (err) {
      commit('postAddDepartmentErr', err);
      throw new Error(err);
    }
  },

  async deleteDepartment({ commit }, departmentId) {
    try {
      commit('deleteDepartmentBegin');
      await DataService.delete(`/api/department/${departmentId}`);
      commit('deleteDepartmentSuccess');
    } catch (err) {
      commit('deleteDepartmentErr', err);
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
