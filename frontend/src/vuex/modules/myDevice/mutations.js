export default {
  filterMyDeviceBegin(state) {
    state.loading = true;
  },
  filterMyDeviceSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterMyDeviceErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
