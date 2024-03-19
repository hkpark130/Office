import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
  data: null,
  loading: false,
  error: null,
});

const actions = {
  async catrgoryFilter({ commit }, { column, value, response }) {
    try {
      commit('filterCatrgoryBegin');
      const data = response.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterCatrgorySuccess', data);
    } catch (err) {
      commit('filterCatrgoryErr', err);
    }
  },

  async fetchCategoryList({ commit }) {
    try {
      commit('fetchCategoryListBegin');
      const catrgoryList = await DataService.get('/api/catrgory-list');
      commit('fetchCategoryListSuccess', catrgoryList.data);
      return catrgoryList.data;
    } catch (err) {
      commit('fetchCategoryListErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
