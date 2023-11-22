import mutations from './mutations';

const response = [
  {
    "img": "notebook.png",
    "deviceId": "DIR-N-106",
    "category": "노트북",
    "purpose": "개발",
    "user": "김철수",
    "manageDep": "제품2팀 0108",
    "project": "스마트로 0205",
    "description": "프로젝트용 노트북"
  },
  {
    "img": "monitor.png",
    "deviceId": "DIR-N-107",
    "category": "모니터",
    "purpose": "개발",
    "user": "김철수",
    "manageDep": "제품2팀 0108",
    "project": "스마트로 0205",
    "description": "프로젝트용 모니터",
  },
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
