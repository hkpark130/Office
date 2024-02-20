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
                  <router-link :to="{ 
                      name: 'device-application', 
                      params: { deviceId: `${deviceId}` }, 
                    }">
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
import { computed, ref, defineComponent, onMounted } from 'vue';
import { useStore } from 'vuex';
import Tag from '../../components/tags/Tag';
import { useRouter } from 'vue-router';
import { availableDeviceList } from './getAvailableDeviceList';

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
      const aValue = a.categoryName?a.categoryName:'';
      const bValue = b.categoryName?b.categoryName:'';
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
    title: '태그',
    dataIndex: 'tag',
    key: 'tag',
    ellipsis: true,
  },
  {
    title: '비고',
    dataIndex: 'memo',
    key: 'memo',
    width: '20%',
  },
  {
    title: '신청정보',
    dataIndex: 'info',
    key: 'approvalInfo',
    align: 'center',
    sorter: (a, b) => {
      const aValue = a.infoKey?a.infoKey:'';
      const bValue = b.infoKey?b.infoKey:'';
      return sortWithNullCheck(aValue, bValue);
    },
  },
];

const filterColumns = columns.filter((column, index) => {
  return column.key !== 'memo' && column.key !== 'id' && column.key !== 'tag' && index !== columns.length - 1;
});

const AvailableDevices = defineComponent({
  name: 'AvailableDevices',
  components: { TopToolBox, Main, TableWrapper },

  setup() {
    const response = availableDeviceList.data;
    const { state, dispatch } = useStore();
    state.devices.data = response;
    const deviceId = ref(null);
    const filterKey = ref('categoryName');
    const searchData = computed(() => state.headerSearchData);
    const router = useRouter();
    const orders = computed(() => state.devices.data);
    const item = computed(() => state.devices.data);
    dispatch("fetchAvailableDeviceList").then(() => {
      orders.value = computed(() => state.devices.data);
      item.value = computed(() => state.devices.data);
    });

    const stateValue = ref('');
    const filterVal = ref([]);

    if ( router.currentRoute.value.params.category ) {
      dispatch('deviceFilter', { column: 'categoryName', 
      value: router.currentRoute.value.params.category });
    }

    onMounted(() => {
      onSorting('categoryName');
    });

    const handleChangeForFilter = (e) => {
      dispatch('deviceFilter', { column: filterKey.value, value: e.target.value, response: response });
    };

    const dataSource = computed(() =>
      orders.value.map((value) => {
        const { categoryId, approvalInfo, purpose, spec, id, description, approvalType, deadline, status, tags } = value;
        const truncatedDescription = description.length > 10 ? description.substring(0, 10) + '...' : description;
        const deadlineDate = (deadline === null)?null:new Date(deadline).toLocaleDateString('ko-KR',
              {
                year: 'numeric',
                month: 'long',
                day: 'numeric',
              });
        const tagComponents = tags.map((tag, index) => (
          <Tag key={index} tagType="colorful" color="blue" data={tag}/>
        ));
        return {
          key: id, // radio 선택시 기준 값
          categoryName: categoryId.name,
          status: status,
          purposeKey: purpose,
          approvalType: approvalType,
          infoKey: approvalInfo,
          id: <span class="order-id">{id}</span>,
          category: <span class="customer-name">{ categoryId.name }</span>,
          info: (
            <span
              class={`status ${
                (approvalInfo === '승인대기' && approvalType === '반납') ? 'waiting' :
                'Success'
              }`}
            >
              {(approvalInfo === '승인대기' && approvalType === '반납') ? `반납예정 ${deadlineDate}`:"사용가능" }
            </span>
          ),
          tag: (
            <div class="taglist-wrap" style="display: flex; flex-wrap: wrap;">
              {tagComponents}
            </div>
          ),
          purpose: (
            <div>
              <span class="ordered-amount spnDetails">{purpose}</span>
              <span class="spnTooltip">
                  {spec}
              </span>
            </div>
          ),
          memo: (
            <div>
              <span class="ordered-date">{truncatedDescription}</span>
              <span class="spnTooltip" >
                  {description}
              </span>
            </div>
          ),
          
        };
      }),
    );

    const rowSelection = {
      onChange: (selectedRowKeys) => {
        deviceId.value = selectedRowKeys[0];
      },
      getCheckboxProps: (record) => {
        return {
            disabled: record.approvalType === '반납'
        };
      },
      type: "radio", //기본값이 체크박스
    };

      const onSorting = (selectedItems) => {
        filterKey.value = selectedItems;
        filterVal.value = [...new Set(item.value.map((item) => item[selectedItems]).filter(val => val !== null))]; // 중복 및 null 제거
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
  
