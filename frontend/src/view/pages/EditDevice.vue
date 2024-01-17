<template>
    <sdPageHeader title="장비 편집"></sdPageHeader>
    <Main>
      <a-row :gutter="15">
        <a-col :xs="24">
          <sdCards headless>
            <a-row :gutter="25" justify="center">
              <a-col :xxl="12" :md="16" :sm="24" :xs="24">
                <a-row class="ant-row-middle" justify="center">
                  <a-col :md="18" :xs="23" >
                    <a-input placeholder="편집할 장비의 `관리번호` 를 입력해주세요." v-model:value="searcgDeviceId" />
                  </a-col>
                  <a-col :md="1" :xs="1" :class="rtl ? 'text-left' : 'text-right'">
                    <a to="#" @click="searchDevice">
                      <span class="certain-category-icon">
                        <sdFeatherIcons type="search" size="25"/>
                      </span>
                    </a>
                  </a-col>
                </a-row>

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
                              <sdCards title="About Device">
                                <a-form-item
                                  name="categoryName"
                                  :initialValue="formState.categoryName"
                                  label="품목"
                                  required
                                >
                                  <a-select
                                    v-model:value="formState.categoryName"
                                    style="width: 100%"
                                  >
                                    <a-select-option
                                      v-for="category in categories"
                                      :key="category.id"
                                      :value="category.name"
                                    >{{ category.name }}</a-select-option>
                                  </a-select>
                                </a-form-item>

                                <a-form-item label="관리번호" required>
                                  <div class="input-prepend-wrap">
                                    <a-input name="id" v-model:value="formState.id" disabled/>
                                  </div>
                                </a-form-item>

                                <a-form-item
                                  name="projectName"
                                  initialValue=""
                                  label="프로젝트"
                                >
                                  <sdPopover
                                    :placement="!rtl ? 'bottomLeft' : 'bottomRight'"
                                    v-model="visible"
                                    title="프로젝트 리스트"
                                    action="click"
                                  >
                                    <template v-slot:content>
                                      <div>
                                        <a @click="() => onClickSearchList(item.name)" v-for="item in filteredData" :key="item.name" to="#">
                                          {{ item.name }}
                                        </a>
                                        <a v-if="filteredData.length === 0" to="#"> Data Not Found..... </a>
                                      </div>
                                    </template>
                                    <a-input v-model:value="projectTmp" placeholder="Search..." @input="(e) => search(e, searchData)" />
                                  </sdPopover>
                                  
                                  <span>선택된 프로젝트: <b>{{ formState.projectName }}</b></span>
                                </a-form-item>

                                <a-form-item
                                  name="manageDepName"
                                  initialValue=""
                                  label="관리부서"
                                >
                                  <a-select
                                    v-model:value="formState.manageDepName"
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
                                  name="purpose"
                                  label="용도"
                                  required
                                >
                                  <a-select
                                    name="purpose"
                                    v-model:value="formState.purpose"
                                    style="width: 100%"
                                  >
                                    <a-select-option value="개발"
                                      >개발</a-select-option
                                    >
                                    <a-select-option value="사무"
                                      >사무</a-select-option
                                    >
                                  </a-select>
                                </a-form-item>

                                <a-form-item label="개발 가능 여부" name="status" required>
                                  <a-radio-group v-model:value="formState.status">
                                    <a-radio value="true">개발 가능</a-radio>
                                    <a-radio value="false">개발 불가능</a-radio>
                                  </a-radio-group>
                                </a-form-item>

                                <a-form-item
                                  name="spec"
                                  label="사양"
                                >
                                  <a-textarea
                                    v-model:value="formState.spec"
                                    :rows="5"
                                  />
                                </a-form-item>
  
                                <a-form-item name="price" label="금액">
                                  <div class="input-prepend-wrap">
                                    <span class="input-prepend">
                                      <sdFeatherIcons
                                        type="dollar-sign"
                                        size="14"
                                      />
                                    </span>
                                    <a-input-number
                                      v-model:value="formState.price"
                                      style="width: 100%"
                                    />
                                  </div>
                                </a-form-item>

                                <a-form-item label="모델명">
                                  <a-input name="model" v-model:value="formState.model"/>
                                </a-form-item>

                                <a-form-item label="제조사">
                                  <a-input name="company" v-model:value="formState.company"/>
                                </a-form-item>

                                <a-form-item label="S/N">
                                  <a-input name="sn" v-model:value="formState.sn"/>
                                </a-form-item>
  
                                <a-form-item
                                  name="description"
                                  label="비고"
                                >
                                  <a-textarea
                                    v-model:value="formState.description"
                                    :rows="5"
                                  />
                                </a-form-item>

                                <a-form-item
                                  name="purchaseDate"
                                  label="구입일자"
                                  required
                                >
                                  <DatePickerWrap>
                                    <DatePickerWrapper>
                                      <a-date-picker 
                                        v-model:value="formState.purchaseDate"
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
                          <sdButton class="btn-cancel" size="large">
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
  import { AddProductForm } from "./style";
  import { DatePickerWrap } from './ui-elements-styled';
  import { toRef, ref, reactive, defineComponent, computed, watch } from "vue";
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';
  import dayjs from 'dayjs';

  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm, DatePickerWrapper, DatePickerWrap },
    setup() {
      const submitValues = ref({});
      const formRef = ref();
      const { state, dispatch } = useStore();
      const { push } = useRouter();
      const projectTmp = ref();
      const searcgDeviceId = ref();

      const categories = computed(() => state.caregoryList.data);
      const projects = computed(() => state.projectList.data);
      const departments = computed(() => state.departmentList.data);

      const searchData = toRef(projects.value);
      const filteredData = toRef(projects.value);
      const getDeviceById = ref();

      const search = (e, searchDatas) => {
        const data = searchDatas.filter((item) => {
          return item.name.includes(e.target.value);
        });
        filteredData.value = data;
      };

      const searchDevice = async () => {
        const response = await dispatch('checkDuplication', searcgDeviceId.value);
        if (response) {
          alert('장비를 찾을 수 없습니다.');
          return;
        } else {
          formState.id = searcgDeviceId.value;
          await dispatch('getDeviceById', formState.id);
          getDeviceById.value = state.deviceById.getDeviceData;
        }
      };
  
      const formState = reactive({
        id: "",
        categoryName: "노트북",
        price: 0,
        projectName: "본사",
        manageDepName: "경영지원부",
        status: "true",
        purpose: "개발",
        description: "",
        model: "",
        company: "",
        sn: "",
        spec: "",
        auto: "manual",
        purchaseDate: "",
        layout: "vertical",
      });

      const onClickSearchList = (v) => {
        formState.projectName = v;
        projectTmp.value = v;
      }

      watch(() => getDeviceById.value, (newId, oldId) => {
        if (newId !== oldId) {
          formState.categoryName = getDeviceById.value.categoryName;
          formState.price = getDeviceById.value.price;
          formState.projectName = getDeviceById.value.projectName;
          formState.manageDepName = getDeviceById.value.manageDepName;
          formState.status = getDeviceById.value.status.toString();
          formState.purpose = getDeviceById.value.purpose;
          formState.description = getDeviceById.value.description;
          formState.model = getDeviceById.value.model;
          formState.company = getDeviceById.value.company;
          formState.spec = getDeviceById.value.spec;
          formState.sn = getDeviceById.value.sn;
          formState.purchaseDate = (getDeviceById.value.purchaseDate === null) ? 
            null : dayjs(getDeviceById.value.purchaseDate);
        }
      });
  
      const handleFinish = () => {
        if (!formState.id) {
          alert('관리번호를 입력해 주세요.');
          return;
        }
        
        dispatch('submitEditDevicePut', formState);
        alert('등록되었습니다.');
        push('/');
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };
  
      return {
        projects,
        categories,
        submitValues,
        formState,
        handleFinish,
        handleFinishFailed,
        formRef,
        searchData,
        filteredData,
        search,
        onClickSearchList,
        projectTmp,
        searchDevice,
        searcgDeviceId,
        departments,
      };
    },
  });
  
  export default AddProduct;
  </script>
  