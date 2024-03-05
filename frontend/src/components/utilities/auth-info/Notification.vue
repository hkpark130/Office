<template>
  <div class="notification">
    <sdPopover placement="bottomLeft" action="click">
      <template v-slot:content>
        <AtbdTopDropdwon class="atbd-top-dropdwon">
          <sdHeading as="h5" class="atbd-top-dropdwon__title">
            <span class="title-text">Notifications</span>
            <a-badge class="badge-success" :count="notificationList.length" />
          </sdHeading>
          
          <perfect-scrollbar
            :options="{
              wheelSpeed: 1,
              swipeEasing: true,
              suppressScrollX: true,
            }"
          >
            <ul class="atbd-top-dropdwon__nav notification-list">
              <li v-for="(notification, index) in notificationList" :key="index">
                <!-- <a :href="notification.link"> -->
                  <div class="atbd-top-dropdwon__content notifications" :class="notification.iconClass">
                    <div class="notification-icon" :class="notification.bgClass">
                      <sdFeatherIcons :type="notification.icon" />
                    </div>
                    <div class="notification-content d-flex">
                      <div class="notification-text">
                        <sdHeading as="h5">
                          <span>{{ notification.userName }}</span> {{ notification.subject }}
                        </sdHeading>
                        <p>{{ notification.date }}</p>
                      </div>
                      <!-- <div class="notification-status">
                        <a-badge v-if="!notification.is_read" dot />
                      </div> -->
                    </div>
                  </div>
                <!-- </a> -->
              </li>
            </ul>
          </perfect-scrollbar>
          <router-link class="btn-seeAll" to="/activity">
            See all incoming activity
          </router-link>
          
        </AtbdTopDropdwon>
        
      </template>
      <a to="#" class="head-example">
        <a-badge dot v-if="notificationList && notificationList.length > 0" />
        <sdFeatherIcons type="bell" size="20" />
      </a>
    </sdPopover>
  </div>
</template>
<script>
import { PerfectScrollbar } from "vue3-perfect-scrollbar";
import "vue3-perfect-scrollbar/dist/vue3-perfect-scrollbar.css";
import { AtbdTopDropdwon } from "./auth-info-style";
import { defineComponent, reactive, ref } from "vue";
import Stomp from 'webstomp-client';
import Sockjs from 'sockjs-client';
import { useStore } from 'vuex';

export default defineComponent({
  name: "Notification",
  components: {
    AtbdTopDropdwon,
    PerfectScrollbar,
  },
  data() {
    const { dispatch } = useStore();
    dispatch('getUser');
  },
  setup() {
    const { state, dispatch } = useStore();

    const notificationList = ref();
    const getUser = reactive(state.getUser.data);

    const API_ENDPOINT = process.env.VUE_APP_API_ENDPOINT;

    const socket = new Sockjs(API_ENDPOINT+"/gs-guide-websocket");
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      // console.log(getUser.value.preferredUsername); TODO: 유저별로 구독하게 해야함
      // const groupName = getUser.value.attributes.groups[0].substring(1);
      
      stompClient.subscribe('/topic/'+getUser.name, message =>
        {
          notificationList.value = JSON.parse(message.body);
        }
      );

      // dispatch('getNotifications', getUser.value.name); TODO: 유저별로 알림을 가져와야함
      dispatch('getNotifications', getUser.name);
    });

    return {
      notificationList,
    };
  },
  
});
</script>
<style scoped>
.ps {
  height: 200px;
}
</style>
