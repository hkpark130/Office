<template>
  <div>
    <sdPageHeader title="장비현황 페이지">
    </sdPageHeader>
    <Main>
      <a-row :gutter="25">
        <a-col :sm="24" :xs="24">
          <Suspense>
            <template #default>
              <DeviceListOverview />
            </template>
          </Suspense>
        </a-col>
      </a-row>
    </Main>
  </div>
</template>

<script>
import { Main } from '../styled';
import { defineAsyncComponent } from 'vue';

const DeviceListOverview = defineAsyncComponent(() => import('./overview/DeviceListOverview'));

export default {
  name: 'DeviceList',
  components: {
    Main,
    DeviceListOverview,
  },
};
</script>
