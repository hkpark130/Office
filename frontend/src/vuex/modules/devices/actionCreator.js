import mutations from './mutations';

const response = [
  {
    "deviceId": "#02-0003",
    "category": "노트북",
    "purpose": "개발",
    "user": "Kellie Marquot",
    "status": "Shipped",
    "info": "승인대기",
    "tag": "$4,248.66",
    "memo": "January 20, 2021"
  },
  {
    "deviceId": "#02-0004",
    "category": "모니터",
    "purpose": "개발",
    "user": "Carroll Maharry",
    "status": "Awaiting Shipment",
    "info": "반납예정(20231120)",
    "tag": "$14,248.61",
    "memo": "January 28, 2021"
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
