import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
  data: null,
  loading: false,
  error: null,
});

const actions = {  
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
