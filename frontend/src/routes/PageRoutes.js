export default [
  {
    path: '/',
    alias: ['/index.html'],
    name: 'index',
    component: () => import(/* webpackChunkName: "DeviceList" */ '@/view/pages/DeviceList.vue'),
  },
  {
    path: '/deviceList',
    name: 'devicelist',
    component: () => import(/* webpackChunkName: "AvailableDeviceList" */ '@/view/devices/AvailableDeviceList.vue'),
  },
  {
    name: 'device-application',
    path: '/device-application',
    props: true,
    component: () => import(/* webpackChunkName: "DeviceApplication" */ '@/view/pages/DeviceApplication.vue'),
  },
  {
    name: 'purchase',
    path: '/purchase',
    component: () => import(/* webpackChunkName: "Purchase" */ '@/view/pages/Purchase.vue'),
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
    name: 'forbidden',
    path: '/403',
    component: () => import(/* webpackChunkName: "403" */ '@/view/pages/403.vue'),
  },
  {
    path: '/:catchAll(.*)',
    component: () => import(/* webpackChunkName: "404" */ '@/view/pages/404.vue'),
  },
];
