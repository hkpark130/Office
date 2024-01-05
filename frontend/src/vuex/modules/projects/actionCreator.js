import mutations from './mutations';
import { projectList } from './load-data';

const response = projectList.data;

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async projectFilter({ commit }) {
    try {
      commit('filterProjectBegin');
      commit('filterProjectSuccess', response);
    } catch (err) {
      commit('filterProjectErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
