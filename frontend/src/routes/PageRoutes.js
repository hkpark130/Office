export default [
  {
    path: '/',
    name: 'devicelist',
    component: () => import(/* webpackChunkName: "DeviceList" */ '@/view/pages/DeviceList.vue'),
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
    path: '/:catchAll(.*)',
    component: () => import(/* webpackChunkName: "404" */ '@/view/pages/404.vue'),
  },
];
