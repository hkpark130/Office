export default [
  {
    path: '/',
    alias: ['/index.html'],
    name: 'devices',
    component: () => import(/* webpackChunkName: "DeviceList" */ '@/view/pages/DeviceList.vue'),
  },
  {
    path: '/deviceList',
    name: 'devicelist',
    component: () => import(/* webpackChunkName: "AvailableDeviceList" */ '@/view/devices/AvailableDeviceList.vue'),
  },
  {
    name: 'device-application',
    path: '/device-application/:deviceId',
    props: true,
    component: () => import(/* webpackChunkName: "DeviceApplication" */ '@/view/pages/DeviceApplication.vue'),
  },
  {
    name: 'approval-process',
    path: '/approval-process',
    props: true,
    component: () => import(/* webpackChunkName: "ApprovalProcess" */ '@/view/pages/ApprovalProcess.vue'),
  },
  {
    name: 'purchase',
    path: '/purchase',
    component: () => import(/* webpackChunkName: "Purchase" */ '@/view/pages/Purchase.vue'),
  },
  {
    path: '/user/myList',
    name: 'myList',
    component: () => import(/* webpackChunkName: "MyList" */ '@/view/user/MyList.vue'),
  },
  
  {
    path: '/blank',
    name: 'starter',
    component: () => import(/* webpackChunkName: "Blank" */ '@/view/pages/BlankPage.vue'),
  },
  {
    name: 'add-approval',
    path: '/add-approval',
    component: () => import(/* webpackChunkName: "AddApproval" */ '@/view/pages/AddApproval.vue'),
  },
  {
    name: 'add-device',
    path: '/add-device',
    component: () => import(/* webpackChunkName: "AddDevice" */ '@/view/pages/AddDevice.vue'),
  },
  {
    name: 'add-device-list',
    path: '/add-device-list',
    component: () => import(/* webpackChunkName: "AddDevice" */ '@/view/pages/AddDeviceList.vue'),
  },
  {
    name: 'deviceListForAdmin',
    path: '/devicelist-admin',
    component: () => import(/* webpackChunkName: "DeviceListAdmin" */ '@/view/devices/DeviceListAdmin.vue'),
  },
  {
    name: 'disposeDeviceListForAdmin',
    path: '/dispose-devicelist-admin',
    component: () => import(/* webpackChunkName: "DisposeDeviceListAdmin" */ '@/view/devices/DisposeDeviceListAdmin.vue'),
  },
  {
    name: 'activity',
    path: '/activity',
    component: () => import(/* webpackChunkName: "Activity" */ '@/view/pages/Activity.vue'),
  },
  {
    name: 'myDevice',
    path: '/user/myDevice',
    component: () => import(/* webpackChunkName: "MyDevice" */ '@/view/user/MyDevice.vue'),
  },
  
  {
    name: 'forbidden',
    path: '/403',
    component: () => import(/* webpackChunkName: "403" */ '@/view/pages/403.vue'),
  },
  {
    path: '/:catchAll(.*)',
    component: () => import(/* webpackChunkName: "404" */ '@/view/pages/404.vue'),
  },
];
