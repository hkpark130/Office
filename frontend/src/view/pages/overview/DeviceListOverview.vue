<template>
    <SocialMediaWrapper>
      <sdCards title="장비 현황" >
        <a-row :gutter="25">
            <a-col v-for="(item, index) in response" :key="index" :xxl="8" :md="4" :xs="8">
            <SocialMediaContent
                :title="item.amount"
                :subTitle="item.name"
                :src="require(`@/static/img/${item.img}`)"
                :path="`deviceList/${item.name}`"
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
  import { defineComponent, onMounted } from 'vue';
  import { DataService } from '@/config/dataService/dataService';

  const SocialMediaOverview = defineComponent({
    name: 'SocialMediaOverview',
    components: { SocialMediaWrapper, SocialMediaContent },
    setup() {
      onMounted(async() => {
        await DataService.get('/api/devices').then(
          (v) => {
            console.log(v.data);
            response.value = v.data;
          }
        );
      });
      return {
        response,
      };
    },
    
  });
  export default SocialMediaOverview;
  </script>
  