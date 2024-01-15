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
  };
  