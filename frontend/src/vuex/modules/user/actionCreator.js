import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';
import Cookies from 'js-cookie';

const state = () => ({
  data: null,
  isAdmin: null,
  login: Cookies.get('loggedIn'),
  loading: false,
  error: null,
});

const actions = {
  async getUser({ commit }) {
    const loggedIn = Cookies.get('loggedIn');
    if (loggedIn) {
      try {
        commit('getUserBegin');
        const getUser = await DataService.get('/api/user');
        commit('getUserSuccess', getUser.data);
        return getUser.data;
      } catch (err) {
        commit('getUserErr', err);
      }
    } else {
      return window.location.href = 'http://192.168.0.5/login/back-office-api';
    }
  },
  // async login({ commit }) {
  //   try {
  //     commit('loginBegin');
  //     Cookies.set('loggedIn', true);
  //     return commit('loginSuccess', true);
  //   } catch (err) {
  //     commit('loginErr', err);
  //   }
  // },
  // async logOut({ commit }) {
  //   try {
  //     commit('logoutBegin');
  //     Cookies.remove('loggedIn');
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
