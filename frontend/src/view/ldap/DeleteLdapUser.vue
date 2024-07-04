<template>
    <sdPageHeader title="유저 삭제"></sdPageHeader>
    <Main>
      <a-row :gutter="15">
        <a-col :xs="24">
          <sdCards headless>
            <a-row :gutter="25" justify="center">
              <a-col :xxl="12" :md="16" :sm="24" :xs="24">
                <a-row class="ant-row-middle" justify="center">
                  <a-col :md="18" :xs="23" >
                    <a-input placeholder="삭제할 유저의 `아이디` 를 입력해주세요." v-model:value="searchCn" />
                  </a-col>
                  <a-col :md="1" :xs="1" :class="rtl ? 'text-left' : 'text-right'">
                    <a to="#" @click="searchLdapUser">
                      <span class="certain-category-icon">
                        <sdFeatherIcons type="search" size="25"/>
                      </span>
                    </a>
                  </a-col>
                </a-row>

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
                              <sdCards title="About User">
                                <a-form-item label="유저 아이디" required>
                                  <div class="input-prepend-wrap">
                                    <a-input name="cn" v-model:value="formState.cn" disabled required/>
                                  </div>
                                </a-form-item>

                                <a-form-item>
                                  <sdButton
                                    class="search-btn"
                                    :style="{ [rtl ? 'marginLeft' : 'marginRight']: -20 }"
                                    type="danger"
                                    htmlType="submit"
                                    @click="handleFinish"
                                  >
                                    유저 삭제&nbsp;
                                    <sdFeatherIcons type="trash-2" />
                                  </sdButton>
                                </a-form-item>
                              </sdCards>
                            </div>
                          </a-col>
                        </a-row>
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
  import { ref, reactive, defineComponent } from "vue";
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';

  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    setup() {
      const submitValues = ref({});
      const formRef = ref();
      const { dispatch } = useStore();
      const { push, go } = useRouter();
      const checkFinished = ref(false);
      const searchCn = ref();
  
      const formState = reactive({
        cn: "",
        userPassword: "",
        layout: "vertical",
      });

      const handleFinish = async () => {
        if (!checkFinished.value) {
          alert('유저 아이디를 검색해주세요.');
          return;
        }
          
        dispatch('deleteLdapUser', formState.cn).then(() => {
          alert('삭제 되었습니다.');
          push('/');
        }).catch((error) => {
          throw new Error("에러 발생: " + error);
        });  
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };
  
      const handleSubmit = (values) => {
        submitValues.value = values;
      };

      const handleCancel = () => {
        go(-1);
      };

      const searchLdapUser = async () => {
        const response = await dispatch('cnCheckDuplication', searchCn.value);
        if (response) {
          alert('유저를 찾을 수 없습니다.');
          return;
        } else {
          formState.cn = searchCn.value;
          checkFinished.value = true;
        }
      };
  
      return {
        checkFinished,
        submitValues,
        formState,
        handleFinish,
        handleFinishFailed,
        handleSubmit,
        formRef,
        handleCancel,
        searchCn,
        searchLdapUser,
      };
    },
  });
  
  export default AddProduct;
  </script>
  