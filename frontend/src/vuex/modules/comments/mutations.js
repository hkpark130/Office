export default {
    commentPostBegin(state) {
      state.loading = true;
    },
    commentPostSuccess(state) {
      state.loading = false;
    },
    commentPostErr(state, err) {
      state.loading = false;
      state.error = err;
    },
  
    getCommentBegin(state) {
      state.loading = true;
    },
    getCommentSuccess(state, data) {
      state.loading = false;
      state.data = data;
    },
    getCommentErr(state, err) {
      state.loading = false;
      state.error = err;
    },

    deleteCommentBegin(state) {
      state.loading = true;
    },
    deleteCommentSuccess(state) {
      state.loading = false;
    },
    deleteCommentErr(state, err) {
      state.loading = false;
      state.error = err;
    },
  };
  