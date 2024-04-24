import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
    data: [],
    loading: false,
    error: null,
  });

const actions = {
  async commentPost({ commit }, data) {
    try {
      commit('commentPostBegin');
      await DataService.post('/api/comments/save', data);
      commit('commentPostSuccess');
    } catch (err) {
      commit('commentPostErr', err);
    }
  },

  async getCommentById({ commit }, ApprovalId) {
    try {
      commit('getCommentBegin');
      const query = await DataService.get(`/api/comments/${ApprovalId}`);
      commit('getCommentSuccess', query.data);
    } catch (err) {
      commit('getCommentErr', err);
    }
  },

  async deleteComment({ commit }, CommentId) {
    try {
      commit('deleteCommentBegin');
      await DataService.delete(`/api/comments/${CommentId}`);
      commit('deleteCommentSuccess');
    } catch (err) {
      commit('deleteCommentErr', err);
    }
  },

};

export default { 
    namespaced: false,
    actions,
    mutations,
    state,
};
