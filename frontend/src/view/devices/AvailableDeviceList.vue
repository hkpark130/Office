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
                :type="radio"
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
  import { useRouter } from 'vue-router';
  
  const columns = [
    {
      title: '품목',
      dataIndex: 'category',
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
      key: 'userId',
    },
    {
      title: '용도',
      dataIndex: 'purpose',
      key: 'purpose',
    },
    {
      title: '태그',
      dataIndex: 'tag',
      key: 'tag',
    },
    {
      title: '비고',
      dataIndex: 'memo',
      key: 'memo',
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
    return column.key !== 'memo' && column.key !== 'id' && index !== columns.length - 1;
  });
  
  const AvailableDevices = defineComponent({
    name: 'AvailableDevices',
    components: { TopToolBox, Main, TableWrapper },
  
    setup() {
      const { state, dispatch } = useStore();
      const deviceId = ref(null);
      const filterKey = ref('categoryName');
      const searchData = computed(() => state.headerSearchData);
      const orders = computed(() => state.devices.data);
      const router = useRouter();
  
      const item = computed(() => state.devices.data);
      const stateValue = ref('');
      const filterVal = ref(['노트북', '모니터', '서버']);
  
      if ( router.currentRoute.value.params.category ) {
        dispatch('deviceFilter', { column: 'categoryName', 
        value: router.currentRoute.value.params.category });
      }

      const handleChangeForFilter = (e) => {
        dispatch('deviceFilter', { column: filterKey.value, value: e.target.value });
      };
  
      const dataSource = computed(() =>
        orders.value.map((value) => {
          const { categoryId, approvalInfo, purpose, spec, userId, id, tag, description } = value;
          return {
            key: id, // radio 선택시 기준 값
            id: <span class="order-id">{id}</span>,
            category: <span class="customer-name">{ categoryId.name }</span>,
            user: <span class="customer-name">{ userId }</span>,
            info: (
              <span
                class={`status ${
                  approvalInfo === '사용가능' ? 'Success' : 
                  approvalInfo === '승인대기' ? 'waiting' : 
                  approvalInfo === '등록대기' ? 'warning' : 
                  'error'
                }`}
              >
                {approvalInfo}
              </span>
            ),
            tag: <span class="ordered-amount">{tag}</span>,
            purpose: (
              <div>
                <span class="ordered-amount spnDetails">{purpose}</span>
                <span class="spnTooltip">
                    {spec}
                </span>
              </div>
            ),
            memo: <span class="ordered-date">{description}</span>,
            action: (
              <div class="table-actions">
                <>
                  <sdButton class="btn-icon" type="primary" to="#" shape="circle" >
                    <sdFeatherIcons type="eye" size={16} title="상세정보" />
                  </sdButton>
                  <sdButton class="btn-icon" type="info" to="#" shape="circle">
                    <sdFeatherIcons type="edit" size={16} title="편집" />
                  </sdButton>
                  <sdButton class="btn-icon" type="danger" to="#" shape="circle">
                    <sdFeatherIcons type="trash-2" size={16} title="삭제" />
                  </sdButton>
                </>
              </div>
            ),
          };
        }),
      );

      const rowSelection = {
        onChange: (selectedRowKeys) => {
          deviceId.value = selectedRowKeys[0];
        },
        type: "radio", //기본값이 체크박스
      };

      const onSorting = (selectedItems) => {
        filterKey.value = selectedItems;
        filterVal.value = [...new Set(state.devices.originData.map((item) => item[selectedItems]))]; // 중복 제거
      };
      
      return {
        deviceId,
        rowSelection,
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
      };
    },
  });
  
  export default AvailableDevices;
  </script>
  