export default {
  getActivitiesBegin(state) {
    state.loading = true;
  },
  getActivitiesSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  getActivitiesErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  fetchActivitiesBegin(state) {
    state.loading = true;
  },
  fetchActivitiesSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  fetchActivitiesErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
