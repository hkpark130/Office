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
  
  getDeviceBegin(state) {
    state.loading = true;
  },
  getDeviceSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  getDeviceErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  postDeviceApplicationBegin(state) {
    state.postDeviceApplicationLoading = true;
  },
  postDeviceApplicationSuccess(state, data) {
    state.postDeviceApplicationLoading = false;
    state.posts = data;
  },
  postDeviceApplicationErr(state, err) {
    state.postDeviceApplicationLoading = false;
    state.error = err;
  },

  postDevicePurchaseBegin(state) {
    state.loading = true;
  },
  postDevicePurchaseSuccess(state, data) {
    state.loading = false;
    state.posts = data;
  },
  postDevicePurchaseErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  postAddDeviceBegin(state) {
    state.loading = true;
  },
  postAddDeviceSuccess(state, data) {
    state.loading = false;
    state.posts = data;
  },
  postAddDeviceErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  checkDuplicationBegin(state) {
    state.loading = true;
  },
  checkDuplicationSuccess(state, data) {
    state.loading = false;
    state.duplicationCheck = data;
  },
  checkDuplicationErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
