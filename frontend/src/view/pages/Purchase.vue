<template>
    <sdPageHeader title="구매 신청"></sdPageHeader>
    <Main>
      <a-row :gutter="15">
        <a-col :xs="24">
          <sdCards headless>
            <a-row :gutter="25" justify="center">
              <a-col :xxl="12" :md="16" :sm="24" :xs="24">
                <AddProductForm>
                  <a-form
                    ref="formRef"
                    style="width: 100%"
                    :model="formState"
                    :layout="formState.layout"
                  >
                    <BasicFormWrapper>
                      <div class="add-product-block">
                        <a-row :gutter="15">
                          <a-col :xs="24">
                            <div class="add-product-content">
                              <sdCards title="구매 신청">
                                <a-form-item
                                  name="category"
                                  :initialValue="formState.category"
                                  label="품목"
                                >
                                  <a-select
                                    v-model:value="formState.category"
                                    style="width: 100%"
                                  >
                                    <a-select-option value=""
                                        >Please Select</a-select-option
                                      >
                                    <a-select-option
                                      v-for="category in categories"
                                      :key="category.id"
                                      :value="category.name"
                                    >{{ category.name }}</a-select-option>
                                  </a-select>
                                </a-form-item>

                                <a-form-item label="신청자" name="userName" required>
                                  <a-input v-model:value="formState.userName" disabled/>
                                </a-form-item>

                                <a-form-item
                                  name="purpose"
                                  initialValue=""
                                  label="용도"
                                >
                                  <a-select
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

                                <a-form-item name="price" label="가격">
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
  
                                <a-form-item
                                  name="spec"
                                  label="사양"
                                >
                                  <a-textarea
                                    v-model:value="formState.spec"
                                    :rows="3"
                                  />
                                </a-form-item>

                                <a-form-item
                                  name="project"
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
                                    <a-input v-model:value="projectTmp" placeholder="Search..." @input="(e) => search(e, searchData)" @keydown.enter.prevent/>
                                  </sdPopover>
                                  
                                  <span>선택된 프로젝트: <b>{{ formState.project }}</b></span>
                                </a-form-item>
  
                                <a-form-item
                                  name="reason"
                                  label="사유"
                                >
                                  <a-textarea
                                    v-model:value="formState.reason"
                                    :rows="5"
                                    placeholder="구매링크를 기재해주세요."
                                  />
                                </a-form-item>

                                <a-form-item
                                  name="deadline"
                                  label="마감일"
                                  required
                                >
                                  <DatePickerWrap>
                                    <DatePickerWrapper>
                                      <a-date-picker 
                                        :disabledDate="disabledDate"
                                        v-model:value="formState.deadline"
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
                            @click="handleFinish"
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
  import { toRef, ref, reactive, defineComponent, computed } from "vue";
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';
  
  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm, DatePickerWrapper, DatePickerWrap },
    setup() {
      const { state, dispatch } = useStore();
      const file = ref(null);
      const list = ref(null);
      const submitValues = ref({});
      const formRef = ref();
      const projectTmp = ref();

      const { push, go } = useRouter();
      const categories = computed(() => state.caregoryList.data);
      const departments = computed(() => state.departmentList.data);
      const projects = computed(() => state.projectList.data);
      const getUser = computed(() => state.getUser.data);

      const searchData = toRef(projects.value);
      const filteredData = toRef(projects.value);

      const search = (e, searchDatas) => {
        const data = searchDatas.filter((item) => {
          return item.name.includes(e.target.value);
        });
        filteredData.value = data;
      };

      const formState = reactive({
        category: "노트북",
        price: 0,
        project: "본사",
        purpose: "개발",
        userName: getUser.value.name,
        reason: "",
        deadline: "",
        type: "구매",
        layout: "vertical",
        file: "",        
      });

      const disabledDate = (current) => {
        return current && current.valueOf() < Date.now();
      }

      const onClickSearchList = (v) => {
        formState.project = v;
        projectTmp.value = v;
      }

      const handleFinish = () => {
        const reason = formState.reason;
        formState.reason = "품목: "+formState.category+"\n";
        formState.reason += "비용: "+formState.price+"\n";
        formState.reason += "용도: "+formState.purpose+"\n";
        formState.reason += "사유: "+reason;

        dispatch('submitDevicePurchasePost', formState);
        alert('구매 신청이 완료되었습니다.');
        push('/');
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };

      const handleCancel = () => {
        go(-1);
      };
  
      return {
        file,
        list,
        submitValues,
        formState,
        handleFinish,
        handleFinishFailed,
        formRef,
        projects,
        categories,
        departments,
        disabledDate,
        searchData,
        filteredData,
        search,
        onClickSearchList,
        projectTmp,
        handleCancel,
      };
    },
  });
  
  export default AddProduct;
  </script>
  