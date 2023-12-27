<template>
    <SocialMediaWrapper>
      <sdCards title="장비 현황" >
        <a-row :gutter="25">
            <a-col v-for="(item, index) in devices" :key="index" :xxl="8" :md="4" :xs="8">
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
    data() {
      onMounted(async() => {
        const data = await DataService.get('/api/devices');
        response.data = data.data;
      });
      return {
          devices: response.data,
      };
    },
    
  });
  export default SocialMediaOverview;
  </script>
  