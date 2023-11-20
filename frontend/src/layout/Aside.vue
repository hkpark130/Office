<template>
  <a-menu
    v-model:openKeys="openKeys"
    v-model:selectedKeys="selectedKeys"
    :mode="mode"
    :theme="darkMode ? 'dark' : 'light'"
  >
    <a-sub-menu key="dashboard">
      <template v-slot:title>
        <sdFeatherIcons type="home" /><span>Dashboard</span>
      </template>
      <a-menu-item @click="toggleCollapsed" key="light">
        <a
          @click="
            (e) => {
              e.preventDefault();
              toggleCollapsed();
              modeChangeLight();
            }
          "
          to="#"
        >
          Light Mode
        </a>
      </a-menu-item>
      <a-menu-item @click="toggleCollapsed" key="dark">
        <a
          @click="
            (e) => {
              e.preventDefault();
              toggleCollapsed();
              modeChangeDark();
            }
          "
          to="#"
        >
          Dark Mode
        </a>
      </a-menu-item>
    </a-sub-menu>

    <a-sub-menu key="devices">
      <template v-slot:title
        ><sdFeatherIcons type="hard-drive" /><span>장비관리</span></template
      >
      <a-menu-item @click="toggleCollapsed" key="device">
        <router-link to="/">
          <sdFeatherIcons type="airplay" />
          <span> 가용장비 대시보드 </span>
        </router-link>
      </a-menu-item>
      <a-menu-item @click="toggleCollapsed" key="deviceList">
        <router-link to="/deviceList"> 
          <sdFeatherIcons type="list" />
          <span> 가용장비 리스트 </span> 
        </router-link>
      </a-menu-item>
      <a-menu-item @click="toggleCollapsed" key="purchase">
        <router-link to="/purchase"> 
          <sdFeatherIcons type="dollar-sign" />
          <span> 구매신청 </span>
        </router-link>
      </a-menu-item>
    </a-sub-menu>
    
    <a-menu-item-group key="pages">
      <template v-slot:title>
        <p class="sidebar-nav-title">Pages</p>
      </template>

      <a-menu-item @click="toggleCollapsed" key="addDevice">
        <router-link to="/add-device">
          <sdFeatherIcons type="plus-square" />
          <span> 장비 등록 </span>
        </router-link>
      </a-menu-item>

      <a-menu-item @click="toggleCollapsed" key="addDeviceList">
        <router-link to="/add-device-list">
          <sdFeatherIcons type="file-plus" />
          <span> 장비 일괄 등록 </span>
        </router-link>
      </a-menu-item>

      <a-sub-menu key="manageDevice">
        <template v-slot:title
          ><sdFeatherIcons type="server" /><span>장비 관리 대장</span></template
        >
        <a-menu-item @click="toggleCollapsed" key="write">
          <router-link to="/add-approval">
            <sdFeatherIcons type="list" />
            <span> 장비 리스트 </span>
          </router-link>
        </a-menu-item>
        <a-menu-item @click="toggleCollapsed" key="view">
          <router-link to="#">
            <sdFeatherIcons type="trash-2" />
            <span> 폐기 장비 리스트 </span>
          </router-link>
        </a-menu-item>
      </a-sub-menu>

      <a-menu-item @click="toggleCollapsed" key="deviceHistory">
        <router-link to="/blank">
          <sdFeatherIcons type="book" />
          <span> 장비 변경 이력 </span>
        </router-link>
      </a-menu-item>

      <a-menu-item @click="toggleCollapsed" key="devicePosition">
        <router-link to="/blank">
          <sdFeatherIcons type="map-pin" />
          <span> 장비 지도 </span>
        </router-link>
      </a-menu-item>

      <a-sub-menu key="users">
        <template v-slot:title
          ><sdFeatherIcons type="user" /><span>마이 페이지</span></template
        >
        <a-menu-item @click="toggleCollapsed" key="team">
          <router-link to="/user/myDevice"> 나의 장비 </router-link>
        </a-menu-item>
        <a-menu-item @click="toggleCollapsed" key="myList">
          <router-link to="/user/myList"> 신청 내역 </router-link>
        </a-menu-item>
      </a-sub-menu>

    </a-menu-item-group>
  </a-menu>
</template>
<script>
import {
  computed,
  reactive,
  ref,
  toRefs,
  watch,
  watchEffect,
  defineComponent,
} from "vue";
import VueTypes from "vue-types";
import { useStore } from "vuex";
import { useRoute } from "vue-router";
import versions from "../demoData/changelog.json";

export default defineComponent({
  name: "AsideItems",
  props: {
    toggleCollapsed: VueTypes.func,
    events: VueTypes.object,
  },
  setup(props) {
    const store = useStore();
    const darkMode = computed(() => store.state.themeLayout.data);
    const mode = ref("inline");
    const { events } = toRefs(props);
    const {
      onRtlChange,
      onLtrChange,
      modeChangeDark,
      modeChangeLight,
      modeChangeTopNav,
      modeChangeSideNav,
    } = events.value;

    const router = computed(() => useRoute());
    const state = reactive({
      selectedKeys: ["home"],
      openKeys: ["dashboard"],
      preOpenKeys: ["dashboard"],
    });

    watchEffect(() => {
      if (router.value.matched.length) {
        if (router.value.matched.length > 2) {
          state.selectedKeys = [router.value.matched[2].name];
          state.openKeys = [router.value.matched[1].name];
          state.preOpenKeys = [router.value.matched[1].name];
        } else if (router.value.matched.length > 3) {
          state.selectedKeys = [router.value.matched[3].name];
          state.openKeys = [router.value.matched[1].name];
          state.preOpenKeys = [router.value.matched[1].name];
        } else {
          state.selectedKeys = [router.value.matched[1].name];
          state.openKeys = [router.value.matched[1].name];
          state.preOpenKeys = [router.value.matched[1].name];
        }
      }
    });

    watch(
      () => state.openKeys,
      (val, oldVal) => {
        state.preOpenKeys = oldVal;
      }
    );

    return {
      mode,
      ...toRefs(state),
      darkMode,
      onRtlChange,
      onLtrChange,
      modeChangeDark,
      modeChangeLight,
      modeChangeTopNav,
      modeChangeSideNav,
      versions,
    };
  },
});
</script>
