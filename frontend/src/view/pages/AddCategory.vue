<template>
    <sdPageHeader title="카테고리 등록"></sdPageHeader>
    <Main>
      <a-row :gutter="15">
        <a-col :xs="24">
          <sdCards headless>
            <a-row :gutter="25" justify="center">
              <a-col :xxl="12" :md="16" :sm="24" :xs="24">
                <AddProductForm>
                  <a-form
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
                              <sdCards title="About Category">
                                <a-form-item label="카테고리 이름">
                                  <a-input name="category" v-model:value="formState.name"/>
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
  import { reactive, defineComponent, computed } from "vue";
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';

  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    setup() {
      const { state, dispatch } = useStore();
      const { push, go } = useRouter();

      const categories = computed(() => state.caregoryList.data);

      const formState = reactive({
        name: "",
        layout: "vertical",
      });
  
      const handleFinish = () => {
        if (!formState.name) {
          alert('카테고리 이름을 입력해 주세요.');
          return;
        }
        
        dispatch('submitAddCategoryPost', formState)
          .then(() => {
            alert('등록되었습니다.');
            push('/');
          }
        );
        
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };

      const handleCancel = () => {
        go(-1);
      };
  
      return {
        categories,
        formState,
        handleFinish,
        handleFinishFailed,
        handleCancel,
      };
    },
  });
  
  export default AddProduct;
  </script>
  