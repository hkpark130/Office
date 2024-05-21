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

  fetchDepartmentListBegin(state) {
    state.loading = true;
  },
  fetchDepartmentListSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  fetchDepartmentListErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  putEditDepartmentBegin(state) {
    state.loading = true;
  },
  putEditDepartmentSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  putEditDepartmentErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  postAddDepartmentBegin(state) {
    state.loading = true;
  },
  postAddDepartmentSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  postAddDepartmentErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  deleteDepartmentBegin(state) {
    state.loading = true;
  },
  deleteDepartmentSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  deleteDepartmentErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
