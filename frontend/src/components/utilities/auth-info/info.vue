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
                                <img
                                    :src="
                                        require('../../../static/img/avatar/chat-auth.png')
                                    "
                                    alt=""
                                />
                                <figcaption>
                                    <sdHeading as="h5"
                                        >Abdullah Bin Talha</sdHeading
                                    >
                                    <p>UI Expert</p>
                                </figcaption>
                            </figure>
                            <ul class="user-dropdwon__links">
                                <li>
                                    <a to="#">
                                        <sdFeatherIcons type="user" /> Profile
                                    </a>
                                </li>
                                <li>
                                    <a to="#">
                                        <sdFeatherIcons type="settings" />
                                        Settings
                                    </a>
                                </li>
                                <li>
                                    <a to="#">
                                        <sdFeatherIcons type="dollar-sign" />
                                        Billing
                                    </a>
                                </li>
                                <li>
                                    <a to="#">
                                        <sdFeatherIcons type="users" /> Activity
                                    </a>
                                </li>
                                <li>
                                    <a to="#">
                                        <sdFeatherIcons type="bell" /> Help
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
import { useRouter } from 'vue-router';
import { LogoutOutlined } from '@ant-design/icons-vue';
import { defineComponent, computed } from 'vue';

export default defineComponent({
    name: 'AuthInfo',
    components: {
        InfoWraper,
        UserDropDwon,
        Notification,
        LogoutOutlined,
    },
    setup() {
        const { dispatch } = useStore();
        const { push } = useRouter();
        const SignOut = (e) => {
            e.preventDefault();
            push('/auth/login');
            dispatch('logOut');
        };
        const { state } = useStore();
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
        };
    },
});
</script>
