export default {
  filterDeviceAdminBegin(state) {
    state.loading = true;
  },
  filterDeviceAdminSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterDeviceAdminErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
