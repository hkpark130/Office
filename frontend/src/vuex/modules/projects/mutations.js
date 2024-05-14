export default {
  filterProjectBegin(state) {
    state.loading = true;
  },
  filterProjectSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterProjectErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  fetchProjectListBegin(state) {
    state.loading = true;
  },
  fetchProjectListSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  fetchProjectListErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  getProjectByCodeBegin(state) {
    state.loading = true;
  },
  getProjectByCodeSuccess(state, data) {
    state.loading = false;
    state.duplicationCheck = data;
  },
  getProjectByCodeErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  putEditProjectBegin(state) {
    state.loading = true;
  },
  putEditProjectSuccess(state, data) {
    state.loading = false;
    state.duplicationCheck = data;
  },
  putEditProjectErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  postAddProjectBegin(state) {
    state.loading = true;
  },
  postAddProjectSuccess(state, data) {
    state.loading = false;
    state.duplicationCheck = data;
  },
  postAddProjectErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  deleteProjectBegin(state) {
    state.loading = true;
  },
  deleteProjectSuccess(state, data) {
    state.loading = false;
    state.duplicationCheck = data;
  },
  deleteProjectErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
