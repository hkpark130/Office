<template>
  <sdPageHeader title="수정"></sdPageHeader>
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
                            <sdCards title="수정">

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
                                  <a-form-item label="사용자" name="realUser">
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
                          @click="approvalEdit"
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
    const { go } = useRouter();
    const file = ref(null);
    const list = ref(null);
    const submitValues = ref({});
    const formRef = ref();
    const disabled = ref(true);

    await dispatch('getApprovalById', router.currentRoute.value.params.approvalId);
    
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
      layout: "vertical",
    });

    const approvalEdit = () => {
      dispatch('EditApprovalPut', formState).then(() => {
        alert('\'수정\'되었습니다.');
        push('/');
      }); 
    };

    const handleCancel = () => {
      go(-1);
    };

    return {
      file,
      list,
      submitValues,
      formState,
      formRef,
      disabled,
      approvalEdit,
      handleCancel,
    };
  },
});

export default AddProduct;
</script>
