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
              />
            </TableWrapper>
          </a-col>
        </a-row>
      </sdCards>
      <sdModal type="primary" title="수정" :visible="formState.visible" :footer="null" :onCancel="handleCancel">
        <div class="todo-modal">
          <BasicFormWrapper>
            <a-form class="adTodo-form" name="todoAdd" :model="formState" @finish="onSubmitHandler">
              <a-input v-model:value="formState.editApp" placeholder="상태" />
              <br />
              <br />
              <a-input v-model:value="formState.editApp" placeholder="비고" />
              <br />
              <br />
              <sdButton @click="showModal" htmlType="submit" class="btn-adTodo" type="primary" size="large">
                수정
              </sdButton>
            </a-form>
          </BasicFormWrapper>
        </div>
      </sdModal>
    </Main>
  </template>
  <script lang="jsx">
  import { TopToolBox } from './Style';
  import { Main, TableWrapper } from '../styled';
  import { computed, ref, defineComponent, reactive } from 'vue';
  import { useStore } from 'vuex';
  
  const columns = [
    {
      title: 'Product',
      dataIndex: 'category',
      key: 'categoryName',
    },
    {
      title: '사용자',
      dataIndex: 'user',
      key: 'username',
    },
    {
      title: '용도',
      dataIndex: 'purpose',
      key: 'purpose',
    },
    {
      title: '프로젝트',
      dataIndex: 'project',
      key: 'projectName',
    },
    {
      title: '관리부서',
      dataIndex: 'manageDep',
      key: 'manageDepName',
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
      // align: 'left',
    },
  ];

  const filterColumns = columns.filter((column, index) => {
    return column.key !== 'description' && 
    column.key !== 'id' && 
    column.key !== 'purchaseDate' &&
    index !== columns.length - 1;
  });
  
  const Orders = defineComponent({
    name: 'Orders',
    components: { TopToolBox, Main, TableWrapper },
  
    async setup() {
      const { state, dispatch } = useStore();
      const deviceId = ref(null);
      const getUser = computed(() => state.getUser.data);
      const searchData = computed(() => state.headerSearchData);

      await dispatch('getMyDevices', getUser.value.name);

      const orders = computed(() => state.myDevice.data);
      const item = computed(() => state.myDevice.data);

      const stateValue = ref('');
      const filterKey = ref('categoryName');
      const filterVal = ref(['노트북', '모니터', '서버']);
  
      const handleChangeForFilter = (e) => {
        dispatch('myDeviceFilter', { column: filterKey.value, value: e.target.value, name: getUser.value.name });
      };

      const formState = reactive({
        visible: false,
        editApp: '',
      });

      const showModal = (row) => {
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
          const { categoryId, categoryName, purpose, projectName, manageDepName, username, id, description } = value;

          return {
            category: (
              <div class="user-info">
                <figure>
                    <img style={{ width: '50px' }} src={`${categoryId ? require(`@/static/img/${categoryId.img}`) : ''}`} alt="" />
                </figure>
                <figcaption>
                    <sdHeading as="h6">
                        {id}
                    </sdHeading>
                    <span class="user-designation">{categoryName}</span>
                </figcaption>
              </div>
            ),
            user: <span class="customer-name">{username}</span>,
            manageDep: <span class="customer-name">{manageDepName}</span>,
            project: <span class="customer-name">{projectName}</span>,
            purpose: (
              <div>
                <span class="ordered-amount spnDetails">{purpose}</span>
                <span class="spnTooltip">
                    <strong>CPU: </strong>12C<br />
                    <strong>RAM: </strong>32G<br />
                    테스트 툴팁
                </span>
              </div>
            ),
            description: <span class="ordered-date">{description}</span>,
            action: (
              <div class="table-actions">
                <>
                  
                  <sdButton onClick={() => showModal(value)} class="btn-icon" type="link" shape="circle">
                    <sdFeatherIcons type="edit" size={16} title="수정" />
                  </sdButton>
                  <router-link to={"/return-device/"+id}>
                    <sdButton class="btn-icon" type="primary" to="#" shape="circle" >
                      <sdFeatherIcons type="rotate-ccw" size={16} title="반납" />
                    </sdButton>
                  </router-link>
                  <router-link to={"/delete-device/"+id}>
                    <sdButton class="btn-icon" type="danger" to="#" shape="circle">
                      <sdFeatherIcons type="trash-2" size={16} title="폐기" />
                    </sdButton>
                  </router-link>
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
        formState,
        deviceId,
        showModal,
        onCancel,
        handleCancel,
        onSubmitHandler,
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
  