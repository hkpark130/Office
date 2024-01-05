export default {
  keycloakLoginBegin(state) {
    state.loading = true;
  },

  keycloakLoginSuccess(state, uid) {
    state.loading = false;
    state.isLogin = true;
    state.uid = uid;
  },

  keycloakLoginErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  keycloakLogOutBegin(state) {
    state.loading = true;
  },

  keycloakLogOutSuccess(state) {
    state.loading = false;
    state.isLogout = true;
    state.isLogin = false;
  },

  keycloakLogOutErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
