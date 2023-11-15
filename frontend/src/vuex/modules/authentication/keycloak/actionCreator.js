import mutations from './mutations';
import 'firebase/auth'; // for authentication

const state = () => ({
  data: null,
  loading: false,
  isLogout: false,
  isLogin: false,
  error: false,
  isSignUpError: false,
  isSignUpLoading: false,
  uid: false,
});

const actions = {
  async keycloakLogin({ commit }, { data }) {
    console.log(commit);
    alert(data);
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
