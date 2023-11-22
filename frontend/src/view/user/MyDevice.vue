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
  
  const columns = [
    {
      title: 'Product',
      dataIndex: 'product',
      key: 'product',
    },
    {
      title: '사용자',
      dataIndex: 'user',
      key: 'user',
    },
    {
      title: '용도',
      dataIndex: 'purpose',
      key: 'purpose',
    },
    {
      title: '프로젝트',
      dataIndex: 'project',
      key: 'project',
    },
    {
      title: '비고',
      dataIndex: 'description',
      key: 'description',
    },
    {
      title: '관리부서',
      dataIndex: 'manageDep',
      key: 'manageDep',
    },
    {
      title: 'Action',
      dataIndex: 'action',
      key: 'action',
    },
  ];
  
  const Orders = defineComponent({
    name: 'Orders',
    components: { TopToolBox, Main, TableWrapper },
  
    setup() {
      const { state, dispatch } = useStore();
      const deviceId = ref(null);
      const searchData = computed(() => state.headerSearchData);
      const orders = computed(() => state.myDevice.data);
  
      const item = computed(() => state.myDevice.data);
      const stateValue = ref('');
      const filterKey = ref(['Shipped', 'Awaiting Shipment', 'Canceled']);
  
      const handleChangeForFilter = (e) => {
        dispatch('orderFilter', { column: 'status', value: e.target.value });
      };
  
      const dataSource = computed(() =>
        orders.value.map((value) => {
          const { category, purpose, project, manageDep, user, deviceId, description, img } = value;
          return {
            product: (
              <div class="user-info">
                <figure>
                    <img style={{ width: '50px' }} src={require(`@/static/img/${img}`)} alt="" />
                </figure>
                <figcaption>
                    <sdHeading as="h6">
                        {deviceId}
                    </sdHeading>
                    <span class="user-designation">{category}</span>
                </figcaption>
              </div>
            ),
            user: <span class="customer-name">{user}</span>,
            manageDep: <span class="customer-name">{manageDep}</span>,
            project: <span class="customer-name">{project}</span>,
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
                  <sdButton class="btn-icon" type="link" to="#" shape="circle">
                    <sdFeatherIcons type="edit" size={16} title="수정" />
                  </sdButton>
                  <sdButton class="btn-icon" type="primary" to="#" shape="circle" >
                    <sdFeatherIcons type="rotate-ccw" size={16} title="반납" />
                  </sdButton>
                  <sdButton class="btn-icon" type="danger" to="#" shape="circle">
                    <sdFeatherIcons type="trash-2" size={16} title="폐기" />
                  </sdButton>
                </>
              </div>
            ),
          };
        }),
      );
      
      return {
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
  