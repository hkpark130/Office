import { createStore } from 'vuex';
import themeLayout from './modules/themeLayout/actionCreator';
import headerSearchData from './modules/headerSearch/actionCreator';
import auth from './modules/authentication/axios/actionCreator';
import devices from './modules/devices/actionCreator';
import disposeDevicesAdmin from './modules/disposeDevicesAdmin/actionCreator';
import devicesAdmin from './modules/devicesAdmin/actionCreator';
import myList from './modules/myList/actionCreator';

export default createStore({
  modules: {
    themeLayout,
    headerSearchData,
    auth,
    devices,
    devicesAdmin,
    disposeDevicesAdmin,
    myList,
  },
});
