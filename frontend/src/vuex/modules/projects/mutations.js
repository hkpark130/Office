export default {
  filterProjectBegin(state) {
    state.loading = true;
  },
  filterProjectSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterProjectErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
