<template>
    <sdPageHeader title="장비 등록"></sdPageHeader>
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
                              <sdCards title="CSV 파일">
                                <a-upload-dragger v-bind="fileUploadProps">
                                  <p class="ant-upload-drag-icon">
                                    <sdFeatherIcons type="upload" size="50" />
                                  </p>
                                  <sdHeading as="h4" class="ant-upload-text">
                                    CSV 파일
                                  </sdHeading>
                                  <p class="ant-upload-hint">
                                    <span>양식: </span> ()
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
    // },
  ];
  
  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    setup() {
      const file = ref(null);
      const list = ref(null);
      const submitValues = ref({});
      const formRef = ref();
  
      const fileUploadProps = {
        name: "file",
        multiple: true,
        action: "http://localhost",
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
        name: "",
        category: "",
        price: 0,
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
  