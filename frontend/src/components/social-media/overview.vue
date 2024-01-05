<template>
  <MainWraper style="text-align: center;">
    <router-link :to="path">
      <img :src="src" alt="Image" />
      <sdHeading as="h1">{{ title }}</sdHeading>
      <p>{{ subTitle }}</p>
    </router-link>
  </MainWraper>
</template>

<script>
import { MainWraper } from './overview-style';
import VueTypes from 'vue-types';
import { defineComponent } from 'vue';

const SocialMediaContent = defineComponent({
  name: 'SocialMediaContent',
  props: {
    title: VueTypes.string.isRequired,
    subTitle: VueTypes.string.isRequired,
    src: VueTypes.string.isRequired,
    path: VueTypes.string.isRequired,
  },
  components: {
    MainWraper,
  },
});

export default SocialMediaContent;
</script>
