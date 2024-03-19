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
                                  <a-form-item label="사용자" name="realUser" required>
                                    <a-input v-model:value="formState.realUser" disabled/>
                                  </a-form-item>
                                </a-col>
                              </a-row>

                              <a-form-item
                                name="purpose"
                                label="용도"
                              >
                                <a-select
                                  name="purpose"
                                  v-model:value="formState.purpose"
                                  disabled
                                >
                                  <a-select-option value="개발"
                                    >개발</a-select-option
                                  >
                                  <a-select-option value="사무"
                                    >사무</a-select-option
                                  >
                                </a-select>
                              </a-form-item>

                              <a-form-item
                                name="description"
                                label="비고"
                                required
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
                                  disabled
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
  </Main>
</template>
<script lang="jsx">
import { Main, BasicFormWrapper } from "../styled";
import { AddProductForm } from "./style";
import { computed, ref, reactive, defineComponent } from "vue";
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

    await dispatch('getApprovalById', router.currentRoute.value.params.approvalId)
    .catch(() => {
      alert("신청 정보를 불러오는 과정에서 에러가 발생하였습니다.");
      window.location.href = "/";
    });
    
    
    const getApprovalById = computed(() => state.approvals.data);
    const getUser = computed(() => state.getUser.data);
    const formState = reactive({
      approvalId: getApprovalById.value.approvalId,
      approverName: getUser.value.name,
      deviceId: getApprovalById.value.deviceId,
      category: getApprovalById.value.categoryName,
      auto: "auto",
      approvalInfo: getApprovalById.value.approvalInfo,
      status: getApprovalById.value.deviceStatus,
      purpose: getApprovalById.value.devicePurpose,
      manageDep: "",
      project: "",
      userName: getApprovalById.value.userName,
      realUser: getApprovalById.value.realUser,
      reason: getApprovalById.value.reason,
      description: getApprovalById.value.description,
      type: getApprovalById.value.type,
      isUsable: null,
      applicant: getApprovalById.value.userName,
      deadline: (new Date(getApprovalById.value.deadline)).toISOString(),
      layout: "vertical",
    });

    if (getApprovalById.value.approvalInfo === '승인완료' || getApprovalById.value.approvalInfo === '반려') {
      alert("승인완료 또는 반려된 신청입니다.");
      window.location.href = "/";
    }
    

    const approvalFinish = () => {
      formState.isUsable = (formState.type === '반납')?true:false;
      dispatch('approvalDeviceFinishPost', formState);
      alert('\'승인\'되었습니다.');
      push('/');
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
      approvalFinish,
      approvalReturn,
      formRef,
      disabled,
    };
  },
});

export default AddProduct;
</script>
