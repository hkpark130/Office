export default {
  filterDisposeDeviceBegin(state) {
    state.loading = true;
  },
  filterDisposeDeviceSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterDisposeDeviceErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
