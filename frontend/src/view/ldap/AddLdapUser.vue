<template>
    <sdPageHeader title="유저 등록"></sdPageHeader>
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
                              <sdCards title="About User">
                                <a-form-item label="유저 아이디" required>
                                  <div class="input-prepend-wrap">
                                    <a-input name="id" v-model:value="formState.cn"/>
                                    <span class="input-append">
                                      <button class="btn-icon" type="button" :onClick="cnCheckDup" transparented>중복체크</button> 
                                      <sdFeatherIcons v-if="cnCheckFinished == true" 
                                      :style="{
                                            backgroundColor: '#20C997',
                                        }" type="circle" size="44" />
                                    </span>
                                  </div>
                                </a-form-item>

                                <a-form-item label="사원번호" required>
                                  <div class="input-prepend-wrap">
                                    <a-input-number name="id" v-model:value="formState.uidNumber"/>
                                    <span class="input-append">
                                      <button class="btn-icon" type="button" :onClick="uidNumCheckDup" transparented>중복체크</button> 
                                      <sdFeatherIcons v-if="uidNumCheckFinished == true" 
                                      :style="{
                                            backgroundColor: '#20C997',
                                        }" type="circle" size="44" />
                                    </span>
                                  </div>
                                </a-form-item>

                                <a-form-item label="유저 이름" required>
                                  <a-input name="uid" v-model:value="formState.uid"/>
                                </a-form-item>

                                <a-form-item
                                  name="ou"
                                  initialValue=""
                                  label="부서"
                                >
                                  <a-select
                                    v-model:value="formState.ou"
                                    style="width: 100%"
                                  >
                                    <a-select-option
                                      v-for="department in departments"
                                      :key="department.id"
                                      :value="department.name"
                                    >{{ department.name }}</a-select-option>
                                  </a-select>
                                </a-form-item>                                

                                <a-form-item label="email">
                                  <a-input name="email" v-model:value="formState.mail" disabled/>
                                </a-form-item>

                                <a-form-item label="비밀번호">
                                  <a-input name="password" v-model:value="formState.userPassword"/>
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
  import { Main, BasicFormWrapper } from "../styled";
  import { AddProductForm } from "./style";
  import { computed, ref, reactive, defineComponent, watch } from "vue";
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';

  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    setup() {
      const submitValues = ref({});
      const formRef = ref();
      const { state, dispatch } = useStore();
      const { push, go } = useRouter();
      const uidNumCheckFinished = ref(false);
      const cnCheckFinished = ref(false);
      const departments = computed(() => state.departmentList.data);
      dispatch('fetchDepartmentList');
  
      const formState = reactive({
        cn: "",
        ou: "경영지원부",
        status: "정상",
        description: "",
        sn: "",
        uid: "",
        gidNumber: 500,
        uidNumber: 0,
        mail: "",
        userPassword: "",
        layout: "vertical",
      });

      watch(() => formState.cn, (newId, oldId) => {
        if (newId !== oldId) {
          cnCheckFinished.value = false;
          formState.mail = formState.cn + "@direa.co.kr";
          formState.sn = formState.cn;
        }
      });
  
      watch(() => formState.uidNumber, (newId, oldId) => {
        if (newId !== oldId) {
          uidNumCheckFinished.value = false;
        }
      });

      const handleFinish = () => {
        if (!cnCheckFinished.value) {
          alert('유저 아이디 중복체크를 해주세요.');
          return;
        }

        if (!uidNumCheckFinished.value) {
          alert('사원번호 중복체크를 해주세요.');
          return;
        }

        if (formState.userPassword === "") {
          // 비밀번호 미 입력 시 기본값으로 사번
          formState.userPassword = formState.uidNumber;
        }

        dispatch('submitAddLdapUserPost', formState).then(() => {
          alert('등록되었습니다.');
          push('/');
        }).catch((error) => {
          throw new Error("에러 발생: " + error);
        });
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
      };

      const cnCheckDup = async () => {
        if (formState.cn === "") {
          alert('유저 아이디를 입력해주세요.');
          return;
        }

        const response = await dispatch('cnCheckDuplication', formState.cn);
        if (response) {
          alert('사용 가능한 유저 아이디입니다.');
          cnCheckFinished.value = true;
        } else {
          alert('이미 사용 중인 유저 아이디입니다.');
          cnCheckFinished.value = false;
        }
      };

      const uidNumCheckDup = async () => {
        if (formState.uidNumber === "") {
          alert('사원번호를 입력해주세요.');
          return;
        }

        const response = await dispatch('uidNumCheckDuplication', formState.uidNumber);
        if (response) {
          alert('사용 가능한 사원번호입니다.');
          uidNumCheckFinished.value = true;
        } else {
          alert('이미 사용 중인 사원번호입니다.');
          uidNumCheckFinished.value = false;
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

      const handleCancel = () => {
        go(-1);
      };
  
      return {
        cnCheckDup,
        uidNumCheckDup,
        uidNumCheckFinished,
        cnCheckFinished,
        rules,
        submitValues,
        formState,
        handleFinish,
        handleFinishFailed,
        handleSubmit,
        formRef,
        handleCancel,
        departments,
      };
    },
  });
  
  export default AddProduct;
  </script>
  