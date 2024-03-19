import mutations from './mutations';
import Cookies from 'js-cookie';

const state = () => ({
  login: Cookies.get('loggedIn'),
  loading: false,
  error: null,
});

const actions = {
  async login({ commit }) {
    try {
      commit('loginBegin');
      Cookies.set('loggedIn', true);
      return commit('loginSuccess', true);
    } catch (err) {
      commit('loginErr', err);
    }
  },
  async logOut({ commit }) {
    try {
      commit('logoutBegin');
      // Cookies.remove('loggedIn');
      return commit('logoutSuccess', null);
    } catch (err) {
      commit('logoutErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
