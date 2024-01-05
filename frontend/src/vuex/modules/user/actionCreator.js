import mutations from './mutations';
// import Cookies from 'js-cookie';
import { getUser } from './load-data';

const response = getUser.data;
const hasAdminGroup = getUser.data.attributes.groups.some(group => group === '/Admin');

const state = () => ({
  data: response,
  isAdmin: hasAdminGroup,
  // login: Cookies.get('logedIn'),
  loading: false,
  error: null,
});

const actions = {
  async getUser({ commit }) {
    try {
      commit('setUserBegin');
      commit('setUserSuccess', response);
    } catch (err) {
      commit('setUserErr', err);
    }
  },
  // async login({ commit }) {
  //   try {
  //     commit('loginBegin');
  //     Cookies.set('logedIn', true);
  //     return commit('loginSuccess', true);
  //   } catch (err) {
  //     commit('loginErr', err);
  //   }
  // },
  // async logOut({ commit }) {
  //   try {
  //     commit('logoutBegin');
  //     Cookies.remove('logedIn');
  //     commit('logoutSuccess', null);
  //   } catch (err) {
  //     commit('logoutErr', err);
  //   }
  // },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
