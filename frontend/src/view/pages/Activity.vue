<template>
    <div>
        <Main>
            <ActivityContents>
                <sdCards title="신청 이력">
                    <ul v-if="activities && activities.length > 0" class="activity-list">
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
                                            {{ activity.subject }}
                                            <span class="hour">{{ activity.date }}</span>
                                        </p>
                                    </a>
                                </div>
                                <a class="activity-more" to="#">
                                    <sdDropdown :action="['click']">
                                        <template #overlay>
                                            <a 
                                                to="#"
                                                @click="() => deleteNotification(activity.id)">
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
import { defineComponent, computed, onMounted } from 'vue';
import { Main } from '../styled';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { getActivities } from './getActivities';

const ActivityContent = defineComponent({
    name: 'ActivityContent',
    components: {
        Main,
        ActivityContents, 
    },
    setup() {
        const response = getActivities.data;
        const { state, dispatch } = useStore();
        const { push } = useRouter();
        state.getActivities.data = response;
        dispatch('getUser');
        const activities = computed(() => state.getActivities.data);

        const showModal = (activity) => {
            push(activity.link);
        };

        const deleteNotification = (id) => {
            dispatch('deleteNotification', id)
                .then(() => {
                        location.reload();
                    }
                );
        };      
        
        onMounted(() => {
            dispatch('getUser').then(() => {
                dispatch('fetchActivities', state.getUser.data.name);
            });
            
        });

        return {
            activities,
            showModal,
            deleteNotification,
        };
    },
});

export default ActivityContent;
</script>
  