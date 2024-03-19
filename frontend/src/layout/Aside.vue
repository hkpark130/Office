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
      <a-menu-item @click="toggleCollapsed" key="home">
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

      <a-sub-menu key="admin" v-if="isAdmin">
        <template v-slot:title
          ><sdFeatherIcons type="key" /><span>관리자 메뉴</span></template
        >
        <a-menu-item @click="toggleCollapsed" key="addDevice">
          <router-link to="/add-device">
            <sdFeatherIcons type="plus-square" />
            <span> 장비 등록 </span>
          </router-link>
        </a-menu-item>

        <a-menu-item @click="toggleCollapsed" key="editDevice">
          <router-link to="/edit-device">
            <sdFeatherIcons type="edit-3" />
            <span> 장비 편집 </span>
          </router-link>
        </a-menu-item>

        <a-menu-item @click="toggleCollapsed" key="addDeviceList">
          <router-link to="/add-device-list">
            <sdFeatherIcons type="file-plus" />
            <span> 장비 일괄 등록 </span>
          </router-link>
        </a-menu-item>

        <a-menu-item @click="toggleCollapsed" key="addCategory">
          <router-link to="/add-category">
            <sdFeatherIcons type="plus-circle" />
            <span> 품목 등록 </span>
          </router-link>
        </a-menu-item>

        <a-sub-menu key="manageDevice">
          <template v-slot:title
            ><sdFeatherIcons type="server" /><span>장비 관리 대장</span></template
          >
          <a-menu-item @click="toggleCollapsed" key="DevicelistAdmin">
            <router-link to="/devicelist-admin">
              <sdFeatherIcons type="list" />
              <span> 장비 리스트 </span>
            </router-link>
          </a-menu-item>
          <a-menu-item @click="toggleCollapsed" key="DisposeDevicelistAdmin">
            <router-link to="/dispose-devicelist-admin">
              <sdFeatherIcons type="trash-2" />
              <span> 폐기 장비 리스트 </span>
            </router-link>
          </a-menu-item>
        </a-sub-menu>

        <a-menu-item @click="toggleCollapsed" key="devicePosition">
          <router-link to="/maps">
            <sdFeatherIcons type="map-pin" />
            <span> 장비 지도 </span>
          </router-link>
        </a-menu-item>
      </a-sub-menu>

      <a-sub-menu key="users">
        <template v-slot:title
          ><sdFeatherIcons type="user" /><span>마이 페이지</span></template
        >
        <a-menu-item @click="toggleCollapsed" key="team">
          <router-link to="/user/myDevice"> 
            <sdFeatherIcons type="airplay" />
            <span>나의 장비 </span>
          </router-link>
        </a-menu-item>
        <a-menu-item @click="toggleCollapsed" key="myList">
          
          <router-link to="/user/myList"> 
            <sdFeatherIcons type="align-justify" />
            <span> 신청 내역 </span>
          </router-link>
        </a-menu-item>

        <a-menu-item @click="toggleCollapsed" key="Activity">
          <router-link to="/activity">
            <sdFeatherIcons type="book" />
            <span> 알림 이력 </span>
          </router-link>
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
// import { useRoute } from "vue-router";
import versions from "../demoData/changelog.json";

export default defineComponent({
  name: "AsideItems",
  props: {
    toggleCollapsed: VueTypes.func,
    events: VueTypes.object,
  },
  setup(props) {
    const store = useStore();
    const { dispatch } = useStore();
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

    // const router = computed(() => useRoute());
    const state = reactive({
      selectedKeys: ["home"],
      openKeys: ["devices"],
      preOpenKeys: ["devices"],
    });

    watchEffect(() => {
        // state.selectedKeys = [router.value.matched[1].name];
        // 이게 변경되면 밑에 watch 함수가 실행됨
        // ~Route.js의 name 조정 필요
    });

    watch(
      () => state.selectedKeys,
      (val, oldVal) => {
        state.preOpenKeys = oldVal;
      }
    );

    const isAdmin = ref('');
    dispatch('getUser').then(() => {
      isAdmin.value = store.state.getUser.isAdmin;
    });

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
      isAdmin,
    };
  },
});
</script>
