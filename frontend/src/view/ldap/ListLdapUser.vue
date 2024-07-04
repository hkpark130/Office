<template>
  <Main>
    <sdCards headless>
      <a-row :gutter="15">
        <a-col :xs="24">
          <TopToolBox>
            <a-row :gutter="15" class="justify-content-center">
              <a-col :lg="6" :xs="24">
                <div class="table-search-box">
                  <a-input placeholder="Search..." v-model:value="searchData" @keyup.enter="onSearching()"/>
                </div>
              </a-col>
              <a-col :xxl="14" :lg="16" :xs="24">
                <div class="table-toolbox-menu">
                  <a-select
                    @change="onSorting"
                    :defaultValue="filterKey"
                  >
                    <a-select-option v-for="column in filterColumns" :key="column.key">
                      <span class="toolbox-menu-title" > {{ column.title }}</span>
                    </a-select-option>
                  </a-select>

                  <a-radio-group v-if="item" @change="handleChangeForFilter">
                    <a-radio-button value="">All</a-radio-button>
                    <a-radio-button v-for="value in [...new Set(filterVal)]" :key="value" :value="value">
                      {{ value }}
                    </a-radio-button>
                  </a-radio-group>
                </div>
              </a-col>
            </a-row>
          </TopToolBox>
        </a-col>
      </a-row>
      <a-row :gutter="15">
        <a-col :md="24">
          <TableWrapper class="table-order table-responsive">
            <a-table
              :type="radio"
              :dataSource="dataSource"
              :columns="columns"
              :pagination="{ pageSize: pageSize, showSizeChanger: true, total: orders ? orders.length : 20, 
                onChange: onChangePage}"
              style="white-space: pre-line;"
            />
          </TableWrapper>
        </a-col>
      </a-row>
    </sdCards>
  </Main>
</template>
<script lang="jsx">
import { TopToolBox } from './style';
import { Main, TableWrapper } from '../styled';
import { computed, ref, defineComponent, onMounted } from 'vue';
import { useStore } from 'vuex';
import { ldapUserList } from './getLdapUserList';

const sortWithNullCheck = (aValue, bValue) => {
  // Null 값을 제일 뒤로 둘거임
  // 둘 다 null이면 순서를 변경하지 않음
  if (aValue === '' && bValue === '') {
    return 0;
  }

  // aValue가 null이면 b가 먼저 오도록 함
  if (aValue === '') {
    return 1;
  }

  // bValue가 null이면 a가 먼저 오도록 함
  if (bValue === '') {
    return -1;
  }

  // 둘 다 null이 아닐 경우, localeCompare로 문자열 비교
  return aValue.localeCompare(bValue);
  // 정수인 경우 aValue - bValue 로 비교해줘야 함
};

const columns = [
  {
    title: '사원번호',
    dataIndex: 'uidNumber',
    key: 'uidNumber',
    sorter: (a, b) => {
      const aValue = a.key?a.key:'';
      const bValue = b.key?b.key:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '유저 아이디',
    dataIndex: 'cn',
    key: 'cn',    
    sorter: (a, b) => {
      const aValue = a.purposeKey?a.purposeKey:'';
      const bValue = b.purposeKey?b.purposeKey:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '유저 이름',
    dataIndex: 'uid',
    key: 'uid',    
    sorter: (a, b) => {
      const aValue = a.purposeKey?a.purposeKey:'';
      const bValue = b.purposeKey?b.purposeKey:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: 'email',
    dataIndex: 'email',
    key: 'email',
  },
  {
    title: '부서',
    dataIndex: 'ou',
    key: 'ou',
  }
];

const filterColumns = columns.filter((column) => {
  return column.key !== 'email' && column.key !== 'uidNumber';
});

const AvailableDevices = defineComponent({
  name: 'AvailableDevices',
  components: { TopToolBox, Main, TableWrapper },

  setup() {
    const response = ldapUserList.data;
    const { state, dispatch } = useStore();
    state.ldap.data = response;
    const filterKey = ref('ou');
    const searchData = ref('');
    const orders = computed(() => state.ldap.data);
    const item = computed(() => state.ldap.data);
    const pageSize = ref(7);
    dispatch("fetchLdapUserList").then(() => {
      orders.value = computed(() => state.ldap.data);
      item.value = computed(() => state.ldap.data);
    });

    onMounted(() => {
      onSorting('ou');
    });

    const stateValue = ref('');
    const filterVal = ref([]);

    const handleChangeForFilter = (e) => {
      dispatch('ldapUserFilter', { column: filterKey.value, value: e.target.value, response: response });
    };

    const dataSource = computed(() =>
      orders.value.map((value) => {
        const { cn, mail, ou, uid, uidNumber } = value;
        return {
          uidNumber: <span class="order-id">{uidNumber}</span>,
          cn: <span class="customer-name">{ cn }</span>,
          uid: <span class="customer-name">{ uid }</span>,
          ou: <span class="customer-name">{ ou }</span>,
          email: <span class="customer-name">{ mail }</span>,
        };
      }),
    );

    const onSorting = (selectedItems) => {
      filterKey.value = selectedItems;
      if(selectedItems === 'uid' || selectedItems === 'cn'){
        filterVal.value = []; // 사원번호는 검색으로
      } else {
        filterVal.value = [...new Set(item.value.map((item) => item[selectedItems]).filter(val => val !== null))]; // 중복 및 null 제거
      }
    };
    
    const onChangePage = (page, size) => {
      pageSize.value = size;
    };

    const onSearching = () => {
      dispatch('ldapUserFilter', { column: filterKey.value, value: searchData.value, response: response });
    };

      return {
        dataSource,
        handleChangeForFilter,
        onSorting,
        filterVal,
        filterKey,
        item,
        searchData,
        columns,
        filterColumns,
        orders,
        stateValue,
        onChangePage,
        pageSize,
        onSearching,
      };
    },
  });
  
  export default AvailableDevices;
  </script>
  
