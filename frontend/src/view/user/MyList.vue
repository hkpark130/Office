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
                    <span class="toolbox-menu-title"> Status:</span>
                    <a-radio-group v-if="item" @change="handleChangeForFilter">
                      <a-radio-button value="">All</a-radio-button>
                      <a-radio-button v-for="value in [...new Set(filterKey)]" :key="value" :value="value">
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
                :rowClassName="(record) => record.level === 'urgent' ? 'urgent-row' : ''"
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
  import { useRouter } from 'vue-router';
  import Tag from '../../components/tags/Tag';

  const columns = [
    {
      title: '구분',
      dataIndex: 'status',
      key: 'status',
    },
    {
      title: '신청장비',
      dataIndex: 'deviceName',
      key: 'deviceName',
    },
    {
      title: '신청자',
      dataIndex: 'user',
      key: 'user',
    },
    {
      title: '신청일자',
      dataIndex: 'date',
      key: 'date',
    },
    {
      title: '신청정보',
      dataIndex: 'info',
      key: 'info',
    },
    {
      title: 'Action',
      dataIndex: 'action',
      key: 'action',
    },
  ];
  
  const Orders = defineComponent({
    name: 'Orders',
    components: { BasicFormWrapper, TopToolBox, Main, TableWrapper },
  
    setup() {
      const { state, dispatch } = useStore();
      const deviceId = ref(1);
      const searchData = computed(() => state.headerSearchData);
      const orders = computed(() => state.myList.data);
      const formState = reactive({
        visible: false,
        editApp: '',
      });
      const router = useRouter();
  
      const item = computed(() => state.myList.data);
      const stateValue = ref('');
      const filterKey = ref(['progress', 'complete', 'delete', 'return']);
  
      const handleChangeForFilter = (e) => {
        dispatch('orderFilter', { column: 'status', value: e.target.value });
      };

      const showModal = (row) => {
        // console.log(row); row 객체 다 받아올 수 있음
        console.log(row.deviceId);
        
        formState.visible = true;
      };

      const navigateWithObject = (value) => {
        router.push({
          name: 'approval-process',
          query: {
            ...value,
          },
        });
      }

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
          const { category, info, status, user, deviceId, date, level } = value;
          let deleteIcon = null;
          let editIcon = null;
          let statusTag = <span class="order-status">{status}</span>;
          if (status === 'progress') {
            deleteIcon = 
            <sdButton class="btn-icon" type="danger" to="#" shape="circle">
              <sdFeatherIcons type="trash-2" size={16} title="삭제" />
            </sdButton>;
            
            editIcon = 
            <router-link onClick={() => navigateWithObject(value)} to="/approval-process">
              <sdButton class="btn-icon" type="info" to="#" shape="circle">
                <sdFeatherIcons type="edit" size={16} title="편집" />
              </sdButton>
            </router-link>;

          }
          if (level === 'urgent') {
            statusTag = 
            <div class="taglist-wrap">
              <Tag data="긴급" tagType="colorful" color="red" />
              <span class="order-status">{status}</span>
            </div>;
          }

          return {
            status: <>{statusTag}</>,
            deviceName: <span class="order-id">{deviceId} {category}</span>,
            category: <span class="customer-name">{category}</span>,
            user: <span class="customer-name">{user}</span>,
            info: <a-tag class={status}>{info}</a-tag>,
            date: <span class="ordered-date">{date}</span>,
            level: level,
            action: (
              <div class="table-actions">
                <>
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
      
      return {
        showModal,
        onCancel,
        navigateWithObject,
        handleCancel,
        onSubmitHandler,
        formState,
        deviceId,
        dataSource,
        handleChangeForFilter,
        filterKey,
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
  