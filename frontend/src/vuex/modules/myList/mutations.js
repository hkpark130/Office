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

  getMyApprovalBegin(state) {
    state.loading = true;
  },
  getMyApprovalSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  getMyApprovalErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  approvalDeviceCancelBegin(state) {
    state.loading = true;
  },
  approvalDeviceCancelSuccess(state) {
    state.loading = false;
  },
  approvalDeviceCancelErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
