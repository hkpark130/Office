import mutations from './mutations';
import { getActivities } from './load-data';
import { DataService } from '@/config/dataService/dataService';

const response = getActivities.data;

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async getActivities({ commit }) {
    try {
      commit('getActivitiesBegin');
      commit('getActivitiesSuccess', response);
    } catch (err) {
      commit('getActivitiesErr', err);
    }
  },
  
  async fetchActivities({ commit }) {
    try {
      commit('fetchActivitiesBegin');
      const getActivities = await DataService.get('/api/activities');
      commit('fetchActivitiesSuccess', getActivities.data);
    } catch (err) {
      commit('fetchActivitiesErr', err);
    }
  },

  
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
