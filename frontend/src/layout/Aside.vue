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
    
    <a-menu-item-group key="pages">
      <template v-slot:title>
        <p class="sidebar-nav-title">Pages</p>
      </template>
      <a-menu-item @click="toggleCollapsed" key="device">
        <router-link to="/">
          <sdFeatherIcons type="airplay" />
          <span> 장비 현황 </span>
        </router-link>
      </a-menu-item>

      <a-sub-menu key="users">
        <template v-slot:title
          ><sdFeatherIcons type="users" /><span>Users</span></template
        >
        <a-menu-item @click="toggleCollapsed" key="team">
          <router-link to="/app/users/team"> Team </router-link>
        </a-menu-item>
        <a-menu-item @click="toggleCollapsed" key="dataTable">
          <router-link to="/app/users/dataTable"> Users table </router-link>
        </a-menu-item>
      </a-sub-menu>
      
      <a-sub-menu key="pricing">
        <template v-slot:title
          ><sdFeatherIcons type="dollar-sign" /><span>결재</span></template
        >
        <a-menu-item @click="toggleCollapsed" key="write">
          <router-link to="/add-approval">
            신청
          </router-link>
        </a-menu-item>
        <a-menu-item @click="toggleCollapsed" key="view">
          <router-link to="#">
            확인
          </router-link>
        </a-menu-item>
        <a-menu-item @click="toggleCollapsed" key="return">
          <router-link to="#">
            반려
          </router-link>
        </a-menu-item>
      </a-sub-menu>

      <a-menu-item @click="toggleCollapsed" key="blank">
        <router-link to="/blank">
          <sdFeatherIcons type="circle" />
          <span> Blank Page </span>
        </router-link>
      </a-menu-item>

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
