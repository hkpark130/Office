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

};
