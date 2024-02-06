<template>
  <sdPageHeader title="상세"></sdPageHeader>
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
                            <sdCards title="상세">

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

                    <a-row :gutter="25">
                <a-col :xs="24">
                    <sdCards title="댓글">
                        <a-list
                            class="comment-list"
                            :header="`${comments.length} replies`"
                            item-layout="horizontal"
                            :data-source="comments"
                        >
                            <template #renderItem="{ item }">
                                <a-list-item>
                                    <a-comment
                                        :author="item.author"
                                    >
                                        <template #content>
                                            <p>
                                                {{ item.comment }}
                                            </p>
                                        </template>
                                        <template #datetime>
                                            <a-tooltip
                                                :title="
                                                    item.createdDate.format(
                                                        'YYYY-MM-DD HH:mm:ss'
                                                    )
                                                "
                                            >
                                                <span>{{
                                                    item.createdDate.fromNow()
                                                }}</span>
                                            </a-tooltip>
                                        </template>
                                    </a-comment>
                                </a-list-item>
                            </template>
                        </a-list>
                    </sdCards>
                </a-col>

                <a-col :lg="24" :xs="24">
                      <sdCards title="Reply Editor">
                          <a-comment>
                              <template v-slot:content>
                                  <a-textarea
                                      :rows="4"
                                      v-model:value="value"
                                  />
                                  <sdButton
                                      html-type="submit"
                                      class="add-comment"
                                      size="large"
                                      :load="true"
                                      type="primary"
                                      @click="handleSubmit"
                                  >
                                      댓글 추가
                                  </sdButton>
                              </template>
                          </a-comment>
                      </sdCards>
                  </a-col>
                </a-row>

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
import dayjs from 'dayjs';

const relativeTime = require('dayjs/plugin/relativeTime');
dayjs.extend(relativeTime);

const AddProduct = defineComponent({
  name: "AddProduct",
  components: { Main, BasicFormWrapper, AddProductForm },
  async setup() {
    const { state, dispatch } = useStore();
    const router = useRouter();
    const file = ref(null);
    const list = ref(null);
    const submitValues = ref({});
    const formRef = ref();
    let comments = [];
    const value = ref('');

    await dispatch('getApprovalById', router.currentRoute.value.params.approvalId);
    await dispatch('getCommentById', router.currentRoute.value.params.approvalId);
    
    const getApprovalById = computed(() => state.approvals.data);
    const getUser = computed(() => state.getUser.data);
    const getCommentById = computed(() => state.comments.data);
    getCommentById.value.map((comment) => {
      comments.push({
        author: comment.userId.username,
        comment: comment.comment,
        createdDate: dayjs(comment.createdDate),
      });
    });

    const formState = reactive({
      approvalId: getApprovalById.value.approvalId,
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

    const disabled = ref(true);

    const resetForm = () => {
      formRef.value.ruleformState.resetFields();
    };

    const handleSubmit = () => {
      const comment = {
        approvalId: formState.approvalId,
        userName: getUser.value.name,
        comment: value.value,
      };
      dispatch('commentPost', comment)
        .then(() => {
          alert('\'댓글 등록\'되었습니다.');
          value.value = '';
        }
      );
    };

    return {
      file,
      list,
      resetForm,
      submitValues,
      formState,
      formRef,
      disabled,
      comments,
      value,
      getCommentById,
      handleSubmit,
    };
  },
});

export default AddProduct;
</script>
