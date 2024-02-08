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
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
