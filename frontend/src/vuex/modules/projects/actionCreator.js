import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
  data: null,
  loading: false,
  error: null,
});

const actions = {
  async projectFilter({ commit, response }) {
    try {
      commit('filterProjectBegin');
      commit('filterProjectSuccess', response);
    } catch (err) {
      commit('filterProjectErr', err);
    }
  },

  async fetchProjectList({ commit }) {
    try {
      commit('fetchProjectListBegin');
      const projectList = await DataService.get('/api/project-list');
      commit('fetchProjectListSuccess', projectList.data);
      return projectList.data;
    } catch (err) {
      commit('fetchProjectListErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
