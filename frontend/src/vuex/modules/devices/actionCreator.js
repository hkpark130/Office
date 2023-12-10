import mutations from './mutations';
// import { availableDeviceList } from './load-data';

// const response = availableDeviceList.data;

const response = [
  {
    "id": "DIR-N-106",
    "categoryId":{"id":1,"name":"노트북","img":"notebook.png"},
    "purpose": "개발",
    "userId": "Kellie Marquot",
    "status": "Shipped",
    "info": "승인대기",
    "tag": "OS 설치필요",
    "description": "January 20, 20222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222221"
  },
  {
    "id": "DIR-N-107",
    "categoryId":{"id":2,"name":"서버","img":"server.png"},
    "purpose": "개발",
    "userId": "Carroll Maharry",
    "status": "Awaiting Shipment",
    "info": "반납예정(20231120)",
    "tag": "포맷완료",
    "description": "테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 "
  }
];

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async deviceFilter({ commit }, { column, value }) {
    try {
      commit('filterDeviceBegin');
      const data = response.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterDeviceSuccess', data);
    } catch (err) {
      commit('filterDeviceErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
