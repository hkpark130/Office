<template>
    <SocialMediaWrapper>
      <sdCards title="장비 현황" >
        <a-row :gutter="25">
            <a-col v-for="(item, index) in devices" :key="index" :xxl="8" :md="4" :xs="8">
            <SocialMediaContent
                :title="item.title"
                :subTitle="item.subTitle"
                :src="require(`@/static/img/${item.src}`)"
                :path="item.path"
            />
            </a-col>
        </a-row>
      </sdCards>
    </SocialMediaWrapper>
  </template>
  <script>
  import { response } from './load-data';
  import { SocialMediaWrapper } from '../style';
  import SocialMediaContent from '../../../components/social-media/overview.vue';
  import { defineComponent } from 'vue';

  const SocialMediaOverview = defineComponent({
    name: 'SocialMediaOverview',
    components: { SocialMediaWrapper, SocialMediaContent },
    data() {
        return {
            devices: response.data,
        };
    },
  });
  console.log(response.data);
  export default SocialMediaOverview;
  </script>
  