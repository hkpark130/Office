import mutations from './mutations';

const response = [
  {
    "deviceId": "DIR-N-106",
    "category": "노트북",
    "purpose": "개발",
    "user": "Kellie Marquot",
    "status": "Shipped",
    "info": "승인대기",
    "tag": "OS 설치필요",
    "memo": "January 20, 20222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222221"
  },
  {
    "deviceId": "DIR-N-107",
    "category": "모니터",
    "purpose": "개발",
    "user": "Carroll Maharry",
    "status": "Awaiting Shipment",
    "info": "반납예정(20231120)",
    "tag": "포맷완료",
    "memo": "테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 "
  }
];

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async orderFilter({ commit }, { column, value, orders }) {
    console.log("SSSSSSSS1", orders);
    try {
      commit('filterOrderBegin');
      const data = orders.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterOrderSuccess', data);
    } catch (err) {
      commit('filterOrderErr', err);
    }
    console.log("SSSSSSSS2", orders);
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
