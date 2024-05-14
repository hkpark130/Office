<template>
  <sdPageHeader title="부서 편집"></sdPageHeader>
  <Main>
    <a-row :gutter="15">
      <a-col :xs="24">
        <sdCards headless>
          <a-row :gutter="25" justify="center">
            <a-col :xxl="12" :md="16" :sm="24" :xs="24">
              <a-row class="ant-row-middle" justify="center">
                <a-col :md="18" :xs="23" >
                  <a-form-item
                    name="name"
                    initialValue=""
                  >
                    <sdPopover
                      :placement="!rtl ? 'bottomLeft' : 'bottomRight'"
                      title="부서 리스트"
                      trigger="focus"
                    >
                      <template v-slot:content>
                        <div>
                          <a @click="() => onClickSearchList(item)" v-for="item in filteredData" :key="item.name" to="#">
                            {{ item.printName }}
                          </a>
                          <a v-if="filteredData.length === 0" to="#"> Data Not Found..... </a>
                        </div>
                      </template>
                          <a-input v-model:value="departmentTmp" placeholder="편집할 부서의 `부서명` 를 입력해주세요." 
                            @input="(e) => search(e, searchData)"  @keydown.enter.prevent/>

                    </sdPopover>
                    
                    <span>선택된 부서: <b>{{ formState.name }}</b></span>
                  </a-form-item>

                </a-col>
                
              </a-row>

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
                            <sdCards title="About Department">
                              <a-form-item name="new" label="신규 or 편집 여부">
                                <a-radio-group v-model:value="formState.new" @change="onChange">
                                  <a-radio value="new">신규</a-radio>
                                  <a-radio value="update" disabled>편집</a-radio>
                                </a-radio-group>
                              </a-form-item>

                              <a-form-item label="부서명" name="name" required>
                                <a-input v-model:value="formState.name"/>
                              </a-form-item>

                              <a-form-item label="코드" name="code" required>
                                <a-input v-model:value="formState.code"/>
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
                        <sdButton type="danger" size="large" @click.prevent="handleDelete">
                          삭제
                        </sdButton>
                        <sdButton
                          size="large"
                          htmlType="submit"
                          type="primary"
                          @click="handleFinish"
                          raised
                        >
                          생성/편집
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
import { toRef, ref, reactive, defineComponent } from "vue";
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { departmentList } from '@/vuex/modules/departments/load-data';

const AddProduct = defineComponent({
  name: "AddProduct",
  components: { Main, BasicFormWrapper, AddProductForm },
  setup() {
    const formRef = ref();
    const { dispatch } = useStore();
    const { go } = useRouter();
    const departmentTmp = ref();

    const combinedArray = toRef(departmentList.data.map(item => {
      return {
        id: `${item.id}`,
        name: `${item.name}`,
        code: `${item.code}`,
        printName: `${item.name} ${item.code}`,
      };
    }));
    const searchData = toRef(combinedArray.value);
    const filteredData = toRef(combinedArray.value);

    const search = (e, searchDatas) => {
      const data = searchDatas.filter((item) => {
        return item.printName.includes(e.target.value);
      });
      filteredData.value = data;
    };

    const onClickSearchList = (v) => {
      formState.new = "update";
      formState.id = v.id;
      formState.code = v.code;
      formState.name = v.name;
    }

    const onChange = () => {
      formState.id = "";
      formState.name = "";
      formState.code = "";
    };

    const formState = reactive({
      id: "",
      name: "",
      code: "",
      new: "new",
      layout: "vertical",
    });

    const handleFinish = async () => {
      try {
        await formRef.value.validate();
        if (formState.new === 'new'){
          dispatch('submitAddDepartmentPost', formState).then(() => {
            alert('등록되었습니다.');
            location.reload();
          });
        } else {
          dispatch('submitEditDepartmentPut', formState).then(() => {
            alert('수정되었습니다.');
            location.reload();
          });
        }
      } catch (error) {
        console.error(error);
      }
    };

    const handleFinishFailed = (errors) => {
      console.log(errors);
    };

    const handleCancel = () => {
      go(-1);
    };

    const handleDelete = () => {
      if (formState.new === 'update' && formState.id !== null) {
        dispatch('deleteDepartment', formState.id)
          .then(() => {
              alert('삭제되었습니다.');
              location.reload();
            }
          );
      } else {
        alert("삭제할 부서를 검색해주세요.");
      }
      
    };

    return {
      formState,
      handleFinish,
      handleFinishFailed,
      formRef,
      handleCancel,
      onChange,
      handleDelete,
      searchData,
      filteredData,
      search,
      onClickSearchList,
      departmentTmp,
    };
  },
});

export default AddProduct;
</script>
