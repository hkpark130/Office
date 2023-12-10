import mutations from './mutations';

const response = [
  {
    "deviceId": "DIR-N-106",
    "category": "노트북",
    "purpose": "개발",
    "user": "김철수",
    "status": "progress",
    "level": "urgent",
    "info": "진행중",
    "date": "January 20, 2021"
  },
  {
    "deviceId": "DIR-N-107",
    "category": "모니터",
    "purpose": "개발",
    "user": "김철수",
    "status": "delete",
    "level": "normal",
    "info": "반납",
    "date": "January 28, 2021"
  },
  {
    "deviceId": "DIR-N-108",
    "category": "모니터",
    "purpose": "개발",
    "user": "김철수",
    "status": "complete",
    "level": "normal",
    "info": "완료",
    "date": "January 28, 2021"
  },
  {
    "deviceId": "DIR-N-109",
    "category": "모니터",
    "purpose": "개발",
    "user": "김철수",
    "status": "return",
    "level": "normal",
    "info": "반려",
    "date": "January 28, 2021"
  },
];

const state = () => ({
  data: response,
  loading: false,
  error: null,
});

const actions = {
  async myListFilter({ commit }, { column, value }) {
    try {
      commit('filterMyListBegin');
      const data = response.filter((item) => {
        if (value !== '') {
          return item[column] === value;
        }
        return item;
      });
      commit('filterMyListSuccess', data);
    } catch (err) {
      commit('filterMyListErr', err);
    }
  },
};

export default {
  namespaced: false,
  state,
  actions,
  mutations,
};
