import { createStore } from 'vuex';
import themeLayout from './modules/themeLayout/actionCreator';
import headerSearchData from './modules/headerSearch/actionCreator';
import auth from './modules/authentication/axios/actionCreator';
import devices from './modules/devices/actionCreator';
import devicePurchase from './modules/devices/devicePurchase';
import deviceById from './modules/devices/getDeviceById';
import addDevice from './modules/devices/addDevice';
import disposeDevicesAdmin from './modules/disposeDevicesAdmin/actionCreator';
import devicesAdmin from './modules/devicesAdmin/actionCreator';
import myList from './modules/myList/actionCreator';
import myDevice from './modules/myDevice/actionCreator';
import caregoryList from './modules/categories/actionCreator';
import projectList from './modules/projects/actionCreator';
import departmentList from './modules/departments/actionCreator';
import getUser from './modules/user/actionCreator';
import getNotifications from './modules/notification/actionCreator';
import getActivities from './modules/activities/actionCreator';
import approvals from './modules/approvals/actionCreator';
import comments from './modules/comments/actionCreator';
import addCategory from './modules/categories/addCategory';

export default createStore({
  modules: {
    themeLayout,
    headerSearchData,
    auth,
    devices,
    devicePurchase,
    devicesAdmin,
    disposeDevicesAdmin,
    myList,
    myDevice,
    caregoryList,
    deviceById,
    projectList,
    departmentList,
    getUser,
    addDevice,
    getNotifications,
    getActivities,
    approvals,
    comments,
    addCategory,
  },
});
