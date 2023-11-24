<template>
    <sdPageHeader title="장비 등록"></sdPageHeader>
    <Main>
      <a-row :gutter="15">
        <a-col :xs="24">
          <sdCards headless>
            <a-row :gutter="25" justify="center">
              <div><h3>예시 양식</h3>
                <a-table
                  :columns="formatKeys"
                  :dataSource="formatData"
                  :pagination="false"
                />
              </div>
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
                                  <p class="ant-upload-hint" style="width: 10px; display:inline;">
                                    <span>위 양식을 지켜주세요.</span> 
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
  import { computed, ref, reactive, defineComponent } from "vue";
  import { message } from "ant-design-vue";
  
  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    setup() {
      const file = ref(null);
      const submitValues = ref({});
      const formRef = ref();

      const API_ENDPOINT = process.env.VUE_APP_API_ENDPOINT;

      const requiredColumns = [
        "category","deviceId","user","manageDep","project","purpose",
        "spec","price","model","company","sn","description"
      ];
  
      const fileUploadProps = {
        name: "file",
        maxCount: 1, // 1개만 표시 되도록
        multiple: false, // 1개만 업로드 되도록
        action: API_ENDPOINT,
        beforeUpload(file) {
          return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.onload = (e) => {
              const fileContent = e.target.result;
              if (checkCSV(fileContent)) {
                resolve(file);
              } else {
                reject(new Error("유효한 양식이 아닙니다."));
              }
            };
            reader.readAsText(file); // FileReader에 직접 파일 전달
          });
        },
        onChange(info) {
          const { status } = info.file;
          if (status !== "uploading") {
            file.value = info.file;
          }
          if (status === "done") {
            message.success(`${info.file.name} file uploaded successfully.`);
          } else if (status === "error") {
            message.error(`${info.file.name} file upload failed.`);
          }
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

      const checkCSV = (csvContent) => {
        const lines = csvContent.split('\r\n');
        const headers = lines[0].split(',');

        for (const col in requiredColumns) {
          if( !headers.includes(requiredColumns[col]) ){
            throw new Error("유효한 양식이 아닙니다. " + requiredColumns[col]);
            // return false;
          }
          for (let row = 1; row < lines.length; row++) {
            const val = lines[row].split(',');
            if (requiredColumns[col] !== 'user' && val[col] === '') {
              throw new Error(row + " 라인 " + requiredColumns[col] + " 값이 없습니다.");
            }
          }
        }
        return true;
      }
  
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

      const formatKeys = [
        {
          title: 'deviceId',
          dataIndex: 'deviceId',
          key: 'deviceId',
        },
        {
          title: 'category',
          dataIndex: 'category',
          key: 'category',
        },
        {
          title: 'user',
          dataIndex: 'user',
          key: 'user',
        },
        {
          title: 'manageDep',
          dataIndex: 'manageDep',
          key: 'manageDep',
        },
        {
          title: 'project',
          dataIndex: 'project',
          key: 'project',
        },
        {
          title: 'purpose',
          dataIndex: 'purpose',
          key: 'purpose',
        },
        {
          title: 'spec',
          dataIndex: 'spec',
          key: 'spec',
        },
        {
          title: 'price',
          dataIndex: 'price',
          key: 'price',
        },
        {
          title: 'model',
          dataIndex: 'model',
          key: 'model',
        },
        {
          title: 'company',
          dataIndex: 'company',
          key: 'company',
        },
        {
          title: 'sn',
          dataIndex: 'sn',
          key: 'sn',
        },
        {
          title: 'description',
          dataIndex: 'description',
          key: 'description',
        },
      ];

      const formatState = computed(() => [{
        "deviceId": "DIR-N-107",
        "category": "노트북",
        "purpose": "사무",
        "user": "김철수",
        "manageDep": "경영지원부",
        "project": "농협",
        "spec": "RAM: 16G\nCPU: 8core",
        "price": "80,000",
        "model": "LSBX2433",
        "company": "SAMSUNG",
        "sn": "ND6179NBK",
        "description": "부팅 느림"
      }]);

      const formatData = computed(() =>
        formatState.value.map((value) => {
          const { deviceId, category, purpose, user, manageDep, spec, price, model, description, project, sn, company } = value;
          return {
            key: deviceId,
            deviceId: <span>{deviceId}</span>,
            category: <span>{category}</span>,
            purpose: <span>{purpose}</span>,
            user: <span>{user}</span>,
            manageDep: <span>{manageDep}</span>,
            spec: <span>{spec}</span>,
            price: <span>{price}</span>,
            model: <span>{model}</span>,
            description: <span>{description}</span>,
            project: <span>{project}</span>,
            company: <span>{company}</span>,
            sn: <span>{sn}</span>,
          };
        })
      );
  
      return {
        fileUploadProps,
        rules,
        file,
        resetForm,
        submitValues,
        formState,
        handleFinish,
        handleFinishFailed,
        handleSubmit,
        formRef,
        formatData,
        formatKeys,
      };
    },
  });
  
  export default AddProduct;
  </script>
  