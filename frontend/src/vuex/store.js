import { createStore } from 'vuex';
import themeLayout from './modules/themeLayout/actionCreator';
import headerSearchData from './modules/headerSearch/actionCreator';
import auth from './modules/authentication/axios/actionCreator';
import keycloak from './modules/authentication/keycloak/actionCreator';

export default createStore({
  modules: {
    themeLayout,
    headerSearchData,
    auth,
    keycloak,
  },
});
