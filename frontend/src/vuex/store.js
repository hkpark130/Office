import { createStore } from 'vuex';
import themeLayout from './modules/themeLayout/actionCreator';
import headerSearchData from './modules/headerSearch/actionCreator';
import auth from './modules/authentication/axios/actionCreator';
import devices from './modules/devices/actionCreator';

export default createStore({
  modules: {
    themeLayout,
    headerSearchData,
    auth,
    devices,
  },
});
