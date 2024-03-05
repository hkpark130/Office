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
                          <sdButton class="btn-cancel" size="large" @click.prevent="handleCancel">
                            Cancel
                          </sdButton>
                          <sdButton
                            size="large"
                            htmlType="submit"
                            type="primary"
                            raised
                          >
                            장비 일괄 등록
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
  import { message, Upload } from "ant-design-vue";
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';
  
  const AddProduct = defineComponent({
    name: "AddProduct",
    components: { Main, BasicFormWrapper, AddProductForm },
    setup() {
      const file = ref(null);
      const submitValues = ref({});
      const formRef = ref();
      const { dispatch } = useStore();
      const { push, go } = useRouter();

      const API_ENDPOINT = process.env.VUE_APP_API_ENDPOINT;

      const requiredColumns = [
        "id", "categoryName", "isUsable"
      ];

      const optionalColumns = [
        "manageDepName","projectName","purpose","purchaseDate",
        "status", "spec","price","model","company","sn","description"
      ];

      const columns = requiredColumns.concat(optionalColumns);
  
      const fileList = [];
      const fileUploadProps = {
        name: "file",
        maxCount: 1, // 1개만 표시 되도록
        multiple: false, // 1개만 업로드 되도록
        action: API_ENDPOINT+"/api/upload-mock",
        beforeUpload(file) {
          return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.onload = (e) => {
              const fileContent = e.target.result;
              if (checkCSV(fileContent)) {
                resolve(file);
              } else {
                reject(Upload.LIST_IGNORE);
              }
            };
            reader.readAsText(file); // FileReader에 직접 파일 전달
          })
        },
        onChange(info) {
          const { status } = info.file;
          if (status !== "uploading") {
            file.value = info.file;
          }
          if (status === "done") {
            message.success(`'장비 일괄 등록' 버튼을 눌러주세요.`);
          } else if (status === "error") {
            message.error(`${info.file.name} file upload failed.`);
          }
        },
        listType: "file",
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

      const requsetData = ref([]);
      const inputData = ref({});

      const checkCSV = (csvContent) => {
        const lines = csvContent.split('\r\n');
        const headers = lines[0].split(',');
        for (const v of new Set([...headers, ...columns])){
          if (headers.filter(e => e === v).length !== columns.filter(e => e === v).length) {
            throw new Error("유효한 양식이 아닙니다. "+ v);
          }
        }

        for (let row = 1; row < lines.length; row++) {
          inputData.value = {};
          const val = lines[row].split(',');
          for (const col in headers) {
            if (requiredColumns.includes(headers[col]) && val[col] === '') {
              throw new Error(row + " 라인에 " + requiredColumns[col] + " 값이 없습니다.");
            }
            // inputData.value.push({"key": headers[col], "value": val[col]});
            inputData.value[headers[col]] = val[col];
          }
          requsetData.value.push(inputData.value);
        }

        return true;
      }
  
      const handleFinish = () => {
        for (const v of requsetData.value) {
          dispatch('submitAddDevicePost', v);
        }
        alert('등록되었습니다.');
        push('/');
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
          title: 'id',
          dataIndex: 'id',
          key: 'id',
        },
        {
          title: 'categoryName',
          dataIndex: 'categoryName',
          key: 'categoryName',
        },
        {
          title: 'manageDepName',
          dataIndex: 'manageDepName',
          key: 'manageDepName',
        },
        {
          title: 'projectName',
          dataIndex: 'projectName',
          key: 'projectName',
        },
        {
          title: 'status',
          dataIndex: 'status',
          key: 'status',
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
          title: 'isUsable',
          dataIndex: 'isUsable',
          key: 'isUsable',
        },
        {
          title: 'purchaseDate',
          dataIndex: 'purchaseDate',
          key: 'purchaseDate',
        },
        {
          title: 'description',
          dataIndex: 'description',
          key: 'description',
        },
      ];

      const formatState = computed(() => [{
        "id": "DIR-N-107",
        "categoryName": "노트북",
        "purpose": "사무",
        "manageDepName": "경영지원부",
        "status": "정상",
        "projectName": "농협",
        "spec": "RAM: 16G\nCPU: 8core",
        "price": "80000",
        "model": "LSBX2433",
        "company": "SAMSUNG",
        "sn": "ND6179NBK",
        "isUsable": "true",
        "purchaseDate": "2024-02-01",
        "description": "부팅 느림"
      }]);

      const formatData = computed(() =>
        formatState.value.map((value) => {
          const { id, categoryName, purpose, manageDepName, spec, price, model, description, 
            projectName, sn, status, company, isUsable, purchaseDate } = value;
          return {
            key: id,
            id: <span>{id}</span>,
            categoryName: <span>{categoryName}</span>,
            purpose: <span>{purpose}</span>,
            manageDepName: <span>{manageDepName}</span>,
            status: <span>{status}</span>,
            spec: <span>{spec}</span>,
            price: <span>{price}</span>,
            model: <span>{model}</span>,
            description: <span>{description}</span>,
            projectName: <span>{projectName}</span>,
            company: <span>{company}</span>,
            sn: <span>{sn}</span>,
            isUsable: <span>{isUsable}</span>,
            purchaseDate: <span>{purchaseDate}</span>,
          };
        })
      );

      const handleCancel = () => {
        go(-1);
      };
  
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
        handleCancel,
      };
    },
  });
  
  export default AddProduct;
  </script>
  