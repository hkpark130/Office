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
                              <sdCards title="구매 신청">
                                <a-form-item label="긴급도" name="urgency">
                                  <a-radio-group v-model:value="formState.urgency">
                                    <a-radio value="normal">보통</a-radio>
                                    <a-radio value="urgent">긴급</a-radio>
                                  </a-radio-group>
                                </a-form-item>

                                <a-form-item
                                  name="category"
                                  :initialValue="formState.categoryName"
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
                                  <a-select
                                    v-model:value="formState.project"
                                    style="width: 100%"
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
                                  label="상세"
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
  import { ref, reactive, defineComponent, computed } from "vue";
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';
  
  const AddProduct = defineComponent({
    // props: ['deviceId'],
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    // setup(props) {
    setup() {
      const { state, dispatch } = useStore();
      const file = ref(null);
      const list = ref(null);
      const submitValues = ref({});
      const formRef = ref();

      const { push } = useRouter();
      const categories = computed(() => state.caregoryList.data);
      const departments = computed(() => state.departmentList.data);
      const projects = computed(() => state.projectList.data);
      const getUser = computed(() => state.getUser.data);

      const formState = reactive({
        category: "",
        price: 0,
        urgency: "normal",
        project: "",
        purpose: "개발",
        userName: getUser.value.name,
        reason: "",
        type: "구매",
        layout: "vertical",
        file: "",
      });
  
      const handleFinish = () => {
        console.log(formState);
        dispatch('submitDevicePurchasePost', formState);
        alert('구매 신청이 완료되었습니다.');
        push('/');
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };
  
      const handleSubmit = (values) => {
        submitValues.value = values;
      };
  
      return {
        file,
        list,
        submitValues,
        formState,
        handleFinish,
        handleFinishFailed,
        handleSubmit,
        formRef,
        projects,
        categories,
        departments,

      };
    },
  });
  
  export default AddProduct;
  </script>
  