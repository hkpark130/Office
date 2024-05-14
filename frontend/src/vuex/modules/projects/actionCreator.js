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
      throw new Error(err);
    }
  },

  async getProjectByCode({ commit }, code) {
    try {
      commit('getProjectByCodeBegin');
      const query = await DataService.get(`/api/project/${code}`);
      commit('getProjectByCodeSuccess', query.data);
      return query.data;
    } catch (err) {
      console.log(err);
      commit('getProjectByCodeErr', err);
      throw new Error(err);
    }
  },

  async submitEditProjectPut({ commit }, data) {
    try {
      commit('putEditProjectBegin');
      await DataService.put('/api/edit-project', data);
      commit('putEditProjectSuccess', data);
    } catch (err) {
      commit('putEditProjectErr', err);
      throw new Error(err);
    }
  },

  async submitAddProjectPost({ commit }, data) {
    try {
      commit('postAddProjectBegin');
      await DataService.post('/api/add-project', data);
      commit('postAddProjectSuccess', data);
    } catch (err) {
      commit('postAddProjectErr', err);
      throw new Error(err);
    }
  },

  async deleteProject({ commit }, projectId) {
    try {
      commit('deleteProjectBegin');
      await DataService.delete(`/api/project/${projectId}`);
      commit('deleteProjectSuccess');
    } catch (err) {
      commit('deleteProjectErr', err);
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
