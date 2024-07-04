import mutations from './mutations';
import { DataService } from '@/config/dataService/dataService';

const state = () => ({
    loading: false,
    error: null,
    duplicationCheck: false,
  });

const actions = {
  async fetchLdapUserList({ commit }) {
    try {
      commit('fetchLdapUserListBegin');
      const ldapUserList = await DataService.get('/api/ldap-users');
      commit('fetchLdapUserListSuccess', ldapUserList.data);
      return ldapUserList.data;
    } catch (err) {
      commit('fetchLdapUserListErr', err);
      throw new Error(err);
    }
  },

  async ldapUserFilter({ commit }, { column, value, response }) {
    try {
      commit('filterLdapUserBegin');
      const data = response.filter((item) => {
        if (value !== '') {
          if (item[column] === null){
            return false;
          }
          return String(item[column]).includes(value);
        }
        return item;
      });
      commit('filterLdapUserSuccess', data);
    } catch (err) {
      commit('filterLdapUserErr', err);
    }
  },

  async submitAddLdapUserPost({ commit }, data) {
    try {
      commit('postAddLdapUserBegin');
      await DataService.post('/api/ldap-user', data);
      commit('postAddLdapUserSuccess', data);
    } catch (err) {
      commit('postAddLdapUserErr', err);
      throw new Error(err);
    }
  },
  
  async reIssuePassword({ commit }, cn) {
    try {
      commit('reIssuePasswordBegin');
      const query = await DataService.get(`/api/reissue-password/${cn}`);
      commit('reIssuePasswordSuccess', query.data);
      return query.data;
    } catch (err) {
      commit('reIssuePasswordErr', err);
      throw new Error(err);
    }
  },

  async cnCheckDuplication({ commit }, cn) {
    try {
      commit('cnCheckDuplicationBegin');
      const query = await DataService.get(`/api/check-ldap-user-cn/${cn}`);
      commit('cnCheckDuplicationSuccess', query.data);
      return query.data;
    } catch (err) {
      commit('cnCheckDuplicationErr', err);
      throw new Error(err);
    }
  },

  async uidNumCheckDuplication({ commit }, uidnum) {
    try {
      commit('uidNumCheckDuplicationBegin');
      const query = await DataService.get(`/api/check-ldap-user-uidnum/${uidnum}`);
      commit('uidNumCheckDuplicationSuccess', query.data);
      return query.data;
    } catch (err) {
      commit('uidNumCheckDuplicationErr', err);
      throw new Error(err);
    }
  },

  async deleteLdapUser({ commit }, cn) {
    try {
      commit('deleteLdapUserBegin');
      const query = await DataService.delete(`/api/ldap-user/${cn}`);
      commit('deleteLdapUserSuccess', query.data);
      return query.data;
    } catch (err) {
      commit('deleteLdapUserErr', err);
      throw new Error(err);
    }
  },
};

export default { 
    namespaced: false,
    actions,
    mutations,
    state,
};
