<template>
  <sdPageHeader title="프로젝트 편집"></sdPageHeader>
  <Main>
    <a-row :gutter="15">
      <a-col :xs="24">
        <sdCards headless>
          <a-row :gutter="25" justify="center">
            <a-col :xxl="12" :md="16" :sm="24" :xs="24">
              <a-row class="ant-row-middle" justify="center">
                <a-col :md="18" :xs="23" >
                  <a-input placeholder="편집할 프로젝트의 `코드` 를 입력해주세요." v-model:value="searchProjectCode" />
                </a-col>
                <a-col :md="1" :xs="1" :class="rtl ? 'text-left' : 'text-right'">
                  <a to="#" @click="searchProject">
                    <span class="certain-category-icon">
                      <sdFeatherIcons type="search" size="25"/>
                    </span>
                  </a>
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
                            <sdCards title="About Project">
                              <a-form-item name="new" label="신규 or 편집 여부">
                                <a-radio-group v-model:value="formState.new" @change="onChange">
                                  <a-radio value="new">신규</a-radio>
                                  <a-radio value="update" disabled>편집</a-radio>
                                </a-radio-group>
                              </a-form-item>

                              <a-form-item label="프로젝트명" name="name" required>
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
import { ref, reactive, defineComponent } from "vue";
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const AddProduct = defineComponent({
  name: "AddProduct",
  components: { Main, BasicFormWrapper, AddProductForm },
  setup() {
    const formRef = ref();
    const { dispatch } = useStore();
    const { go } = useRouter();
    const searchProjectCode = ref();

    const searchProject = async () => {
      const response = await dispatch('getProjectByCode', searchProjectCode.value);
      if (response) {
        formState.new = "update";
        formState.id = response.id;
        formState.code = response.code;
        formState.name = response.name;
      } else {
        alert("프로젝트를 찾을 수 없습니다.");
      }
    };

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
          dispatch('submitAddProjectPost', formState).then(() => {
            alert('등록되었습니다.');
            location.reload();
          });
        } else {
          dispatch('submitEditProjectPut', formState).then(() => {
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
      if (formState.new === 'update' && formState.code !== null) {
        dispatch('deleteProject', formState.id)
          .then(() => {
              alert('삭제되었습니다.');
              location.reload();
            }
          );
      } else {
        alert("삭제할 프로젝트를 검색해주세요.");
      }
      
    };

    return {
      formState,
      handleFinish,
      handleFinishFailed,
      formRef,
      searchProject,
      searchProjectCode,
      handleCancel,
      onChange,
      handleDelete,
    };
  },
});

export default AddProduct;
</script>
