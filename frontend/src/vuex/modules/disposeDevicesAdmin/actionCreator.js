import mutations from './mutations';
// import { disposeDeviceListAdmin } from './load-data';

const response = [
  {
    "deviceId": "DIR-N-106",
    "category": "노트북",
    "purpose": "개발",
    "purchaseDate": "January 20, 2021",
    "user": "김철수",
    "model": "LSBX2431",
    "company": "LG",
    "sn": "ND6179NBK",
    "description": "파손"
  },
  {
    "deviceId": "DIR-N-107",
    "category": "모니터",
    "purpose": "개발",
    "purchaseDate": "January 20, 2021",
    "user": "김철수",
    "model": "LSBX2431",
    "company": "LG",
    "sn": "ND6179NBK",
    "description": "부팅불가"
  }
];

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async orderFilter({ commit }, { column, value }) {
    try {
      commit('filterOrderBegin');
      const data = response.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterOrderSuccess', data);
    } catch (err) {
      commit('filterOrderErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
