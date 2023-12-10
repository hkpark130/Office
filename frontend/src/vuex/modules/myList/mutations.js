export default {
  filterMyListBegin(state) {
    state.loading = true;
  },
  filterMyListSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterMyListErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
