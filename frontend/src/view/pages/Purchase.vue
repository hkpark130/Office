<template>
    <sdPageHeader title="구매 신청"></sdPageHeader>
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
                              <sdCards title="구매 신청">
                                <a-form-item
                                  name="category"
                                  initialValue=""
                                  label="품목"
                                >
                                  <a-select
                                    v-model:value="formState.category"
                                    style="width: 100%"
                                  >
                                    <a-select-option value=""
                                      >Please Select</a-select-option
                                    >
                                    <a-select-option value="wearingClothes"
                                      >품의서</a-select-option
                                    >
                                    <a-select-option value="sunglasses"
                                      >Sunglasses</a-select-option
                                    >
                                    <a-select-option value="t-shirt"
                                      >T-Shirt</a-select-option
                                    >
                                  </a-select>
                                </a-form-item>

                                <a-form-item label="신청자">
                                  <a-input v-model:value="formState.name" />
                                </a-form-item>

                                <a-form-item
                                  name="purpose"
                                  initialValue=""
                                  label="용도"
                                >
                                  <a-select
                                    v-model:value="formState.purpose"
                                    style="width: 100%"
                                  >
                                    <a-select-option value=""
                                      >개발</a-select-option
                                    >
                                    <a-select-option value="sunglasses"
                                      >ETC</a-select-option
                                    >
                                  </a-select>
                                </a-form-item>
  
                                <a-form-item
                                  name="spec"
                                  label="사양"
                                >
                                  <a-textarea
                                    v-model:value="formState.spec"
                                    :rows="3"
                                  />
                                </a-form-item>

                                <a-form-item
                                  name="project"
                                  initialValue=""
                                  label="프로젝트"
                                >
                                  <a-select
                                    v-model:value="formState.project"
                                    style="width: 100%"
                                  >
                                    <a-select-option value=""
                                      >스마트로</a-select-option
                                    >
                                    <a-select-option value="sunglasses"
                                      >ETC</a-select-option
                                    >
                                  </a-select>
                                </a-form-item>

                                <a-form-item name="level" label="긴급도">
                                  <a-radio-group v-model:value="formState.level">
                                    <a-radio value="normal">보통</a-radio>
                                    <a-radio value="urgent">긴급</a-radio>
                                  </a-radio-group>
                                </a-form-item>
  
                                <a-form-item
                                  name="description"
                                  label="상세"
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
                      <div class="add-product-block">
                        <a-row :gutter="15">
                          <a-col :xs="24">
                            <div class="add-product-content">
                              <sdCards title="첨부 이미지 or 파일">
                                <a-upload-dragger v-bind="fileUploadProps">
                                  <p class="ant-upload-drag-icon">
                                    <sdFeatherIcons type="upload" size="50" />
                                  </p>
                                  <sdHeading as="h4" class="ant-upload-text">
                                    Drag and drop an image
                                  </sdHeading>
                                  <p class="ant-upload-hint">
                                    or <span>Browse</span> to choose a file
                                  </p>
                                </a-upload-dragger>
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
  import { ref, reactive, defineComponent } from "vue";
  import { message } from "ant-design-vue";
  
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
  