<template>
  <Main>
    <sdCards headless>
      <a-row :gutter="15">
        <a-col :xs="24">
          <TopToolBox>
            <a-row :gutter="15" class="justify-content-center">
              <a-col :lg="6" :xs="24">
                <div class="table-search-box">
                  <sdAutoComplete :dataSource="searchData" width="100%" patterns />
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
              <a-col :xxl="4" :xs="24">
                <div class="table-toolbox-actions">
                  <sdButton size="small" type="secondary" transparented> Export </sdButton>
                  <router-link :to="`/device-application/${deviceId}`">
                    <sdButton size="small" type="primary"> <sdFeatherIcons type="plus" size="12" /> 
                    사용신청
                    </sdButton>
                  </router-link>
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
              :dataSource="dataSource"
              :columns="columns"
              :pagination="{ pageSize: 7, showSizeChanger: true, total: orders ? orders.length : 20 }"
            />
          </TableWrapper>
        </a-col>
      </a-row>
    </sdCards>
  </Main>
</template>
<script lang="jsx">
import { TopToolBox } from './Style';
import { Main, TableWrapper } from '../styled';
import { computed, ref, defineComponent } from 'vue';
import { useStore } from 'vuex';
import * as FontAwesomeIcon from '@fortawesome/free-solid-svg-icons';

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
    title: '품목',
    dataIndex: 'category',
    key: 'categoryName',
    sorter: (a, b) => {
      const aValue = a.categoryNameKey?a.categoryNameKey:'';
      const bValue = b.categoryNameKey?b.categoryNameKey:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '관리번호',
    dataIndex: 'id',
    key: 'id',
    sorter: (a, b) => {
      const aValue = a.key?a.key:'';
      const bValue = b.key?b.key:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '용도',
    dataIndex: 'purpose',
    key: 'purpose',
    sorter: (a, b) => {
      const aValue = a.purposeKey?a.purposeKey:'';
      const bValue = b.purposeKey?b.purposeKey:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '구입일자',
    dataIndex: 'purchaseDate',
    key: 'purchaseDate',
    sorter: (a, b) => {
      const aValue = a.purchaseDateKey?a.purchaseDateKey:'';
      const bValue = b.purchaseDateKey?b.purchaseDateKey:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '모델명',
    dataIndex: 'model',
    key: 'model',
    sorter: (a, b) => {
      const aValue = a.modelKey?a.modelKey:'';
      const bValue = b.modelKey?b.modelKey:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '제조사',
    dataIndex: 'company',
    key: 'company',
    sorter: (a, b) => {
      const aValue = a.companyKey?a.companyKey:'';
      const bValue = b.companyKey?b.companyKey:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: 'S/N',
    dataIndex: 'sn',
    key: 'sn',
    sorter: (a, b) => {
      const aValue = a.snKey?a.snKey:'';
      const bValue = b.snKey?b.snKey:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '비고',
    dataIndex: 'description',
    key: 'description',
  },
  {
    title: 'Action',
    dataIndex: 'action',
    key: 'action',
  },
];

const filterColumns = columns.filter((column, index) => {
  return column.key !== 'memo' && 
  column.key !== 'id' && 
  column.key !== 'sn' && 
  column.key !== 'model' && 
  column.key !== 'purchaseDate' &&
  index !== columns.length - 1;
});

const Orders = defineComponent({ 
  name: 'Orders',
  components: { TopToolBox, Main, TableWrapper },

  setup() {
    const { state, dispatch } = useStore();
    const deviceId = ref(null);
    const searchData = computed(() => state.headerSearchData);
    const orders = computed(() => state.disposeDevicesAdmin.data);

    const item = computed(() => state.disposeDevicesAdmin.data);
    const stateValue = ref('');
    const filterKey = ref('categoryName');
    const filterVal = ref(['노트북', '모니터', '서버']);

    const handleChangeForFilter = (e) => {
      dispatch('disposeDeviceFilter', { column: filterKey.value, value: e.target.value });
    };

    const removeItem = (deviceId) => {
      const index = orders.value.findIndex((item) => item.id === deviceId);
      if (index !== -1) {
        orders.value.splice(index, 1);
      }
    };

    const dataSource = computed(() =>
      orders.value.map((value) => {
        const { categoryName, manageDep, project, purpose, model, user, id, company, sn, 
            purchaseDate, description, history } = value;
        const formattedPurchaseDate = (purchaseDate === null)?null:new Date(purchaseDate).toISOString().split('T')[0];
        return {
          key: id, // radio 선택시 기준 값
          id: (
              <div>
                <span class="order-id spnDetails">{id}</span>
                {history.length !== 0 ? (
                  <span class="spnTooltip">
                    <table class="historyTable">
                      <thead>
                        <tr>
                          <th>사용자</th>
                          <th>타입</th>
                          <th>날짜</th>
                        </tr>
                      </thead>
                      <tbody>
                        {history.map((item) => (
                          <tr>
                            <td style="background-color:white;">{item.username}</td>
                            <td style="background-color:white;">{item.type}</td>
                            <td style="background-color:white;">{item.modifiedDate}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </span>
                ) : null}
              </div>
            ),
          category: <span class="customer-name">{categoryName}</span>,
          categoryNameKey: categoryName,
          user: <span class="customer-name">{user}</span>,
          userKey: user,
          purpose: (
            <div>
              <span class="ordered-amount spnDetails">{purpose}</span>
              <span class="spnTooltip">
                  <strong>CPU: </strong>12C<br/>
                  <strong>RAM: </strong>32G<br/>
              </span>
            </div>
          ),
          purposeKey: purpose,
          manageDep: <span class="ordered-amount">{manageDep}</span>,
          manageDepKey: manageDep,
          project: <span class="ordered-amount">{project}</span>,
          projectKey: project,
          model: <span class="ordered-amount">{model}</span>,
          modelKey: model,
          company: <span class="ordered-amount">{company}</span>,
          companyKey: company,
          sn: <span class="ordered-amount">{sn}</span>,
          snKey: sn,
          purchaseDate: <span class="ordered-date">{formattedPurchaseDate}</span>,
          purchaseDateKey: purchaseDate,
          description: <span class="ordered-date">{description}</span>,
          action: (
            <div class="table-actions">
              <>
                <sdButton class="btn-icon" onClick={() => removeItem(id)} type="info" to="#" shape="circle">
                  <font-awesome-icon icon={FontAwesomeIcon["faRecycle"]} size={16} title="복구" />
                </sdButton>
              </>
            </div>
          ),
        };
      }),
    );

      const onSorting = (selectedItems) => {
        filterKey.value = selectedItems;
        filterVal.value = [...new Set(item.value.map((item) => item[selectedItems]).filter(val => val !== null))]; // 중복 및 null 제거
      };
      
      return {
        deviceId,
        dataSource,
        handleChangeForFilter,
        filterKey,
        filterVal,
        filterColumns,
        onSorting,
        item,
        searchData,
        columns,
        orders,
        stateValue,
      };
    },
  });
  
  export default Orders;
  </script>
  
