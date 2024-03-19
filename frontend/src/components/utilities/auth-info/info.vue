<template>
    <InfoWraper>
        <Notification />

        <div class="nav-author">
            <sdPopover
                placement="bottomRight"
                action="click"
                :class="rtl ? 'ant-popover-rtl' : ''"
            >
                <template v-slot:content>
                    <UserDropDwon>
                        <div class="user-dropdwon">
                            <figure class="user-dropdwon__info">
                                <figcaption>
                                    <sdHeading as="h5" 
                                        >{{ username }}</sdHeading>
                                    <p :style="{ width: '78px' }"></p>
                                    
                                </figcaption>
                            </figure>
                            <ul class="user-dropdwon__links">
                                <li>
                                    <a to="#">
                                        <sdFeatherIcons type="user" /> Profile
                                    </a>
                                </li>
                                
                            </ul>
                            <a
                                @click="SignOut"
                                class="user-dropdwon__bottomAction"
                                href="#"
                            >
                                <LogoutOutlined /> Sign Out
                            </a>
                        </div>
                    </UserDropDwon>
                </template>
                <a to="#" class="head-example">
                    <a-avatar
                        src="https://cdn0.iconfinder.com/data/icons/user-pictures/100/matureman1-512.png"
                    />
                </a>
            </sdPopover>
        </div>
    </InfoWraper>
</template>

<script>
import { InfoWraper, UserDropDwon } from './auth-info-style';
import Notification from './Notification';
import { useStore } from 'vuex';
import { LogoutOutlined } from '@ant-design/icons-vue';
import { defineComponent, computed, ref } from 'vue';

export default defineComponent({
    name: 'AuthInfo',
    components: {
        InfoWraper,
        UserDropDwon,
        Notification,
        LogoutOutlined,
    },
    data() {
        const { dispatch } = useStore();
        dispatch('getUser');
    },
    async setup() {
        const { state, dispatch } = useStore();
        const API_ENDPOINT = process.env.VUE_APP_API_ENDPOINT;
        const SignOut = (e) => {
            e.preventDefault();
            dispatch('logOut').then(()=>{
                window.location.href = API_ENDPOINT+'/logout';
            });
        };
        const username = ref('');
        await dispatch('getUser').then(() => {
            username.value = state.getUser.data.name;
        });
        
        const rtl = computed(() => state.themeLayout.rtlData);
        let flag = 'english';

        const onFlagChangeHandle = (value) => {
            this.flag = value;
        };
        return {
            SignOut,
            rtl,
            flag,
            onFlagChangeHandle,
            username,
        };
    },
});
</script>
