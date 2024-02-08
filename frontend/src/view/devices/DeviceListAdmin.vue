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
import { computed, ref, defineComponent, onMounted } from 'vue';
import { useStore } from 'vuex';
import { deviceListAdmin } from './getDeviceListAdmin';

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
    dataIndex: 'categoryName',
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
    title: '사용자',
    dataIndex: 'user',
    key: 'username',
    sorter: (a, b) => {
        const aValue = a.userKey?a.userKey:'';
        const bValue = b.userKey?b.userKey:'';
        return sortWithNullCheck(aValue, bValue);
      },
  },
  {
    title: '상태',
    dataIndex: 'status',
    key: 'status',
    sorter: (a, b) => {
        const aValue = a.status?a.status:'';
        const bValue = b.status?b.status:'';
        return sortWithNullCheck(aValue, bValue);
      },
  },
  {
    title: '관리부서',
    dataIndex: 'manageDep',
    key: 'manageDepName',
    sorter: (a, b) => {
        const aValue = a.manageDepKey?a.manageDepKey:'';
        const bValue = b.manageDepKey?b.manageDepKey:'';
        return sortWithNullCheck(aValue, bValue);
      },
  },
  {
    title: '프로젝트',
    dataIndex: 'project',
    key: 'projectName',
    sorter: (a, b) => {
        const aValue = a.projectKey?a.projectKey:'';
        const bValue = b.projectKey?b.projectKey:'';
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
    const response = deviceListAdmin.data;
    const { state, dispatch } = useStore();
    state.devicesAdmin.data = response;
    const deviceId = ref(null);
    const searchData = computed(() => state.headerSearchData);
    const orders = computed(() => state.devicesAdmin.data);
    
    const item = computed(() => state.devicesAdmin.data);
    const stateValue = ref('');
    const filterKey = ref('categoryName');
    const filterVal = ref([]);

    onMounted(() => {
      onSorting('categoryName');
    });

    const handleChangeForFilter = (e) => {
      dispatch('deviceAdminFilter', { column: filterKey.value, value: e.target.value, response: response });
    };

    const adminReturnDevice = (approvalId) => {
      dispatch('adminReturnDevice', approvalId)
        .then(() => {
          alert('반납 처리되었습니다.');
          location.reload();
        }
      );
    };

    const adminDisposeDevice = (deviceId) => {
      dispatch('adminDisposeDevice', deviceId)
        .then(() => {
          alert('폐기 처리되었습니다.');
          location.reload();
        }
      );
    };

    const dataSource = computed(() =>
      orders.value.map((value) => {
        let returnIcon = null;
        const { categoryName, manageDepName, projectName, purpose, model, history, approvalInfo, realUser,
          username, id, company, sn, purchaseDate, spec, description, approvalType, approvalId, status } = value;
        const formattedPurchaseDate = (purchaseDate === null) ? null : new Date(purchaseDate).toLocaleDateString('ko-KR',
              {
                year: 'numeric',
                month: 'long',
                day: 'numeric',
              });
        
        if (approvalInfo === '승인완료' && approvalType === '대여') { // 대여중인 것만
          returnIcon = 
          <sdButton class="btn-icon" onClick={() => adminReturnDevice(approvalId, id)} type="info" to="#" shape="circle">
            <sdFeatherIcons type="rotate-ccw" size={16} title="반납" />
          </sdButton>;
        }
              
        return {
          key: id,
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
          categoryNameKey: categoryName,
          categoryName: <span class="customer-name">{categoryName}</span>,
          userKey: username,
          user: <span class="customer-name">{(realUser)?realUser:username}</span>,
          status: status,
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
          purposeKey: purpose,
          manageDepKey: manageDepName,
          manageDep: <span class="ordered-amount">{manageDepName}</span>,
          projectKey: projectName,
          project: <span class="ordered-amount">{projectName}</span>,
          modelKey: model,
          model: <span class="ordered-amount">{model}</span>,
          companyKey: company,
          company: <span class="ordered-amount">{company}</span>,
          snKey: sn,
          sn: <span class="ordered-amount">{sn}</span>,
          purchaseDateKey: purchaseDate,
          purchaseDate: <span class="ordered-date">{formattedPurchaseDate}</span>,
          action: (
            <div class="table-actions">
              <>
                {returnIcon}
                <sdButton class="btn-icon" onClick={() => adminDisposeDevice(id)} type="danger" to="#" shape="circle">
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
