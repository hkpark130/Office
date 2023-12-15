export default {
  filterDeviceBegin(state) {
    state.loading = true;
  },
  filterDeviceSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterDeviceErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
