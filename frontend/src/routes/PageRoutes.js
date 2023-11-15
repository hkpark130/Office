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
    name: 'forbidden',
    path: '/403',
    component: () => import(/* webpackChunkName: "403" */ '@/view/pages/403.vue'),
  },
  {
    path: '/:catchAll(.*)',
    component: () => import(/* webpackChunkName: "404" */ '@/view/pages/404.vue'),
  },
];
