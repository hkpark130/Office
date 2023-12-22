<template>
    <sdPageHeader title="장비 등록"></sdPageHeader>
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
                              <sdCards title="About Device">
                                <a-form-item
                                  name="category"
                                  :initialValue="formState.categoryName"
                                  label="품목"
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

                                <a-form-item label="관리번호">
                                  <div class="input-prepend-wrap">
                                    <a-input name="id" v-model:value="formState.id"/>
                                    <span class="input-append">
                                      <button class="btn-icon" type="button" :onClick="checkDup" transparented>중복체크</button> 
                                      <sdFeatherIcons v-if="checkFinished == true" 
                                      :style="{
                                            backgroundColor: '#20C997',
                                        }" type="circle" size="44" />
                                    </span>
                                  </div>
                                </a-form-item>

                                <a-form-item
                                  name="projectName"
                                  initialValue=""
                                  label="프로젝트"
                                >
                                  <a-select
                                    v-model:value="formState.projectName"
                                    style="width: 100%"
                                  >
                                    <a-select-option value="본사"
                                        >본사</a-select-option
                                      >
                                    <a-select-option
                                      v-for="project in projects"
                                      :key="project.id"
                                      :value="project.name"
                                    >{{ project.name }}</a-select-option>
                                  </a-select>
                                </a-form-item>

                                <a-form-item
                                  name="purpose"
                                  label="용도"
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

                                <a-form-item label="개발 가능 여부" name="status">
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
  import { ref, reactive, defineComponent, computed, watch } from "vue";
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';

  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    setup() {
      const submitValues = ref({});
      const formRef = ref();
      const { state, dispatch } = useStore();
      const { push } = useRouter();
      const checkFinished = ref(false);

      const categories = computed(() => state.caregoryList.data);
      const projects = computed(() => state.projectList.data);
  
      const formState = reactive({
        id: "",
        categoryName: "노트북",
        price: 0,
        projectName: "본사",
        status: "true",
        purpose: "개발",
        description: "",
        model: "",
        company: "",
        sn: "",
        spec: "",
        layout: "vertical",
      });

      watch(() => formState.id, (newId, oldId) => {
        if (newId !== oldId) {
          checkFinished.value = false;
        }
      });
  
      const handleFinish = () => {
        if (!checkFinished.value) {
          alert('관리번호 중복체크를 해주세요.');
          return;
        }

        console.log(formState);

        // dispatch('submitAddDevicePost', formState);
        alert('등록되었습니다.');
        push('/');
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };

      const checkDup = async () => {
        if (formState.id === "") {
          alert('관리번호를 입력해주세요.');
          return;
        }

        const response = await dispatch('checkDuplication', formState.id);
        if (response) {
          alert('사용 가능한 관리번호입니다.');
          checkFinished.value = true;
        } else {
          alert('이미 사용 중인 관리번호입니다.');
          checkFinished.value = false;
        }

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
  
      const resetForm = () => {
        formRef.value.ruleformState.resetFields();
      };
  
      return {
        checkDup,
        projects,
        categories,
        checkFinished,
        rules,
        resetForm,
        submitValues,
        formState,
        handleFinish,
        handleFinishFailed,
        handleSubmit,
        formRef,
      };
    },
  });
  
  export default AddProduct;
  </script>
  