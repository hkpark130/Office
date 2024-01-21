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

  editMyDeviceBegin(state) {
    state.loading = true;
  },
  editMyDeviceSuccess(state) {
    state.loading = false;
  },
  editMyDeviceErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  getMyDevicesBegin(state) {
    state.loading = true;
  },
  getMyDevicesSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  getMyDevicesErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
