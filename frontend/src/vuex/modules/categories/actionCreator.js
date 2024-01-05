import mutations from './mutations';
import { catrgoryList } from './load-data';

const response = catrgoryList.data;

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async catrgoryFilter({ commit }, { column, value }) {
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
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
