export default {
  getNotificationsBegin(state) {
    state.loading = true;
  },
  getNotificationsSuccess(state, data) {
    state.loading = false;
    state.duplicationCheck = data;
  },
  getNotificationsErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
