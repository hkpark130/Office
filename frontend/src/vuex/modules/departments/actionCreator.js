import mutations from './mutations';
import { departmentList } from './load-data';

const response = departmentList.data;

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async departmentFilter({ commit }) {
    try {
      commit('filterDepartmentBegin');
      commit('filterDepartmentSuccess', response);
    } catch (err) {
      commit('filterDepartmentErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
