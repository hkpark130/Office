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
      <sdModal type="primary" title="Add New Todo" :visible="formState.visible" :footer="null" :onCancel="handleCancel">
        <div class="todo-modal">
          <BasicFormWrapper>
            <a-form class="adTodo-form" name="todoAdd" :model="formState" @finish="onSubmitHandler">
              <a-input v-model:value="formState.editApp" placeholder="Input Item Name......." />
              <br />
              <br />

              <sdButton @click="showModal" htmlType="submit" class="btn-adTodo" type="primary" size="large">
                Add New
              </sdButton>
            </a-form>
          </BasicFormWrapper>
        </div>
      </sdModal>
      
    </Main>
  </template>
  <script lang="jsx">
  import { TopToolBox } from './Style';
  import { Main, TableWrapper, BasicFormWrapper } from '../styled';
  import { computed, reactive, ref, defineComponent } from 'vue';
  import { useStore } from 'vuex';
  import Tag from '../../components/tags/Tag';

  const columns = [
    {
      title: '구분',
      dataIndex: 'status',
      key: 'status',
      align: 'center',
    },
    {
      title: '신청장비',
      dataIndex: 'category',
      key: 'categoryName',
    },
    {
      title: '신청자',
      dataIndex: 'user',
      key: 'userName',
    },
    {
      title: '신청정보',
      dataIndex: 'info',
      key: 'approvalInfo',
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
    components: { BasicFormWrapper, TopToolBox, Main, TableWrapper },
  
    async setup() {
      const { state, dispatch } = useStore();
      const deviceId = ref(1);
      const searchData = computed(() => state.headerSearchData);

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

      const showModal = (row) => {
        // console.log(row); row 객체 다 받아올 수 있음
        console.log(row.deviceId);
        
        formState.visible = true;
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
          const { categoryName, type, approvalInfo, userName, deviceId, approvalId, createdDate, urgency } = value;
          let deleteIcon = null;
          let editIcon = null;
          let checkIcon = null;
          let statusTag = <span class="order-status">{approvalInfo}</span>;
          let approvalInfoClass = 
            approvalInfo === '승인대기' ? 'progress' : approvalInfo === '반납' ? 'delete' 
            : approvalInfo === '승인완료' ? 'complete' : approvalInfo === '반려' ? 'delete' : 'danger';
          if (approvalInfo === '승인대기') {
            deleteIcon = 
            <sdButton class="btn-icon" type="danger" to="#" shape="circle">
              <sdFeatherIcons type="trash-2" size={16} title="삭제" />
            </sdButton>;
            
            editIcon = 
            <router-link to={"/edit-approval/"+approvalId}>
              <sdButton class="btn-icon" type="info" to="#" shape="circle">
                <sdFeatherIcons type="edit" size={16} title="편집" />
              </sdButton>
            </router-link>;

            if (state.getUser.isAdmin) {
              checkIcon = 
                <router-link to={"/check-approval-device/"+approvalId}>
                  <sdButton class="btn-icon" type="info" to="#" shape="circle">
                    <sdFeatherIcons type="check" size={16} title="승인/반려" />
                  </sdButton>
                </router-link>;
              }
          }
          if (urgency) {
            statusTag = 
            <div class="taglist-wrap">
              <Tag data="긴급" tagType="colorful" color="red" />
              <span class="order-status">{type}</span>
            </div>;
          } else {
            statusTag = 
            <div class="taglist-wrap">
              <span class="order-status">{type}</span>
            </div>;
          }

          return {
            status: <>{statusTag}</>,
            category: <span class="order-id">{deviceId} <br/>{categoryName}</span>,
            user: <span class="customer-name">{userName}</span>,
            info: <a-tag class={approvalInfoClass}>{approvalInfo}</a-tag>,
            date: <span class="ordered-date">{createdDate}</span>,
            level: urgency,
            action: (
              <div class="table-actions">
                <>
                  {checkIcon}
                  <sdButton onClick={() => showModal(value)} class="btn-icon" type="primary" to="#" shape="circle" >
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
        filterVal.value = [...new Set(item.value.map((item) => item[selectedItems]))]; // 중복 제거
      };
      
      return {
        showModal,
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
  