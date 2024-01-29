<template>
    <sdPageHeader title="장비 반납 신청"></sdPageHeader>
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
                              <sdCards title="장비 반납 신청">
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
                                  <a-input v-model:value="formState.userName" disabled/>
                                </a-form-item>

                                <a-form-item
                                  name="purpose"
                                  label="용도"
                                >
                                  <a-select
                                    name="purpose"
                                    v-model:value="formState.purpose" disabled
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
                                      />
                                    </DatePickerWrapper>
                                  </DatePickerWrap>
                                </a-form-item>

                                <a-form-item name="tag" label="Tag">
                                  <TagInput>
                                      <div>
                                          <template v-for="(tag, index) in formState.printTag">
                                              <a-tooltip
                                                  v-if="tag.length > 20"
                                                  :key="tag"
                                                  :title="tag"
                                              >
                                                  <a-tag
                                                      :key="tag"
                                                      :closable="true"
                                                      tagType="colorful"
                                                      :color="formState.tag.includes(tag) ? 'green' : ''" 
                                                      @click="() => handleClick(tag)"
                                                      @close="() => handleClose(tag)"
                                                  >
                                                      {{ `${tag.slice(0, 20)}...` }}
                                                  </a-tag>
                                              </a-tooltip>
                                              <a-tag
                                                  v-else
                                                  :key="index + 1"
                                                  :closable="true"
                                                  tagType="colorful" 
                                                  :color="formState.tag.includes(tag) ? 'green' : ''"
                                                  @click="() => handleClick(tag)"
                                                  @close="() => handleClose(tag)"
                                              >
                                                  {{ tag }}
                                              </a-tag>
                                          </template>
                                          <div>
                                              <a-input
                                                  v-if="inputVisible"
                                                  type="text"
                                                  size="small"
                                                  :style="{ width: '78px' }"
                                                  v-model:value="inputValue"
                                                  @keypress.enter="handleInputConfirm()"
                                              />
                                              <a-tag
                                                  v-else
                                                  style="
                                                      background: #fff;
                                                      borderstyle: dashed;
                                                  "
                                                  @click="showInput"
                                              >
                                                  <sdFeatherIcons
                                                      type="plus"
                                                      size="14"
                                                  />
                                                  New Tag
                                              </a-tag>
                                          </div>
                                      </div>
                                  </TagInput>
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
  import { Main, BasicFormWrapper, DatePickerWrapper, TagInput } from "../styled";
  import { AddProductForm } from "./style";
  import { DatePickerWrap } from './ui-elements-styled';
  import { computed, ref, reactive, defineComponent } from "vue";
  import { useRouter } from 'vue-router';
  import { useStore } from 'vuex';
  
  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm, DatePickerWrapper, DatePickerWrap, TagInput },
    async setup() {
      const { state, dispatch } = useStore();
      const router = useRouter();
      const { push, go } = useRouter();
      const file = ref(null);
      const list = ref(null);
      const submitValues = ref({});
      const formRef = ref(null);

      await dispatch('getDeviceById', router.currentRoute.value.params.deviceId);
      
      const getDeviceById = computed(() => state.deviceById.getDeviceData);
      const getUser = computed(() => state.getUser.data);
  
      const formState = reactive({
        deviceId: router.currentRoute.value.params.deviceId,
        category: getDeviceById.value.categoryName,
        price: getDeviceById.value.price,
        auto: "auto",
        status: getDeviceById.value.status,
        purpose: getDeviceById.value.purpose,
        manageDep: "",
        project: "",
        deadline: "",
        userName: getUser.value.name,
        realUser: getDeviceById.value.realUser,
        reason: "",
        description: getDeviceById.value.description,
        type: "반납",
        isUsable: false,
        tag: [],
        printTag: ['Office 미설치', 'OS 미설치', '포맷완료'],
        layout: "vertical",
      });

      const disabledDate = (current) => {
        return current && current.valueOf() < Date.now();
      }
  
      const handleFinish = async () => {
        try {
          await formRef.value.validate();
          dispatch('submitDeviceReturnPost', formState).then(() => {
            alert('신청되었습니다.');
            push('/');
          });
        } catch (error) {
          console.error(error);
        }
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };

      const disabled = ref(true);

      const handleCancel = () => {
        go(-1);
      };

      const inputVisible = ref(false);
      const inputValue = ref('');

      const showInput = () => {
        inputVisible.value = true;
      };

      const handleClose = (removedTag) => {
        const removedtags = formState.printTag.filter((tag) => tag !== removedTag);
        formState.printTag = removedtags;
        if (formState.tag.includes(removedTag)) {
          const index = formState.tag.indexOf(removedTag);
          if (index !== -1) {
            formState.tag.splice(index, 1);
          }
        }
      };

      const handleInputConfirm = () => {
        if (inputValue.value && formState.printTag.indexOf(inputValue.value) === -1) {
          formState.printTag = [...formState.printTag, inputValue.value];
        }
        inputVisible.value = false;
        inputValue.value = '';
      };

      const handleClick = (tag) => {
        if (formState.tag.includes(tag)) {
          const index = formState.tag.indexOf(tag);
          if (index !== -1) {
            formState.tag.splice(index, 1);
          }
        } else {
          formState.tag.push(tag);
        }
      };
      
  
      return {
        file,
        list,
        submitValues,
        formState,
        handleFinish,
        handleFinishFailed,
        formRef,
        disabled,
        disabledDate,
        handleCancel,
        showInput,
        inputVisible,
        handleClose,
        handleInputConfirm,
        inputValue,
        handleClick,
      };
    },
  });
  
  export default AddProduct;
  </script>
  