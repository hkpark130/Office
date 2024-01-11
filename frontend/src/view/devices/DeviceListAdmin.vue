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
                  <router-link :to="`/add-device`">
                    <sdButton size="small" type="primary"> <sdFeatherIcons type="plus" size="12" /> 
                    장비등록
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
              :rowSelection="rowSelection"
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

const columns = [
  {
    title: '품목',
    dataIndex: 'categoryName',
    key: 'categoryName',
  },
  {
    title: '관리번호',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '사용자',
    dataIndex: 'user',
    key: 'username',
  },
  {
    title: '관리부서',
    dataIndex: 'manageDep',
    key: 'manageDepName',
  },
  {
    title: '프로젝트',
    dataIndex: 'project',
    key: 'projectName',
  },
  {
    title: '용도',
    dataIndex: 'purpose',
    key: 'purpose',
  },
  {
    title: '구입일자',
    dataIndex: 'purchaseDate',
    key: 'purchaseDate',
  },
  {
    title: '모델명',
    dataIndex: 'model',
    key: 'model',
  },
  {
    title: '제조사',
    dataIndex: 'company',
    key: 'company',
  },
  {
    title: 'S/N',
    dataIndex: 'sn',
    key: 'sn',
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
    const orders = computed(() => state.devicesAdmin.data);
    
    const item = computed(() => state.devicesAdmin.data);
    const stateValue = ref('');
    const filterKey = ref('categoryName');
    const filterVal = ref(['노트북', '모니터', '서버']);

    const handleChangeForFilter = (e) => {
      dispatch('deviceAdminFilter', { column: filterKey.value, value: e.target.value });
    };

    const removeItem = (deviceId) => {
      const index = orders.value.findIndex((item) => item.id === deviceId);
      if (index !== -1) {
        orders.value.splice(index, 1);
      }
    };

    const dataSource = computed(() =>
      orders.value.map((value) => {
        const { categoryName, manageDepName, projectName, purpose, model, history,
          username, id, company, sn, purchaseDate, spec, description } = value;
        const formattedPurchaseDate = (purchaseDate === null)?null:new Date(purchaseDate).toISOString().split('T')[0];
        return {
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
          categoryName: <span class="customer-name">{categoryName}</span>,
          user: <span class="customer-name">{username}</span>,
          purpose: (
            <div>
              <span class="ordered-amount spnDetails">{purpose}</span>
              <span class="spnTooltip">
                <strong>스펙: </strong>{spec}<br />
                <hr/>
                {description}
              </span>
            </div>
          ),
          manageDep: <span class="ordered-amount">{manageDepName}</span>,
          project: <span class="ordered-amount">{projectName}</span>,
          model: <span class="ordered-amount">{model}</span>,
          company: <span class="ordered-amount">{company}</span>,
          sn: <span class="ordered-amount">{sn}</span>,
          purchaseDate: <span class="ordered-date">{formattedPurchaseDate}</span>,
          action: (
            <div class="table-actions">
              <>
                <sdButton class="btn-icon" type="info" to="#" shape="circle">
                  <sdFeatherIcons type="rotate-ccw" size={16} title="반납" />
                </sdButton>
                <sdButton class="btn-icon" onClick={() => removeItem(id)} type="danger" to="#" shape="circle">
                  <sdFeatherIcons type="trash-2" size={16} title="폐기" />
                </sdButton>
              </>
            </div>
          ),
        };
      }),
    );

    const onSorting = (selectedItems) => {
      filterKey.value = selectedItems;
      filterVal.value = [...new Set(item.value.map((item) => item[selectedItems]))]; // 중복 제거
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
