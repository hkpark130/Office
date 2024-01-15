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
              :rowClassName="(record) => record.level ? 'urgent-row' : ''"
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
import { computed, reactive, ref, defineComponent } from 'vue';
import { useStore } from 'vuex';
import Tag from '../../components/tags/Tag';
import { useRouter } from 'vue-router';

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
    title: '구분',
    dataIndex: 'status',
    key: 'status',
    align: 'center',
    sorter: (a, b) => {
      const aValue = a.type?a.type:'';
      const bValue = b.type?b.type:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '신청장비',
    dataIndex: 'category',
    key: 'categoryName',
    sorter: (a, b) => {
      const aValue = a.categoryName?a.categoryName:'';
      const bValue = b.categoryName?b.categoryName:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
  {
    title: '신청자',
    dataIndex: 'user',
    key: 'userName',
    sorter: (a, b) => {
      const aValue = a.userName?a.userName:'';
      const bValue = b.userName?b.userName:'';
      return sortWithNullCheck(aValue, bValue);
    },    
  },
  {
    title: '마감일',
    dataIndex: 'deadline',
    key: 'deadline',
    sorter: (a, b) => {
      const aValue = a.deadline?a.deadline:'';
      const bValue = b.deadline?b.deadline:'';
      return sortWithNullCheck(aValue, bValue);
    }, 
  },
  {
    title: '신청정보',
    dataIndex: 'info',
    key: 'approvalInfo',
    sorter: (a, b) => {
      const aValue = a.approvalInfo?a.approvalInfo:'';
      const bValue = b.approvalInfo?b.approvalInfo:'';
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
  return column.key !== 'status' && index !== columns.length - 1;
});

const Orders = defineComponent({
  name: 'Orders',
  components: { TopToolBox, Main, TableWrapper },

  async setup() {
    const { state, dispatch } = useStore();
    const deviceId = ref(1);
    const searchData = computed(() => state.headerSearchData);
    const { push } = useRouter();

    const getUser = computed(() => state.getUser.data);
    await dispatch('getMyApproval', getUser.value.name);

    const orders = computed(() => state.myList.data);
    const item = computed(() => state.myList.data);

    const formState = reactive({
      visible: false,
      editApp: '',
    });

    const stateValue = ref('');
    const filterKey = ref('categoryName');
    const filterVal = ref(['노트북', '모니터', '서버']);

    const handleChangeForFilter = (e) => {
      dispatch('myListFilter', { column: filterKey.value, value: e.target.value, name: getUser.value.name });
    };

    const checkApproval = (approvalId) => {
      push("/check-approval-device/"+approvalId);
    };

    const editApproval = (approvalId) => {
      push("/edit-approval-device/"+approvalId);
    };

    const detailApproval = (approvalId) => {
      push("/detail-approval-device/"+approvalId);
    };

    const onCancel = () => {
      formState.visible = false;
    };

    const handleCancel = () => {
      onCancel();
    };

    const onSubmitHandler = () => {
      if (formState.editApp !== '') {
        formState.visible = false;
      } else {
        alert('Please Give a Task Title...');
      }
    };

    const dataSource = computed(() => 
      orders.value.map((value) => {
        const { categoryName, type, approvalInfo, userName, deviceId, approvalId, createdDate, deadline } = value;
        let deleteIcon = null;
        let editIcon = null;
        let checkIcon = null;
        let timeDiff = null;
        let daysDiff = null;
        let dDayTag = null;
        let urgency = false;
        const deadlineDate = (deadline === null)?null:new Date(deadline).toLocaleDateString('ko-KR',
            {
              year: 'numeric',
              month: 'long',
              day: 'numeric',
            });
        let statusTag = <span class="order-status">{approvalInfo}</span>;
        let approvalInfoClass = 
          approvalInfo === '승인대기' ? 'progress' : approvalInfo === '반납' ? 'delete' 
          : approvalInfo === '승인완료' ? 'complete' : approvalInfo === '반려' ? 'delete' : 'danger';

          if (deadline !== null) {
            timeDiff = (new Date(deadline)).getTime() - (new Date()).getTime();
            daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
            if (daysDiff < 6) {
              urgency = true;
              dDayTag = "긴급 "+daysDiff+" 일 남음";
            }
          }  

        if (approvalInfo === '승인대기') {
          deleteIcon = 
          <sdButton class="btn-icon" type="danger" to="#" shape="circle">
            <sdFeatherIcons type="trash-2" size={16} title="삭제" />
          </sdButton>;
          
          editIcon = 
            <sdButton onClick={() => editApproval(approvalId)} class="btn-icon" type="info" to="#" shape="circle">
              <sdFeatherIcons type="edit" size={16} title="편집" />
            </sdButton>;

          if (state.getUser.isAdmin) {
            checkIcon = 
              <sdButton onClick={() => checkApproval(approvalId)} class="btn-icon" type="success" shape="circle">
                <sdFeatherIcons type="check" size={16} title="승인/반려" />
              </sdButton>
            }
        }
        statusTag = (
            <div class="taglist-wrap">
              {(urgency)?<Tag data={dDayTag} tagType="colorful" color="red" />:''}
              <span class="order-status">{type}</span>
            </div>
          );

        return {
          key: approvalId,
          status: <>{statusTag}</>,
          categoryName: categoryName,
          type: type,
          approvalInfo: approvalInfo,
          deadline: deadline,
          category: <span class="order-id">{deviceId} <br/>{categoryName}</span>,
          user: <span class="customer-name">{userName}</span>,
          info: <a-tag class={approvalInfoClass}>{approvalInfo}</a-tag>,
          date: <span class="ordered-date">{createdDate}</span>,
          level: urgency,
          deadline: deadlineDate,
          action: (
            <div class="table-actions">
              <>
                {checkIcon}
                <sdButton onClick={() => detailApproval(approvalId)} class="btn-icon" type="primary" to="#" shape="circle" >
                  <sdFeatherIcons type="eye" size={16} title="상세정보" />
                </sdButton>
                {editIcon}
                {deleteIcon}
                
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
      onCancel,
      handleCancel,
      onSubmitHandler,
      formState,
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
