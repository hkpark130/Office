export default {
  fetchLdapUserListBegin(state) {
    state.loading = true;
  },
  fetchLdapUserListSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  fetchLdapUserListErr(state, err) {
    state.loading = false;
    state.error = err;
  },
  
  filterLdapUserBegin(state) {
    state.loading = true;
  },
  filterLdapUserSuccess(state, data) {
    state.loading = false;
    state.data = data;
  },
  filterLdapUserErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  postAddLdapUserBegin(state) {
    state.loading = true;
  },
  postAddLdapUserSuccess(state, data) {
    state.loading = false;
    state.posts = data;
  },
  postAddLdapUserErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  cnCheckDuplicationBegin(state) {
    state.loading = true;
  },
  cnCheckDuplicationSuccess(state, data) {
    state.loading = false;
    state.duplicationCheck = data;
  },
  cnCheckDuplicationErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  reIssuePasswordBegin(state) {
    state.loading = true;
  },
  reIssuePasswordSuccess(state, data) {
    state.loading = false;
    state.duplicationCheck = data;
  },
  reIssuePasswordErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  uidNumCheckDuplicationBegin(state) {
    state.loading = true;
  },
  uidNumCheckDuplicationSuccess(state, data) {
    state.loading = false;
    state.duplicationCheck = data;
  },
  uidNumCheckDuplicationErr(state, err) {
    state.loading = false;
    state.error = err;
  },

  deleteLdapUserBegin(state) {
    state.loading = true;
  },
  deleteLdapUserSuccess(state) {
    state.loading = false;
  },
  deleteLdapUserErr(state, err) {
    state.loading = false;
    state.error = err;
  },
};
