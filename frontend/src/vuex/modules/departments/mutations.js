export default {
  filterDepartmentBegin(state) {
    state.loading = true;
  },
  filterDepartmentSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterDepartmentErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
