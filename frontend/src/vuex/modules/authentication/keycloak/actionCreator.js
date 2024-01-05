import mutations from './mutations';
import { notification } from 'ant-design-vue';
import keycloak from './keycloak';

const loginNotificationError = err => {
  notification.error({
    message: err,
  });
};

const state = () => ({
  data: null,
  loading: false,
  isLogout: false,
  isLogin: false,
  error: false,
  isSignUpError: false,
  isSignUpLoading: false,
  uid: false,
});

const actions = {
  async keycloakAuthLogin({ commit }, { data }) {
    try {
      await commit('keycloakLoginBegin');
      await keycloak.auth().signInWithEmailAndPassword(data.username, data.password);
      keycloak.auth().onAuthStateChanged(user => {
        user = user ? user : {};
        commit('keycloakLoginSuccess', user.uid ? user.uid : false);
      });
    } catch (err) {
      loginNotificationError(err.message);
      await commit('keycloakLoginErr', err);
    }
  },

  async keycloakAuthGetUid({ commit }) {
    try {
      await commit('keycloakLoginBegin');
      keycloak.auth().onAuthStateChanged(async user => {
        user = user ? user : {};
        await commit('keycloakLoginSuccess', user.uid ? user.uid : false);
      });
    } catch (err) {
      await commit('keycloakLoginErr', err);
    }
  },

  async keycloakAuthLogout({ commit }, logOutRoot) {
    try {
      await commit('keycloakLogOutBegin');
      await keycloak.auth().signOut();
      await commit('keycloakLogOutSuccess', false);
      await logOutRoot();
    } catch (err) {
      await commit('keycloakLogOutErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
