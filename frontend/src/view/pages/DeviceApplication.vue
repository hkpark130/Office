<template>
    <sdPageHeader title="장비 사용 신청"></sdPageHeader>
    <Main>
      <a-row :gutter="15">
        <a-col :xs="24">
          <sdCards headless>
            <a-row :gutter="25" justify="center">
              <a-col :xxl="12" :md="16" :sm="24" :xs="24">
                <AddProductForm>
                  <a-form
                    :ref="formRef"
                    style="width: 100%"
                    :model="formState"
                    @finish="handleFinish"
                    @finishFailed="handleFinishFailed"
                    :layout="formState.layout"
                  >
                    <BasicFormWrapper>
                      <div class="add-product-block">
                        <a-row :gutter="15">
                          <a-col :xs="24">
                            <div class="add-product-content">
                              <sdCards title="장비 사용 신청">
                                <a-form-item label="관리번호" required>
                                  <a-input v-model:value="formState.deviceId" disabled/>
                                </a-form-item>

                                <a-form-item
                                  name="category"
                                  :initialValue="formState.categoryName"
                                  label="품목"
                                  required
                                >
                                  <a-select
                                    v-model:value="formState.category"
                                    style="width: 100%" disabled
                                  >
                                    <a-select-option
                                      v-for="category in categories"
                                      :key="category.id"
                                      :value="category.name"
                                    >{{ category.name }}</a-select-option>
                                  </a-select>
                                </a-form-item>

                                <a-row :gutter="15">
                                  <a-col :span="12">
                                    <a-form-item label="사용자" name="userName" required>
                                      <a-input v-model:value="formState.realUser" :disabled="disabled" @change="onChangeUser"/>
                                    </a-form-item>
                                  </a-col>

                                  <a-col :span="12">
                                    <a-form-item name="auto" label="외부인력 사용여부">
                                      <a-radio-group v-model:value="formState.auto" @change="onChange">
                                        <a-radio value="auto">자동 입력</a-radio>
                                        <a-radio value="manual">직접 입력</a-radio>
                                      </a-radio-group>
                                    </a-form-item>
                                  </a-col>
                                </a-row>

                                <a-form-item
                                  name="manageDep"
                                  initialValue=""
                                  label="관리부서"
                                >
                                  <a-select
                                    v-model:value="formState.manageDep"
                                    style="width: 100%"
                                  >
                                    <a-select-option
                                      v-for="department in departments"
                                      :key="department.id"
                                      :value="department.name"
                                    >{{ department.name }}</a-select-option>
                                  </a-select>
                                </a-form-item>

                                <a-form-item
                                name="projectName"
                                initialValue=""
                                label="프로젝트"
                                @click="() => openPopover()"
                              >
                                <sdPopover
                                  :placement="!rtl ? 'bottomLeft' : 'bottomRight'"
                                  :visible="popoverVisible"
                                  title="프로젝트 리스트"
                                  action="click"
                                >
                                  <template v-slot:content>
                                    <div>
                                      <a @click="() => onClickSearchList(item.name)" v-for="item in filteredData" :key="item.name" to="#">
                                        {{ item.printName }}
                                      </a>
                                      <a v-if="filteredData.length === 0" to="#"> Data Not Found..... </a>
                                    </div>
                                  </template>
                                  <a-input v-model:value="projectTmp" placeholder="Search..." @input="(e) => search(e, searchData)"  @keydown.enter.prevent/>
                                </sdPopover>
                                
                                <span>선택된 프로젝트: <b>{{ formState.projectName }}</b></span>
                              </a-form-item>

                                <a-form-item
                                  name="description"
                                  label="비고"
                                >
                                  <a-textarea
                                    v-model:value="formState.description"
                                    :rows="5"
                                    disabled
                                  />
                                </a-form-item>

                                <a-form-item
                                  name="reason"
                                  label="사유"
                                  required
                                >
                                  <a-textarea
                                    v-model:value="formState.reason"
                                    :rows="5"
                                  />
                                </a-form-item>

                                <a-form-item
                                  name="deadline"
                                  label="마감일/사용예정일"
                                  required
                                >
                                  <DatePickerWrap>
                                    <DatePickerWrapper>
                                      <a-date-picker 
                                        :disabledDate="disabledDate"
                                        v-model:value="formState.deadline"
                                        style="width: 100%"
                                      />
                                    </DatePickerWrapper>
                                  </DatePickerWrap>
                                </a-form-item>
                              </sdCards>
                            </div>
                          </a-col>
                        </a-row>
                      </div>

                      <div class="add-form-action">
                        <a-form-item>
                          <sdButton class="btn-cancel" size="large" @click.prevent="handleCancel">
                            Cancel
                          </sdButton>
                          <sdButton
                            size="large"
                            htmlType="submit"
                            type="primary"
                            raised
                          >
                            Save
                          </sdButton>
                        </a-form-item>
                      </div>
                    </BasicFormWrapper>
                  </a-form>
                </AddProductForm>
              </a-col>
            </a-row>
          </sdCards>
        </a-col>
      </a-row>
    </Main>
  </template>
  <script lang="jsx">
  import { Main, BasicFormWrapper, DatePickerWrapper } from "../styled";
  import { DatePickerWrap } from './ui-elements-styled';
  import { AddProductForm } from "./style";
  import { computed, ref, reactive, defineComponent, watch, toRef } from "vue";
  import { useRouter } from 'vue-router';
  import { useStore } from 'vuex';
  import { getUserD } from './getActivities';
  import { projectList } from '@/vuex/modules/projects/load-data';
  
  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm, DatePickerWrapper, DatePickerWrap },
    data() {
      const { dispatch } = useStore();
      dispatch('fetchCategoryList');
      dispatch('fetchDepartmentList');
      dispatch('fetchProjectList');
    },
    setup() {
      const { state, dispatch } = useStore();
      const router = useRouter();
      const { push, go } = useRouter();
      const submitValues = ref({});
      const formRef = ref();
      const projectTmp = ref();
      state.getUser.data = getUserD.data;

      const combinedArray = toRef(projectList.data.map(item => {
        return {
          name: `${item.name}`,
          code: `${item.code}`,
          printName: `${item.name} ${item.code}`,
        };
      }));
      const searchData = toRef(combinedArray.value);
      const filteredData = toRef(combinedArray.value);

      const search = (e, searchDatas) => {
        const data = searchDatas.filter((item) => {
          return item.printName.includes(e.target.value);
        });
        filteredData.value = data;
      };
      
      const categories = computed(() => state.caregoryList.data);
      const departments = computed(() => state.departmentList.data);
      const projects = computed(() => state.projectList.data);
      const getDeviceById = computed(() => state.deviceById.getDeviceData);
      const getUser = computed(() => state.getUser.data);
      dispatch('getDeviceById', router.currentRoute.value.params.deviceId).then(() => {
        getDeviceById.value = state.deviceById.getDeviceData;
      });

      watch(getDeviceById, (newValue) => {
        if (newValue) {
          formState.category = newValue.categoryName;
          formState.price = newValue.price;
          formState.status = newValue.status;
          formState.manageDep = newValue.manageDep && newValue.manageDep.name ? newValue.manageDep.name : "";
          formState.project = newValue.projectId && newValue.projectId.name ? newValue.projectId.name : "";
          formState.description = newValue.description;
          formState.projectName = formState.project;
          formState.departmentName = formState.manageDep;
        }
      });
  
      const formState = reactive({
        deviceId: router.currentRoute.value.params.deviceId,
        category: getDeviceById.value.categoryName,
        price: getDeviceById.value.price,
        auto: "auto",
        status: getDeviceById.value.status,
        manageDep: "",
        project: "",
        projectName: "본사",
        departmentName: "경영지원부",
        userName: getUser.value.name,
        realUser: getUser.value.name,
        reason: "",
        description: getDeviceById.value.description,
        type: "대여",
        isUsable: false,
        layout: "vertical",
        deadline: "",
      });

      const disabledDate = (current) => {
        return current && current.valueOf() < Date.now();
      }
  
      const handleFinish = () => {
        dispatch('submitDeviceApplicationPost', formState).then(() => {
          alert('신청되었습니다.');
          push('/');
        }).catch((error) => {
          throw new Error("에러 발생: " + error);
        });
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };
  
      const handleSubmit = (values) => {
        submitValues.value = values;
      };

      const disabled = ref(true);

      const onChange = (check) => {
        if (check.target.value === "auto") {
          disabled.value = true;
          formState.realUser = formState.userName;
        } else {
          disabled.value = false;
          
        }
      };

      const onChangeUser = (name) => {
        formState.realUser = name.target.value;
      }

      const handleCancel = () => {
        go(-1);
      };
  
      return {
        submitValues,
        formState,
        handleFinish,
        handleFinishFailed,
        handleSubmit,
        formRef,
        categories,
        projects,
        departments,
        onChange,
        disabled,
        disabledDate,
        handleCancel,
        onChangeUser,
        projectTmp,
        searchData,
        filteredData,
        search,
      };
    },
  });
  
  export default AddProduct;
  </script>
  