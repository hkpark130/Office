export default {
  filterCatrgoryBegin(state) {
    state.loading = true;
  },
  filterCatrgorySuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterCatrgoryErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  postAddCategoryBegin(state) {
    state.loading = true;
  },
  postAddCategorySuccess(state, data) {
    state.loading = false;
    state.posts = data;
  },
  postAddCategoryErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  fetchCategoryListBegin(state) {
    state.loading = true;
  },
  fetchCategoryListSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  fetchCategoryListErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
