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
          <a-form class="adTodo-form" name="todoAdd" :model="formState" @finish="editMyDevice">
            <a-form-item
              name="description"
              label="비고"
            >
              <a-textarea
                v-model:value="formState.description"
                :rows="5"
              />
            </a-form-item>
            <br />
            <br />
            <sdButton htmlType="submit" class="btn-adTodo" type="primary" size="large">
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
    title: 'Product',
    dataIndex: 'category',
    key: 'categoryName',
    sorter: (a, b) => {
      // 카테고리별 정렬임, Device ID가 아님
      const aValue = a.categoryNameKey?a.categoryNameKey:'';
      const bValue = b.categoryNameKey?b.categoryNameKey:'';
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
    title: '용도',
    dataIndex: 'purpose',
    key: 'purpose',
    width: '5%',
    sorter: (a, b) => {
      const aValue = a.purposeKey?a.purposeKey:'';
      const bValue = b.purposeKey?b.purposeKey:'';
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

    const orders = computed(() => state.myDevice.data);
    const item = computed(() => state.myDevice.data);
    // const router = useRouter();
    const { push } = useRouter();

    const stateValue = ref('');
    const filterKey = ref('categoryName');
    const filterVal = ref([]);
    await dispatch('getMyDevices', getUser.value.name);
    const fetchData = async () => {
      await dispatch('getMyDevices', getUser.value.name);
      onSorting('categoryName');
    };

    fetchData();

    const handleChangeForFilter = (e) => {
      dispatch('myDeviceFilter', { column: filterKey.value, value: e.target.value, name: getUser.value.name });
    };

    const editMyDevice = () => {
      dispatch('editMyDevice', formState).then(() => {
        alert('수정되었습니다.');
        location.reload();
      });
    };

    const formState = reactive({
      visible: false,
      id: '',
      status: '',
      description: '',
    });

    const showModal = (row) => {
      formState.visible = true;
      formState.id = row.id;
      formState.status = row.status;
      formState.description = row.description;
    };

    const onCancel = () => {
      formState.visible = false;
    };

    const handleCancel = () => {
      onCancel();
    };

    const dataSource = computed(() =>
      orders.value.map((value) => {
        const { categoryId, categoryName, purpose, projectName, manageDepName, username, id, description, 
          realUser, spec, approvalType, approvalInfo } = value;
        const truncatedDescription = description.length > 10 ? description.substring(0, 10) + '...' : description;
        let action = approvalType+"중";

        if (approvalInfo === "승인완료") {
          action = (
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
          );
        }

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
          categoryNameKey: categoryName,
          user: <span class="customer-name">{(realUser)?realUser:username}</span>,
          userKey: username,
          manageDep: <span class="customer-name">{manageDepName}</span>,
          manageDepKey: manageDepName,
          project: <span class="customer-name">{projectName}</span>,
          projectKey: projectName,
          purpose: (
            <div>
              <span class="ordered-amount spnDetails">{purpose}</span>
              <span class="spnTooltip">
                {spec}
              </span>
            </div>
          ),
          purposeKey: purpose,
          description: (
            <div>
              <span class="ordered-date">{truncatedDescription}</span>
              <span class="spnTooltip" >
                  {description}
              </span>
            </div>
          ),
          action: action,
        };
      }),
    );

    const onSorting = (selectedItems) => {
      filterKey.value = selectedItems;
      filterVal.value = [...new Set(item.value.map((item) => item[selectedItems]).filter(val => val !== null))]; // 중복 및 null 제거
    };
    
    return {
      formState,
      deviceId,
      showModal,
      onCancel,
      handleCancel,
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
      editMyDevice,
    };
  },
});

export default Orders;
</script>
