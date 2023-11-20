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
                              <sdCards title="About Device">
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
                                      >모니터</a-select-option
                                    >
                                    <a-select-option value="wearingClothes"
                                      >노트북</a-select-option
                                    >
                                  </a-select>
                                </a-form-item>

                                <a-form-item label="관리번호">
                                  <a-input name="deviceId" />
                                </a-form-item>

                                <a-form-item label="사용자">
                                  <a-input name="user" />
                                </a-form-item>

                                <a-form-item
                                  name="manageDep"
                                  initialValue=""
                                  label="관리부서"
                                >
                                  <a-select
                                    name="manageDep"
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

                                <a-form-item label="프로젝트">
                                  <a-input name="project" />
                                </a-form-item>

                                <a-form-item
                                  name="purpose"
                                  initialValue=""
                                  label="용도"
                                >
                                  <a-select
                                    name="purpose"
                                    style="width: 100%"
                                  >
                                    <a-select-option value=""
                                      >개발</a-select-option
                                    >
                                    <a-select-option value="wearingClothes"
                                      >사무</a-select-option
                                    >
                                  </a-select>
                                </a-form-item>

                                <a-form-item
                                  name="spec"
                                  label="사양"
                                >
                                  <a-textarea
                                    v-model:value="formState.description"
                                    :rows="5"
                                  />
                                </a-form-item>
  
                                <a-form-item name="price" label="금액">
                                  <div class="input-prepend-wrap">
                                    <span class="input-prepend">
                                      <sdFeatherIcons
                                        type="dollar-sign"
                                        size="14"
                                      />
                                    </span>
                                    <a-input-number
                                      v-model:value="formState.price"
                                      style="width: 100%"
                                    />
                                  </div>
                                </a-form-item>

                                <a-form-item label="모델명">
                                  <a-input name="model" />
                                </a-form-item>

                                <a-form-item label="제조사">
                                  <a-input name="company" />
                                </a-form-item>

                                <a-form-item label="S/N">
                                  <a-input name="sn" />
                                </a-form-item>
  
                                <a-form-item
                                  name="description"
                                  label="비고"
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
  