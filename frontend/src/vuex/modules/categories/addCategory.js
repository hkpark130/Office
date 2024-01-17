import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
    loading: false,
    error: null,
  });

const actions = {
    async submitAddCategoryPost({ commit }, data) {
      try {
        commit('postAddCategoryBegin');
        await DataService.post('/api/add-category', data);
        commit('postAddCategorySuccess', data);
      } catch (err) {
        commit('postAddCategoryErr', err);
      }
    },
};

export default { 
    namespaced: false,
    actions,
    mutations,
    state,
};
