export default {
  getApprovalBegin(state) {
    state.loading = true;
  },
  getApprovalSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  getApprovalErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  approvalDeviceFinishBegin(state) {
    state.loading = true;
  },
  approvalDeviceFinishSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  approvalDeviceFinishErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  editApprovalPutBegin(state) {
    state.loading = true;
  },
  editApprovalPutSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  editApprovalPutErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  approvalDeviceReturnBegin(state) {
    state.loading = true;
  },
  approvalDeviceReturnSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  approvalDeviceReturnErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
