<template>
    <div>
        <Main>
            <ActivityContents>
                <sdCards title="장비변경이력">
                    <ul v-if="activities.length > 0" class="activity-list">
                        <li v-for="(activity, i) in activities" class="activity-list__single" :key="i">
                            <span :class="['activity-icon', activity.iconClass]">
                                <sdFeatherIcons :type="activity.icon" size="14" />
                            </span>
                            <div class="activity-content">
                                <div class="activity-info">
                                    <a
                                        to="#"
                                        @click="() => showModal(activity)"
                                    >
                                        <p>
                                            <span class="inline-text color-primary">{{ activity.userName }}</span>
                                            이/가 {{ activity.message }}
                                            <span class="hour">{{ activity.date }}</span>
                                        </p>
                                    </a>
                                </div>
                                <a class="activity-more" to="#">
                                    <sdDropdown :action="['click']">
                                        <template #overlay>
                                            <a to="#">
                                                <span>Delete</span>
                                            </a>
                                        </template>
                                        <sdFeatherIcons type="more-horizontal" />
                                    </sdDropdown>
                                </a>
                            </div>
                        </li>
                    </ul>

                    <div v-else class="sDash_empty-task">
                        <span>Sorry !! No Activity Found :(</span>
                    </div>
                </sdCards>
            </ActivityContents>
        </Main>
    </div>
</template>
<script>
import { ActivityContents } from './style';
import { defineComponent } from 'vue';
import { Main } from '../styled';

const ActivityContent = defineComponent({
    name: 'ActivityContent',
    components: {
        Main,
        ActivityContents, 
    },
    setup() {
        const activities = [
                {
                    icon: 'inbox',
                    iconClass: 'primary',
                    userName: 'James',
                    message: '장비 사용 신청을 하였습니다.',
                    date: '2023.11.22'
                },
                {
                    icon: 'dollar-sign',
                    iconClass: 'primary',
                    userName: 'James',
                    message: '장비 구매 신청을 하였습니다.',
                    date: '2023.11.22'
                },
                {
                    icon: 'edit',
                    iconClass: 'success',
                    userName: 'James',
                    message: '신청 내용을 수정하였습니다.',
                    date: '2023.11.22'
                },
                {
                    icon: 'trash',
                    iconClass: 'secondary',
                    userName: 'Adam',
                    message: '폐기 신청을 하였습니다.',
                    date: '2023.11.21'
                },
            ];

            const showModal = (row) => {
                // console.log(row); row 객체 다 받아올 수 있음
                console.log(row);
            };

        return {
            activities,
            showModal,
        };
    },
});

export default ActivityContent;
</script>
  