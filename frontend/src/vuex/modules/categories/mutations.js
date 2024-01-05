export default {
  filterCatrgoryBegin(state) {
    state.loading = true;
  },
  filterCatrgorySuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterCatrgoryErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
