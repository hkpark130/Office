<template>
    <sdPageHeader title="결재 처리"></sdPageHeader>
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
                              <sdCards title="결재 처리">
                                <a-form-item label="관리번호">
                                  <a-input v-model:value="getApprovalObj.deviceId" />
                                </a-form-item>

                                <a-form-item label="품목">
                                  <a-input v-model:value="getApprovalObj.category" />
                                </a-form-item>

                                <a-form-item label="신청자">
                                  <a-input v-model:value="getApprovalObj.user" />
                                </a-form-item>

                                <a-form-item
                                  name="manageDep"
                                  label="관리부서"
                                >
                                  <a-select
                                    v-model:value="getApprovalObj.manageDep"
                                    style="width: 100%"
                                  >
                                    <a-select-option value=""
                                      >경영관리부</a-select-option
                                    >
                                    <a-select-option value="wearingClothes"
                                      >제품2팀</a-select-option
                                    >
                                  </a-select>
                                </a-form-item>

                                <a-form-item
                                  name="project"
                                  label="프로젝트"
                                >
                                  <a-select
                                    v-model:value="getApprovalObj.project"
                                    style="width: 100%"
                                  >
                                    <a-select-option value=""
                                      >스마트로</a-select-option
                                    >
                                  </a-select>
                                </a-form-item>

                                <a-form-item
                                  name="reason"
                                  label="사유"
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
                          <sdButton
                            size="large"
                            htmlType="submit"
                            type="success"
                            raised
                          >
                            승인
                          </sdButton>
                          <sdButton type="danger" size="large">
                            반려
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
  import { ref, reactive, defineComponent } from "vue";
  import { message } from "ant-design-vue";
  import { useRouter } from 'vue-router';
  
  const fileList = [
    // {
    //   uid: "1",
    //   name: "1.png",
    //   status: "done",
    //   url: require("@/static/img/products/1.png"),
    //   thumbUrl: require("@/static/img/products/1.png"),
    // }, 파일첨부시 양식
  ];
  
  const AddProduct = defineComponent({
    props: ['deviceId'],
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    setup(props) {
      const router = useRouter();
      const getApprovalObj = reactive(router.currentRoute.value.query);
      const file = ref(null);
      const list = ref(null);
      const submitValues = ref({});
      const formRef = ref();

      const API_ENDPOINT = process.env.VUE_APP_API_ENDPOINT;
  
      const fileUploadProps = {
        name: "file",
        multiple: true,
        action: API_ENDPOINT,
        onChange(info) {
          const { status } = info.file;
          if (status !== "uploading") {
            file.value = info.file;
            list.value = info.fileList;
          }
          if (status === "done") {
            message.success(`${info.file.name} file uploaded successfully.`);
          } else if (status === "error") {
            message.error(`${info.file.name} file upload failed.`);
          }
        },
        listType: "picture",
        defaultFileList: fileList,
        showUploadList: {
          showRemoveIcon: true,
          removeIcon: (
            <sdFeatherIcons
              type="trash-2"
              onClick={(e) => console.log(e, "custom removeIcon event")}
            />
          ),
        },
      };
  
      const formState = reactive({
        name: props.deviceId,
        category: "",
        price: 0,
        level: "normal",
        status: "",
        description: "",
        layout: "vertical",
      });
  
      const handleFinish = () => {
        console.log(formState);
      };
  
      const handleFinishFailed = (errors) => {
        console.log(errors);
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
        getApprovalObj,
        fileList,
        fileUploadProps,
        rules,
        file,
        list,
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
  