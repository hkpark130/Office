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

  downloadAvailableDeviceListBegin(state) {
    state.loading = true;
  },
  downloadAvailableDeviceListSuccess(state) {
    state.loading = false;
  },
  downloadAvailableDeviceListErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
