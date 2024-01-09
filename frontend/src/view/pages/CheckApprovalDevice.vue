<template>
    <sdPageHeader title="승인 / 반려"></sdPageHeader>
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
                    :layout="formState.layout"
                  >
                    <BasicFormWrapper>
                      <div class="add-product-block">
                        <a-row :gutter="15">
                          <a-col :xs="24">
                            <div class="add-product-content">
                              <sdCards title="승인 / 반려">

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

                                <a-row :gutter="15">
                                  <a-col :span="12">
                                    <a-form-item label="신청자" name="userName" required>
                                      <a-input v-model:value="formState.userName" disabled/>
                                    </a-form-item>
                                  </a-col>
                                  <a-col :span="12">
                                    <a-form-item label="사용자" name="userName" required>
                                      <a-input v-model:value="formState.userName" disabled/>
                                    </a-form-item>
                                  </a-col>
                                </a-row>


                                <a-row :gutter="15">
                                  <a-col :span="12">
                                    <a-form-item
                                      name="purpose"
                                      label="용도"
                                    >
                                      <a-select
                                        name="purpose"
                                        v-model:value="formState.purpose"
                                      >
                                        <a-select-option value="개발"
                                          >개발</a-select-option
                                        >
                                        <a-select-option value="사무"
                                          >사무</a-select-option
                                        >
                                      </a-select>
                                    </a-form-item>
                                  </a-col>

                                  <a-col :span="12">
                                    <a-form-item label="상태" name="status" required>
                                      <a-radio-group v-model:value="formState.status">
                                        <a-radio :value=true>Y</a-radio>
                                        <a-radio :value=false>N</a-radio>
                                      </a-radio-group>
                                    </a-form-item>
                                  </a-col>
                                </a-row>

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
                              </sdCards>
                            </div>
                          </a-col>
                        </a-row>
                      </div>

                      <div class="add-form-action">
                        <a-form-item>
                          <sdButton 
                            type="danger" 
                            size="large"
                            @click="approvalReturn"
                          >
                            반려
                          </sdButton>
                          <sdButton
                            size="large"
                            htmlType="submit"
                            type="success"
                            @click="approvalFinish"
                            raised
                          >
                            승인
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

      <sdModal type="primary" title="장비 추가" :visible="modalFormState.visible" :footer="null" :onCancel="handleCancel">
        <div class="todo-modal">
          <BasicFormWrapper>
            <a-form class="adTodo-form" name="todoAdd" :model="modalFormState">
              <div class="add-product-block" style="overflow-y: auto;overflow-x: hidden;">
                <a-row :gutter="15">
                  <a-col :xs="24">
                    <div class="add-product-content">
                      <sdCards title="About Device">
                        <a-form-item
                          name="category"
                          :initialValue="modalFormState.category"
                          label="품목"
                        >
                          <a-select
                            v-model:value="modalFormState.category"
                            style="width: 100%"
                          >
                            <a-select-option
                              v-for="category in categories"
                              :key="category.id"
                              :value="category.name"
                            >{{ category.name }}</a-select-option>
                          </a-select>
                        </a-form-item>
                        <br/><br/>

                        <a-form-item label="관리번호">
                          <div class="input-prepend-wrap">
                            <a-input name="id" v-model:value="modalFormState.id" required/>
                            <span class="input-append">
                              <button class="btn-icon" type="button" :onClick="checkDup" transparented>중복체크</button> 
                              <sdFeatherIcons v-if="checkFinished == true" 
                              :style="{
                                    backgroundColor: '#20C997',
                                }" type="circle" size="44" />
                            </span>
                          </div>
                        </a-form-item>
                        <br/><br/>

                        <a-form-item
                          name="projectName"
                          initialValue=""
                          label="프로젝트"
                        >
                          <a-select
                            v-model:value="modalFormState.projectName"
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
                        <br/><br/>

                        <a-form-item
                          name="purpose"
                          label="용도"
                        >
                          <a-select
                            name="purpose"
                            v-model:value="modalFormState.purpose"
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
                        <br/><br/>

                        <a-form-item label="개발 가능 여부" name="status">
                          <a-radio-group v-model:value="modalFormState.status">
                            <a-radio value="true">개발 가능</a-radio>
                            <a-radio value="false">개발 불가능</a-radio>
                          </a-radio-group>
                        </a-form-item>
                        <br/>

                        <a-form-item
                          name="spec"
                          label="사양"
                        >
                          <a-textarea
                            v-model:value="modalFormState.spec"
                            :rows="5"
                          />
                        </a-form-item>
                        <br/><br/><br/><br/><br/><br/>

                        <a-form-item name="price" label="금액">
                          <div class="input-prepend-wrap">
                            <span class="input-prepend">
                              <sdFeatherIcons
                                type="dollar-sign"
                                size="14"
                              />
                            </span>
                            <a-input-number
                              v-model:value="modalFormState.price"
                              style="width: 100%"
                            />
                          </div>
                        </a-form-item>
                        <br/><br/>

                        <a-form-item label="모델명">
                          <a-input name="model" v-model:value="modalFormState.model"/>
                        </a-form-item>
                        <br/><br/>

                        <a-form-item label="제조사">
                          <a-input name="company" v-model:value="modalFormState.company"/>
                        </a-form-item>
                        <br/><br/>

                        <a-form-item label="S/N">
                          <a-input name="sn" v-model:value="modalFormState.sn"/>
                        </a-form-item>
                        <br/><br/>

                        <a-form-item
                          name="description"
                          label="비고"
                        >
                          <a-textarea
                            v-model:value="modalFormState.description"
                            :rows="5"
                          />
                        </a-form-item>
                        <br/><br/><br/><br/>
                      </sdCards>
                    </div>
                  </a-col>
                </a-row>
              </div>
            </a-form>
            
            <div class="add-form-action">
              <a-form-item>
                <sdButton @click="finishModal" htmlType="submit" class="btn-adTodo" type="primary" size="large">
                  장비 추가
                </sdButton>
              </a-form-item>
            </div>
          </BasicFormWrapper>
        </div>
      </sdModal>
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
    async setup() {
      const { state, dispatch } = useStore();
      const router = useRouter();
      const { push } = useRouter();
      const file = ref(null);
      const list = ref(null);
      const submitValues = ref({});
      const formRef = ref();

      const modalFormState = reactive({
        visible: false,
        id: "",
        category: "노트북",
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
      });

      watch(() => modalFormState.id, (newId, oldId) => {
        if (newId !== oldId) {
          checkFinished.value = false;
        }
      });

      await dispatch('getApprovalById', router.currentRoute.value.params.approvalId);
      
      const getApprovalById = computed(() => state.approvals.data);
      const getUser = computed(() => state.getUser.data);
      const categories = computed(() => state.caregoryList.data);
      const departments = computed(() => state.departmentList.data);
      const projects = computed(() => state.projectList.data);
  
      const formState = reactive({
        approvalId: getApprovalById.value.approvalId,
        approverName: getUser.value.name,
        deviceId: getApprovalById.value.deviceId,
        category: getApprovalById.value.categoryName,
        auto: "auto",
        urgency: "normal", // normal, urgent
        approvalInfo: getApprovalById.value.approvalInfo,
        status: getApprovalById.value.deviceStatus,
        purpose: getApprovalById.value.devicePurpose,
        manageDep: "",
        project: "",
        userName: getApprovalById.value.userName,
        reason: getApprovalById.value.reason,
        type: getApprovalById.value.type,
        layout: "vertical",
      });

      const showModal = () => {        
        modalFormState.visible = true;
      };

      const finishModal = () => {
        console.log("HHHHHHHHHHHHHHHHHHHHH",modalFormState);
        console.log("WWWWWWWWWWWWWWWWWWWW",formState);
        if (!checkFinished.value) {
          alert('관리번호 중복체크를 해주세요.');
          return;
        }

        if (modalFormState.id !== '') {
          modalFormState.visible = false;
        } else {
          alert('Please Give a Task Title...');
        }

        modalFormState.visible = true;
      };

      const onCancel = () => {
        modalFormState.visible = false;
      };

      const handleCancel = () => {
        onCancel();
      };

      const checkFinished = ref(false);

      const checkDup = async () => {
        if (modalFormState.id === "") {
          alert('관리번호를 입력해주세요.');
          return;
        }

        const response = await dispatch('checkDuplication', modalFormState.id);
        if (response) {
          alert('사용 가능한 관리번호입니다.');
          checkFinished.value = true;
        } else {
          alert('이미 사용 중인 관리번호입니다.');
          checkFinished.value = false;
        }

      };

      const approvalFinish = () => {
        if (formState.type === '구매'){
          showModal();
        }
        // dispatch('approvalDeviceFinishPost', formState);
        // alert('\'승인\'되었습니다.');
        // push('/');
      };
  
      const approvalReturn = () => {
        dispatch('approvalDeviceReturnPost', formState);
        alert('\'반려\'되었습니다.');
        push('/');
      };

      const disabled = ref(true);
  
      const resetForm = () => {
        formRef.value.ruleformState.resetFields();
      };
  
      return {
        file,
        list,
        resetForm,
        submitValues,
        formState,
        showModal,
        onCancel,
        handleCancel,
        modalFormState,
        finishModal,
        checkDup,
        checkFinished,
        approvalFinish,
        approvalReturn,
        formRef,
        disabled,
        categories,
        departments,
        projects,
      };
    },
  });
  
  export default AddProduct;
  </script>
  