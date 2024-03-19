import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
  data: null,
  loading: false,
  error: null,
});

const actions = {  
  async fetchActivities({ commit }, username) {
    try {
      commit('fetchActivitiesBegin');
      const getActivities = await DataService.get(`/api/activities/${username}`);
      commit('fetchActivitiesSuccess', getActivities.data);
    } catch (err) {
      commit('fetchActivitiesErr', err);
    }
  },

  async deleteNotification({ commit }, NotiId) {
    try {
      commit('deleteNotificationBegin');
      await DataService.delete(`/api/activity/${NotiId}`);
      commit('deleteNotificationSuccess');
    } catch (err) {
      commit('deleteNotificationErr', err);
    }
  },

};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
