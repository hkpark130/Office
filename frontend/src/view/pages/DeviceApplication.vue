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
                                <a-form-item label="긴급도" name="urgency">
                                  <a-radio-group v-model:value="formState.urgency">
                                    <a-radio value="normal">보통</a-radio>
                                    <a-radio value="urgent">긴급</a-radio>
                                  </a-radio-group>
                                </a-form-item>

                                <a-form-item label="관리번호">
                                  <a-input v-model:value="formState.deviceId" disabled/>
                                </a-form-item>

                                <a-form-item
                                  name="category"
                                  :initialValue="formState.categoryName"
                                  label="품목"
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

                                <a-form-item label="신청자" name="userName" required>
                                  <a-input v-model:value="formState.userName" :disabled="disabled"/>
                                  <a-radio-group v-model:value="formState.auto" @change="onChange">
                                    <a-radio value="auto">자동 입력</a-radio>
                                    <a-radio value="manual">직접 입력</a-radio>
                                  </a-radio-group>
                                </a-form-item>

                                <a-form-item
                                  name="manageDep"
                                  initialValue=""
                                  label="관리부서"
                                >
                                  <a-select
                                    v-model:value="formState.manageDep"
                                    style="width: 100%" disabled
                                  >
                                    <a-select-option
                                      v-for="department in departments"
                                      :key="department.id"
                                      :value="department.name"
                                    >{{ department.name }}</a-select-option>
                                  </a-select>
                                </a-form-item>

                                <a-form-item
                                  name="project"
                                  initialValue=""
                                  label="프로젝트"
                                >
                                  <a-select
                                    v-model:value="formState.project"
                                    style="width: 100%" disabled
                                  >
                                    <a-select-option
                                      v-for="project in projects"
                                      :key="project.id"
                                      :value="project.name"
                                    >{{ project.name }}</a-select-option>
                                  </a-select>
                                </a-form-item>

                                <a-form-item
                                  name="reason"
                                  label="신청사유"
                                  required
                                >
                                  <a-textarea
                                    v-model:value="formState.reason"
                                    :rows="5"
                                  />
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
  import { Main, BasicFormWrapper } from "../styled";
  import { AddProductForm } from "./style";
  import { computed, ref, reactive, defineComponent, watch } from "vue";
  import { useRouter } from 'vue-router';
  import { useStore } from 'vuex';
  
  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    setup() {
      const { state, dispatch } = useStore();
      const router = useRouter();
      // const { push } = useRouter();
      const file = ref(null);
      const list = ref(null);
      const submitValues = ref({});
      const formRef = ref();

      dispatch('getDeviceById', router.currentRoute.value.params.deviceId);
      
      const categories = computed(() => state.caregoryList.data);
      const departments = computed(() => state.departmentList.data);
      const projects = computed(() => state.projectList.data);
      const getDeviceById = computed(() => state.deviceById.data);
      const getUser = computed(() => state.getUser.data);

      watch(getDeviceById, (newValue) => {
        if (newValue) {
          formState.category = newValue.categoryName;
          formState.price = newValue.price;
          formState.status = newValue.status;
          formState.manageDep = newValue.manageDep.name;
          formState.project = newValue.projectId && newValue.projectId.name ? newValue.projectId.name : "";
        }
      });
  
      const formState = reactive({
        deviceId: router.currentRoute.value.params.deviceId,
        category: getDeviceById.value.categoryName,
        price: getDeviceById.value.price,
        auto: "auto",
        urgency: "normal", // normal, urgent
        status: getDeviceById.value.status,
        manageDep: "",
        project: "",
        userName: getUser.value.name,
        reason: "",
        type: "대여",
        layout: "vertical",
      });
  
      const handleFinish = () => {
        dispatch('submitDeviceApplicationPost', formState);
        alert('신청되었습니다.');
        // push('/');
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };
  
      const handleSubmit = (values) => {
        submitValues.value = values;
      };
  
      const rules = {
        name: [
          {
            required: true,
            message: "Please input Activity name",
            trigger: "blur",
          },
        ],
      };

      const disabled = ref(true);

      const onChange = (check) => {
        if (check.target.value === "auto") {
          disabled.value = true;
        } else {
          disabled.value = false;
        }
      };
  
      const resetForm = () => {
        formRef.value.ruleformState.resetFields();
      };
  
      return {
        rules,
        file,
        list,
        resetForm,
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
      };
    },
  });
  
  export default AddProduct;
  </script>
  